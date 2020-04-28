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
        private IProductRepository repository;
        private Cart cart;

        public CartController(IProductRepository repo, Cart cartService) {
            repository = repo;
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
            var content = await client.GetStringAsync($"http://localhost:7002/products/{productId}");
            Console.WriteLine(content.GetType());
            Console.WriteLine(content);

            var parsedProduct = JsonConvert.DeserializeObject<List<Product>>(content);
            if (parsedProduct.Count>0)
            {
                var product = parsedProduct[0];
                if (product != null)
                {
                    cart.AddItem(product, 1);
                }
            }
            

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
            Product product = repository.Products
                .FirstOrDefault(p => p.ProductID == productId);

            if (product != null) {
                cart.RemoveLine(product);
            }
            return RedirectToAction("Index", new { returnUrl });
        }
    }
}