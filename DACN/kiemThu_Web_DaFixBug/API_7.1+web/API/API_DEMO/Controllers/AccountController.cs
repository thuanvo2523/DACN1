using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Net.Http;

using Microsoft.AspNetCore.Authentication.Cookies;
using API_DEMO.Areas.Identity.Pages.Account;
using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Identity;
using static API_DEMO.Areas.Identity.Pages.Account.LoginModel;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace API_DEMO.Controllers
{
    public class AccountController : Controller
    {
        private readonly IHttpClientFactory _httpClientFactory;
        private readonly SignInManager<IdentityUser> _signInManager;
        private readonly UserManager<IdentityUser> _userManager;

        public AccountController(
            SignInManager<IdentityUser> signInManager,
            UserManager<IdentityUser> userManager,
            IHttpClientFactory httpClientFactory)
        {
            _signInManager = signInManager;
            _userManager = userManager;
            _httpClientFactory = httpClientFactory;
        }
        [HttpGet]
        public IActionResult Login()
        {
            return View();
        }


        [HttpPost]
        public async Task<IActionResult> Login(string username, string password)
        {
            // Thực hiện logic đăng nhập
            var result = await _signInManager.PasswordSignInAsync(username, password, isPersistent: false, lockoutOnFailure: false);

            if (result.Succeeded)
            {
                // Đăng nhập thành công
                return RedirectToAction("LoggedIn", "Home"); // Chuyển hướng đến trang đã đăng nhập thành công
            }
            else
            {
                // Đăng nhập thất bại, xử lý tương ứng (ví dụ: hiển thị thông báo lỗi)
                ModelState.AddModelError(string.Empty, "Đăng nhập không thành công. Vui lòng kiểm tra lại tên người dùng và mật khẩu.");
                return View(); // Chuyển hướng lại trang đăng nhập với thông báo lỗi
            }
        }
            // POST: /Account/Login

            //public async Task<IActionResult> Login(LoginViewModel model)
            //{
            //    if (ModelState.IsValid)
            //    {
            //        using var httpClient = _httpClientFactory.CreateClient();

            //        try
            //        {
            //            string apiUrl = "/api/TaiKhoan/dangNhapTaiKhoan";

            //            // Tạo dữ liệu yêu cầu dưới dạng chuỗi content
            //            string requestData = "taiKhoan="+model.Username.ToString()+"&matKhau="+model.Password.ToString();

            //            // Tạo nội dung yêu cầu POST từ chuỗi dữ liệu
            //            HttpContent content = new StringContent(requestData, System.Text.Encoding.UTF8, "application/x-www-form-urlencoded");

            //            // Thực hiện yêu cầu POST đến API
            //            HttpResponseMessage response = await httpClient.PostAsync(apiUrl, content);



            //            if (response.IsSuccessStatusCode)
            //            {
            //                var result = await response.Content.ReadFromJsonAsync<LoginResultViewModel>();

            //                if (result.status == true)
            //                {
            //                    // Đăng nhập thành công, chuyển hướng đến trang chính
            //                    return RedirectToAction("Index", "Home");
            //                }
            //                else
            //                {
            //                    // Đăng nhập không thành công, hiển thị thông báo lỗi
            //                    ModelState.AddModelError(string.Empty, result?.Message ?? "Invalid login attempt.");
            //                    return View(model);
            //                }
            //            }
            //            else
            //            {
            //                // Xử lý lỗi từ phản hồi API
            //                ModelState.AddModelError(string.Empty, "Error while processing the request.");
            //                return View(model);
            //            }
            //        }
            //        catch (HttpRequestException e)
            //        {
            //            Console.WriteLine("Request failed: " + e.Message);
            //        }


            //    }

            //    // Nếu dữ liệu không hợp lệ, hiển thị lại form đăng nhập với thông tin đã nhập và thông báo lỗi
            //    return View(model);
            //}


            // Định nghĩa các action khác như Register, ForgotPassword, ...

            // GET: /Account/Register
            [HttpGet]
        public IActionResult Register()
        {
            return View();
        }

        // POST: /Account/Register
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Register(RegisterViewModel model)
        {
            if (ModelState.IsValid)
            {
                using var httpClient = _httpClientFactory.CreateClient();

                var apiUrl = "https://localhost:7159/api/TaiKhoan/dangKyTaiKhoan1";
                var response = await httpClient.PostAsJsonAsync(apiUrl, model);

                if (response.IsSuccessStatusCode)
                {
                    var result = await response.Content.ReadFromJsonAsync<RegisterResultViewModel>();

                    if (result.status == true)
                    {
                        // Đăng ký thành công, có thể chuyển hướng đến trang chủ hoặc trang thông báo thành công
                        return RedirectToAction("Index", "Home");
                    }
                }

                // Đăng ký không thành công, hiển thị thông báo lỗi
                ModelState.AddModelError(string.Empty, "Error while registering.");
                return View(model);
            }

            // Nếu dữ liệu không hợp lệ, hiển thị lại form đăng ký với thông tin đã nhập và thông báo lỗi
            return View(model);
        }

        // Các action khác...
    }

    internal class RegisterResultViewModel
    {
        public bool status { get; set; }
        public string Message { get; set; }
    }



    // ... các action khác ...

    //GET: /Account/Logout
    //[HttpGet]
    //public async Task<IActionResult> Logout()
    //{
    //    // Xóa tất cả thông tin xác thực của người dùng
    //    await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);

    //    // Chuyển hướng đến trang chủ hoặc trang đăng nhập
    //    return RedirectToAction("Index", "Home");
    //}


    internal class LoginResultViewModel
    {
        public bool status { get; set; }
        public string Message { get; set; }
    }

    // Model đại diện cho thông tin đăng nhập
    public class LoginViewModel
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }

    // Model đại diện cho thông tin đăng ký tài khoản
    public class RegisterViewModel
    {
        // Các thuộc tính thông tin tài khoản cần thiết để đăng ký
        public string taiKhoan { get; set; }
        public string matKhau { get; set; }
        public string? ten { get; set; }
        public byte[]? avatar { get; set; }
        public string? moMo { get; set; }
        public string? soDienThoai { get; set; }
        public string? diaChi { get; set; }
        public string? otp { get; set; }
    }

}