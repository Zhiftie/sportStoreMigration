using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using SportsStore.Models;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Newtonsoft.Json;
using SportsStore.Models.ViewModels;

namespace SportsStore.Controllers {
    [Authorize]
    public class ProductController : Controller {
        public int PageSize = 4;

        public ProductController() {
        }

        /*
        public ViewResult List(string category, int productPage = 1)
            => View(new ProductsListViewModel
            {
                Products = repository.Products
                    .Where(p => category == null || p.Category == category)
                    .OrderBy(p => p.ProductID)
                    .Skip((productPage - 1) * PageSize)
                    .Take(PageSize),
                PagingInfo = new PagingInfo
                {
                    CurrentPage = productPage,
                    ItemsPerPage = PageSize,
                    TotalItems = category == null
                        ? repository.Products.Count()
                        : repository.Products.Where(e =>
                            e.Category == category).Count()
                },
                CurrentCategory = category
            });
            */


        public async Task<IActionResult> List()
        {
            var accessToken = await HttpContext.GetTokenAsync("access_token");

            var client = new HttpClient();
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", accessToken);

            var content = await client.GetStringAsync("http://localhost:7000/api/products");

            var model = JsonConvert.DeserializeObject<List<Product>>(content);
            var view = new ProductsListViewModel
            {
                Products = model,
                PagingInfo = new PagingInfo
                {
                    CurrentPage = 1,
                    ItemsPerPage = 4,
                    TotalItems = 5
                },
                CurrentCategory = "Football"
            };


            return View(view);
        }
    }
}
