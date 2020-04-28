using System;
using Microsoft.AspNetCore.Mvc;
using SportsStore.Models;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Newtonsoft.Json;

namespace SportsStore.Controllers {

    public class OrderController : Controller {
        private IOrderRepository repository;
        private Cart cart;

        public OrderController(IOrderRepository repoService, Cart cartService) {
            repository = repoService;
            cart = cartService;
        }

        [Authorize]
        public ViewResult List() =>
            View(repository.Orders.Where(o => !o.Shipped));

        [HttpPost]
        [Authorize]
        public IActionResult MarkShipped(int orderID) {
            Order order = repository.Orders
                .FirstOrDefault(o => o.OrderID == orderID);
            if (order != null) {
                order.Shipped = true;
                repository.SaveOrder(order);
            }
            return RedirectToAction(nameof(List));
        }

        public ViewResult Checkout() => View(new Order());

        [HttpPost]
        public async Task<IActionResult> Checkout(Order order) {
            if (cart.Lines.Count() == 0) {
                ModelState.AddModelError("", "Sorry, your cart is empty!");
            }
            if (ModelState.IsValid) {
                Console.WriteLine("Checkout Called");
                order.Lines = cart.Lines.ToArray();

                var accessToken = await HttpContext.GetTokenAsync("access_token");

                var client = new HttpClient();
                client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", accessToken);
                var OrderString = JsonConvert.SerializeObject(order);
                var buffer = System.Text.Encoding.UTF8.GetBytes(OrderString);
                var byteContent = new ByteArrayContent(buffer);
                byteContent.Headers.ContentType = new MediaTypeHeaderValue("application/json");

                // HttpContent content = new ByteArrayContent(JsonConvert.SerializeObject(order));

                HttpResponseMessage response = await client.PostAsync("http://localhost:7000/api/checkout", byteContent);

                return RedirectToAction(nameof(Completed));
                
            } else {
                return View(order);
            }

        }

        public ViewResult Completed() {
            cart.Clear();
            return View();
        }
    }
}
