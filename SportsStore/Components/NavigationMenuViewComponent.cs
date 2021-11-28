using Microsoft.AspNetCore.Mvc;
using System.Linq;
using SportsStore.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using SportsStore.Models.ViewModels;


namespace SportsStore.Components {

    public class NavigationMenuViewComponent : ViewComponent {

        public NavigationMenuViewComponent() {
        }

        public IViewComponentResult Invoke() {
            ViewBag.SelectedCategory = RouteData?.Values["category"];
            var client = new HttpClient();
            var task = Task.Run(() => client.GetStringAsync("http://localhost:7000/api/products/categories")); 
            task.Wait();
            var response = task.Result;
            var categories = JsonConvert.DeserializeObject<List<String>>(response);
            return View(categories);
        }
    }
}
