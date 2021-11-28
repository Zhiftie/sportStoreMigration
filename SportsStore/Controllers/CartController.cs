using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SportsStore.Models;
using SportsStore.Models.ViewModels;

namespace SportsStore.Controllers {

    public class CartController : Controller {
        private Cart cart;

        public CartController(Cart cartService) {
            cart = cartService;
        }

        public ViewResult Index(string returnUrl) {
            return View(new CartIndexViewModel {
                Cart = cart,
                ReturnUrl = returnUrl
            });
        }

        public async Task<IActionResult> AddToCart(int productId, string returnUrl) {
            var accessToken = await HttpContext.GetTokenAsync("access_token");

            var client = new HttpClient();
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", accessToken);
            var content = await client.GetStringAsync($"http://localhost:7000/api/products/{productId}");
            Console.WriteLine(content.GetType());
            Console.WriteLine(content);

            var parsedProduct = JsonConvert.DeserializeObject<Product>(content);
            cart.AddItem(parsedProduct, 1);
            

            foreach (var line in cart.Lines)
            {
                Console.WriteLine(line.Product.Name);
                Console.WriteLine(line.Quantity);
            }

            return await Task.Run<ActionResult>(() =>
            {
                return RedirectToAction("Index", new {returnUrl}); 

            });
        }


        public RedirectToActionResult RemoveFromCart(int productId,
                string returnUrl) {
            var client = new HttpClient();
            var task = Task.Run(() => client.GetStringAsync("http://localhost:7000/api/products/" + productId)); 
            task.Wait();
            var response = task.Result;
            var product = JsonConvert.DeserializeObject<Product>(response);
            if (product != null) {
                cart.RemoveLine(product);
            }
            return RedirectToAction("Index", new { returnUrl });
        }
    }
}