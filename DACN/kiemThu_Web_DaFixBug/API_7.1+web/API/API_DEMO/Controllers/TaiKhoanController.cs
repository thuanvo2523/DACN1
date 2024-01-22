using Microsoft.AspNetCore.Mvc;
using API_DEMO.Models;

namespace WebApplication1.Controllers
{
    public class TaiKhoanController : Controller
    {
        public IActionResult Index()
        {
            //TaiKhoan taiKhoan = new TaiKhoan() { taiKhoan = "0888076609" ,matKhau="123456",ten="alo",soDienThoai="123456",diaChi="1eijlsmdkw" };
            return View();
        }
    }
}
