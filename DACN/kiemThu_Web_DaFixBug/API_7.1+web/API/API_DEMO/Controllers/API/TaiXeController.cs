using API_DEMO.Models;
using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore.Metadata.Internal;
using System.Linq;
using System.Reflection;

namespace API_DEMO.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class TaiXeController : ControllerBase
    {
        private TaiXeService TaiXeService;

        public TaiXeController(TaiXeService TaiXeService)
        {
            this.TaiXeService = TaiXeService;
        }
        [HttpPost]
        [Route("insertTaiXe")]
        public IActionResult insertTaiXe(string taiKhoan, string moTa, string thongTinXe, string bienSo)
        {
            TaiXe tam = TaiXeService.getTaiXes(taiKhoan);
            if(tam != null)
            {
                return Ok(new { status = false, message = "daTonTai" });
            }
            TaiXe TaiXe = new TaiXe();
            TaiXe.taiKhoan = taiKhoan;
            TaiXe.moTa = moTa;
            TaiXe.thongTinXe = thongTinXe;
            TaiXe.bienSo = bienSo;
            TaiXeService.insertTaiXe(TaiXe);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("ktTonTai")]
        public IActionResult ktTonTai(string taiKhoan)
        {
            TaiXe tam = TaiXeService.getTaiXes(taiKhoan);
            if (tam == null)
            {
                return Ok(new { status = false, message = "" });
            }
            else
            {
                if(tam.hinhBangLai == null || tam.hinhCMND ==  null || tam.hinhGiayToXe == null || tam.hinhXe == null)
                {
                    return Ok(new { status = true, message = "chuaCoHinh" });
                }
                else
                {
                    return Ok(new { status = true, message = "" });
                }
            }
        }

        [HttpPost]
        [Route("updateTrangThai")]
        public IActionResult updateTrangThai(string taiKhoan, Boolean hoatDong)
        {
            TaiXe TaiXe = new TaiXe();
            TaiXe.taiKhoan = taiKhoan;
            TaiXe.hoatDong = hoatDong;
            TaiXeService.updateTaiXe(TaiXe);
            if(hoatDong == true)
            {
                return Ok(new { status = true, message = "true" });
            }
            return Ok(new { status = true, message = "false" });
        }


        //Step by step

        [HttpPost]
        [Route("addBangLai")]
        public IActionResult addBangLai([FromForm] InformationTemp temp)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(temp.taiKhoan);
            if (TaiXe == null)
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
            TaiXe.hinhBangLai = imageBytes;
            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("addCMND")]
        public IActionResult addCMND([FromForm] InformationTemp temp)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(temp.taiKhoan);
            if (TaiXe == null)
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
            TaiXe.hinhCMND = imageBytes;
            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("addHinhXe")]
        public IActionResult addHinhXe([FromForm] InformationTemp temp)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(temp.taiKhoan);
            if (TaiXe == null)
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
            TaiXe.hinhXe = imageBytes;
            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("addGiayToXe")]
        public IActionResult addGiayToXe([FromForm] InformationTemp temp)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(temp.taiKhoan);
            if (TaiXe == null)
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
            TaiXe.hinhGiayToXe = imageBytes;
            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "" });
        }

        //kết thúc step by step

        [HttpGet]
        [Route("Get-TaiXe-List")]
        public IActionResult GetTaiXeList()
        {

            List<TaiXe> TaiXeList = TaiXeService.getTaiXesList();
            return Ok(new { status = true, message = "", data = TaiXeList });
        }

        [HttpPost]
        [Route("Delete")]
        public IActionResult deleteTaiXe(string taiKhoan)
        {
            TaiXeService.deleteTaiXe(taiKhoan);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("updateTaiXe")]
        public IActionResult updateTaiXe(string taiKhoan, string moTa, string thongTinXe, string bienSo)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(taiKhoan);
            TaiXe.taiKhoan = taiKhoan;
            TaiXe.thongTinXe = thongTinXe;
            TaiXe.moTa = moTa;
            TaiXe.bienSo = bienSo;
            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "", data = TaiXe });

        }


        //Step by step update

        [HttpPost]
        [Route("UpdateHinhBangLai")]
        public IActionResult UpdateHinhBangLai(string taiKhoan, byte[] hinhBangLai)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(taiKhoan);

            TaiXe.taiKhoan = taiKhoan;
            TaiXe.hinhBangLai = null;
            TaiXe.hinhBangLai = TaiXe.hinhBangLai.Concat(hinhBangLai).ToArray(); ;

            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "", data = TaiXe });

        }

        [HttpPost]
        [Route("UpdateHinhCMND")]
        public IActionResult UpdateHinhCMND(string taiKhoan, byte[] hinhCMND)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(taiKhoan);

            TaiXe.taiKhoan = taiKhoan;
            TaiXe.hinhCMND = null;
            TaiXe.hinhCMND = TaiXe.hinhCMND.Concat(hinhCMND).ToArray(); ;

            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "", data = TaiXe });

        }

        [HttpPost]
        [Route("UpdateHinhXe")]
        public IActionResult UpdateHinhXe(string taiKhoan, byte[] hinhXe)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(taiKhoan);

            TaiXe.taiKhoan = taiKhoan;
            TaiXe.hinhXe = null;
            TaiXe.hinhXe = TaiXe.hinhXe.Concat(hinhXe).ToArray(); ;

            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "", data = TaiXe });

        }

        [HttpPost]
        [Route("UpdateHinhGiayToXe")]
        public IActionResult UpdateHinhGiayToXe(string taiKhoan, byte[] hinhGiayToXe)
        {
            TaiXe TaiXe = TaiXeService.getTaiXes(taiKhoan);

            TaiXe.taiKhoan = taiKhoan;
            TaiXe.hinhGiayToXe = null;
            TaiXe.hinhGiayToXe = TaiXe.hinhGiayToXe.Concat(hinhGiayToXe).ToArray(); ;

            TaiXeService.updateTaiXe(TaiXe);
            return Ok(new { status = true, message = "", data = TaiXe });

        }

        //kết thúc step by step Update


        [HttpGet]
        [Route("Get-TaiXe-List-HoatDong")]
        public IActionResult GetListTaiXeHoatDong()
        {

            List<TaiKhoan> ListTaiKhoan = TaiXeService.getListTaiXeHoatDong();
            return Ok(new { status = true, message = "", data = ListTaiKhoan });
        }
    }
}
