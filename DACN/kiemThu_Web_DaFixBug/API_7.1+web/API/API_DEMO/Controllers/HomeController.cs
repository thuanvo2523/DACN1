using API_DEMO.Models;
using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace API_DEMO.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger,DatChuyenService datChuyenService,ChiTietDatChuyenService chiTietDatChuyenService,TaiKhoanService taiKhoanService,PictureService pictureService,TaiXeService taiXeService,ThongTinChuyenService thongTinChuyenService)
        {

            _logger = logger;
            
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}