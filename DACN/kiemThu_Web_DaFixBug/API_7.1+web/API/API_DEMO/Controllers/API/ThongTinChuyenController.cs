using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Reflection;

namespace API_DEMO.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class ThongTinChuyenController : ControllerBase
    {
        private ThongTinChuyenService ThongTinChuyenService;

        public ThongTinChuyenController(ThongTinChuyenService ThongTinChuyenService)
        {
            this.ThongTinChuyenService = ThongTinChuyenService;
        }
        [HttpPost]
        [Route("insertThongTinChuyen")]
        public IActionResult insertThongTinChuyen(string diemBatDau, string diemDen, float soKm, decimal soTien)
        {
            ThongTinChuyen thongTinChuyen = new ThongTinChuyen();
            thongTinChuyen.maChuyen=Guid.NewGuid();
            thongTinChuyen.diemBatDau=diemBatDau;
            thongTinChuyen.soKm=soKm;
            thongTinChuyen.diemDen=diemDen;
            thongTinChuyen.soTien=soTien;
            ThongTinChuyenService.insertThongTinChuyen(thongTinChuyen);
            return Ok(new { status = true, message = "" });
        }

        [HttpGet]
        [Route("Get-ThongTinChuyen-List")]
        public IActionResult GetThongTinChuyenList()
        {

            List<ThongTinChuyen> ThongTinChuyenList = ThongTinChuyenService.getThongTinChuyenList();
            return Ok(new { status = true, message = "", data = ThongTinChuyenList });
        }

        [HttpPost]
        [Route("Delete")]
        public IActionResult deleteThongTinChuyen(Guid maChuyen)
        {
            ThongTinChuyenService.deleteThongTinChuyen(maChuyen);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("updateThongTinChuyen")]
        public IActionResult updateThongTinChuyen(Guid maChuyen, string diemBatDau, string diemDen, float soKm, decimal soTien)
        {
            ThongTinChuyen thongTinChuyen = ThongTinChuyenService.getThongTinChuyen(maChuyen);
            thongTinChuyen.diemBatDau = diemBatDau;
            thongTinChuyen.soKm = soKm;
            thongTinChuyen.diemDen = diemDen;
            thongTinChuyen.soTien = soTien;

            ThongTinChuyenService.updateThongTinChuyen(thongTinChuyen);
            return Ok(new { status = true, message = "", data = thongTinChuyen });

        }
    }
}
