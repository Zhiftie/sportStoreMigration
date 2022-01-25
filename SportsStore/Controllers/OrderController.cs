using System;
using System.Collections.Generic;
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
        private Cart cart;
        private static readonly String TENANT_X = "2";

        public OrderController(Cart cartService) {
            cart = cartService;
        }

        [Authorize]
        public ViewResult List() {
            var client = new HttpClient();
            var task = Task.Run(() => client.GetStringAsync("http://localhost:7000/api/orders")); 
            task.Wait();
            var response = task.Result;
            var orders = JsonConvert.DeserializeObject<List<Order>>(response);
            String tenant = HttpContext.User.FindFirst("sub")?.Value;
            if(tenant.Equals(TENANT_X))
            {
                orders.ForEach(order => {
                    try 
	                {	        
		                var shippingInformation = FetchShippingInformation(order.OrderID);
                        order.ShippingInformation = shippingInformation;
	                }
	                catch (Exception)
	                {
                        //Ignore
	                }
                });
            }
            return View(orders);
        }

        private ShippingInformation FetchShippingInformation(int orderId)
        {
            var client = new HttpClient();
            var task = Task.Run(() => client.GetStringAsync("http://localhost:8083/shippinginformation/" + orderId)); 
            task.Wait();
            var response = task.Result;
            return JsonConvert.DeserializeObject<ShippingInformation>(response);
        }

        [HttpPost]
        [Authorize]
        public IActionResult MarkShipped(int orderID) {
            //TODO or remove
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
