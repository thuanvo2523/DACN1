using API_DEMO.Models;
using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.ComponentModel.DataAnnotations;
using System.Text.RegularExpressions;
using Twilio.Rest.Api.V2010.Account;
using Twilio.Types;

namespace API_DEMO.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class TaiKhoanController : ControllerBase
    {
        private TaiKhoanService TaiKhoanService;


        public TaiKhoanController(TaiKhoanService TaiKhoanService)
        {
            this.TaiKhoanService = TaiKhoanService;
        }

        [HttpPost]
        [Route("insertTaiKhoan")]
        public IActionResult insertTaiKhoan(string taiKhoan, string matKhau, string ten, byte[] avatar, string moMo, string soDienThoai, string diaChi, string otp)
        {
            TaiKhoan TaiKhoan = new TaiKhoan();
            TaiKhoan.taiKhoan = taiKhoan;
            TaiKhoan.matKhau = matKhau;
            TaiKhoan.ten = ten;
            TaiKhoan.avatar = avatar;
            TaiKhoan.moMo = moMo;
            TaiKhoan.soDienThoai = soDienThoai;
            TaiKhoan.diaChi = diaChi;
            TaiKhoanService.insertTaiKhoan(TaiKhoan);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("dangKyTaiKhoan")]
        public IActionResult dangKyTaiKhoan(string taiKhoan, string matKhau, string ten, string soDienThoai)
        {
            TaiKhoan tam = TaiKhoanService.getTaiKhoans(taiKhoan);
            if (tam != null)
            {
                return BadRequest("loi");
            }
            TaiKhoan TaiKhoan = new TaiKhoan();
            TaiKhoan.taiKhoan = taiKhoan;
            TaiKhoan.matKhau = matKhau;
            TaiKhoan.ten = ten;
            TaiKhoan.soDienThoai = soDienThoai;
            TaiKhoanService.insertTaiKhoan(TaiKhoan);
            return Ok(new { status = true, message = "" });
        }


        [HttpPost]
        [Route("dangKyTaiKhoan1")]
        public IActionResult dangKyTaiKhoan1(TaiKhoanModel taiKhoan)
        {
            TaiKhoan tam = TaiKhoanService.getTaiKhoans(taiKhoan.taiKhoan);
            if (tam == null)
            {
                TaiKhoan TaiKhoan = new TaiKhoan();
                TaiKhoan.taiKhoan = taiKhoan.taiKhoan;
                TaiKhoan.matKhau = taiKhoan.matKhau;
                TaiKhoan.ten = taiKhoan.ten;
                TaiKhoan.soDienThoai = taiKhoan.soDienThoai;
                TaiKhoanService.insertTaiKhoan(TaiKhoan);
                return Ok(new { status = true, message = "" });
            }
            else
            {
                return BadRequest("loi");
            }
        }

        [HttpPost]
        [Route("dangNhapTaiKhoan")]
        public IActionResult dangNhapTaiKhoan(string taiKhoan, string matKhau)
        {
            TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(taiKhoan);
            if(TaiKhoan != null && TaiKhoan.matKhau.Equals(matKhau))
            {
                return Ok(new { status = true, message = "" });
            }
            else
            {
                return Ok(new { status = false, message = "" });
            }
        }
        [HttpPost]
        [Route("dangNhapTaiKhoan1")]
        public IActionResult dangNhapTaiKhoan1(TaiKhoanModel taiKhoan)
        {
            TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(taiKhoan.taiKhoan);
            if (TaiKhoan != null && TaiKhoan.matKhau.Equals(taiKhoan.matKhau))
            {
                return Ok(new { status = true, message = "" });
            }
            else
            {
                return BadRequest("loi");
            }
        }
        //[HttpPost]
        //[Route("dangNhapTaiKhoan2")]
        public String dangNhapTaiKhoan2(string taiKhoan, string matKhau)
        {
            const string USERNAME_PATTERN = @"^[0-9A-Za-z]{8,20}$";

            // Regular expression for password
            const string PASSWORD_PATTERN = @"^(?=.*?[0-9])(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[^0-9A-Za-z]).{8,20}$";

            if (!Regex.IsMatch(taiKhoan, USERNAME_PATTERN))
            {
                return "Sai định dạng tài khoản";
            }
            else if (!Regex.IsMatch(matKhau, PASSWORD_PATTERN))
            {
                return "Sai định dạng mật khẩu";
            }
            else
            {
                TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(taiKhoan);
                if (TaiKhoan != null && TaiKhoan.matKhau.Equals(matKhau))
                {
                    return "Đăng nhập thành công!";
                }
                else
                {
                    return "Sai Tài Khoản hoặc Mật Khẩu!";
                    // Hiển thị thông báo lỗi cho người dùng nếu cần
                }
                     
            }

        }

        [HttpPost]
        [Route("updateHinhAnh")]
        public IActionResult updateHinhAnh([FromForm] InformationTemp temp)
        {
            TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(temp.taiKhoan);
            if (TaiKhoan == null)
            {
                return Ok(new { status = false, message = "Khong co tai khoan" });
            }
            if (temp.imageFile == null)
            {
                return BadRequest();
            }
            byte[] imageBytes;
            using (var stream = temp.imageFile.OpenReadStream())
            {
                using (var memoryStream = new MemoryStream())
                {
                    stream.CopyTo(memoryStream);
                    imageBytes = memoryStream.ToArray();
                }
            }
            TaiKhoan.avatar = imageBytes;
            TaiKhoanService.updateTaiKhoan(TaiKhoan);
            return Ok(new { status = true, message = "" });
        }

        [HttpGet]
        [Route("Get-TaiKhoan")]
        public IActionResult GetTaiKhoan(string taiKhoan)
        {
            TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(taiKhoan);
            return Ok(new { status = true, message = "", oneData = TaiKhoan });
        }

        [HttpGet]
        [Route("Get-TaiKhoan-List")]
        public IActionResult GetTaiKhoanList()
        {

            List<TaiKhoan> TaiKhoanList = TaiKhoanService.getTaiKhoansList();
            return Ok(new { status = true, message = "", data = TaiKhoanList });
        }

        [HttpPost]
        [Route("Delete")]
        public IActionResult deleteTaiKhoan(string taiKhoan)
        {
            TaiKhoanService.deleteTaiKhoan(taiKhoan);
            return Ok(new { status = true, message = "" });
        }

        string verificationCode = new Random().Next(1000, 9999).ToString();
        [HttpPost]
        [Route("forgotpassword")]
        public async Task<IActionResult> ForgotPassword(string taiKhoan)
        {
            // Tạo mã xác minh ngẫu nhiên
            TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(taiKhoan);
            if(TaiKhoan == null)
            {
                return Ok(new { status = false, message = "saiTaiKhoan" });
            }
             //verificationCode = new Random().Next(1000, 9999).ToString();
            string phoneNumber=null;
            if (TaiKhoan.soDienThoai.StartsWith("0"))
            {
                // Thay thế "0" bằng "+84"
                phoneNumber = "+84" + TaiKhoan.soDienThoai.Substring(1);
            }
            // Lưu mã xác minh trong cơ sở dữ liệu hoặc cache

            // Gửi mã xác minh qua SMS
            var message = await MessageResource.CreateAsync(
                body: $"Your verification code is: {verificationCode}",
                from: new Twilio.Types.PhoneNumber("+15026509383"),
                to: new Twilio.Types.PhoneNumber(phoneNumber)
            );

            if (message.Status != MessageResource.StatusEnum.Sent)
            {
                TaiKhoan.otp = verificationCode;
                return Ok(new { status = true, message = "" });
                
            }
            else
            {

                return Ok(new { status = false, message = "" });
            }
        }

        [HttpPost]
        [Route("verifycode")]
        public IActionResult VerifyCode(string taiKhoan,string OTP,string matKhauMoi)
        {

            //string savedVerificationCode = verificationCode; 
            TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(taiKhoan);
            
            /*if (OTP == savedVerificationCode)*/
            if(OTP.Equals(TaiKhoan.otp.Trim()))
            {

                TaiKhoan.matKhau = matKhauMoi;
                TaiKhoanService.updateTaiKhoan(TaiKhoan);
                return Ok(new { status = true, message = "" });
            }
            else
            {
                return Ok(new { status = false, message = "" });
            }
            
        }


        [HttpPost]
        [Route("updateTaiKhoan")]
        public IActionResult updateTaiKhoan(string taiKhoan, string matKhau, string ten, byte[] avatar, string moMo, string soDienThoai, string diaChi, string otp)
        {
            TaiKhoan TaiKhoan = TaiKhoanService.getTaiKhoans(taiKhoan);
            TaiKhoan.matKhau = matKhau;
            TaiKhoan.ten = ten;
            TaiKhoan.avatar = avatar;
            TaiKhoan.moMo = moMo;
            TaiKhoan.soDienThoai = soDienThoai;
            TaiKhoan.diaChi = diaChi;

            TaiKhoanService.updateTaiKhoan(TaiKhoan);
            return Ok(new { status = true, message = "", data = TaiKhoan  });

        }
    }
}
