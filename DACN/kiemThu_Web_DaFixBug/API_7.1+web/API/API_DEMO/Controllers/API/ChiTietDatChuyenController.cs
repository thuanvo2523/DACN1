using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Reflection;

namespace API_DEMO.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class ChiTietDatChuyenController : ControllerBase
    {
        private ChiTietDatChuyenService ChiTietDatChuyenService;

        public ChiTietDatChuyenController(ChiTietDatChuyenService ChiTietDatChuyenService)
        {
            this.ChiTietDatChuyenService = ChiTietDatChuyenService;
        }
        [HttpPost]
        [Route("insertChiTietDatChuyen")]
        public IActionResult insertChiTietDatChuyen(string diemBatDau, string diemDen, float soKm, decimal soTien, string tkTaiXe)
        {
            ChiTietDatChuyen ChiTietDatChuyen = new ChiTietDatChuyen();
            ChiTietDatChuyen.maChiTiet=Guid.NewGuid();
            ChiTietDatChuyen.diemBatDau=diemBatDau;
            ChiTietDatChuyen.diemDen=diemDen;
            ChiTietDatChuyen.soKm=soKm;
            ChiTietDatChuyen.soTien=soTien;
            ChiTietDatChuyen.tkTaiXe=tkTaiXe;
            
            ChiTietDatChuyenService.insertChiTietDatChuyen(ChiTietDatChuyen);
            return Ok(new { status = true, message = "" });
        }

        [HttpGet]
        [Route("Get-ChiTietDatChuyen-List")]
        public IActionResult GetChiTietDatChuyenList()
        {

            List<ChiTietDatChuyen> ChiTietDatChuyenList = ChiTietDatChuyenService.getChiTietDatChuyenList();
            return Ok(new { status = true, message = "", data = ChiTietDatChuyenList });
        }

        [HttpPost]
        [Route("Delete")]
        public IActionResult deleteChiTietDatChuyen(Guid maChiTiet)
        {
            ChiTietDatChuyenService.deleteChiTietDatChuyen(maChiTiet);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("updateChiTietDatChuyen")]
        public IActionResult updateChiTietDatChuyen(Guid maChiTiet, string diemBatDau, string diemDen, float soKm, decimal soTien, string tkTaiXe)
        {
            ChiTietDatChuyen ChiTietDatChuyen = ChiTietDatChuyenService.getChiTietDatChuyen(maChiTiet);
            ChiTietDatChuyen.diemBatDau = diemBatDau;
            ChiTietDatChuyen.diemDen = diemDen;
            ChiTietDatChuyen.soKm = soKm;
            ChiTietDatChuyen.soTien = soTien;
            ChiTietDatChuyen.tkTaiXe = tkTaiXe;
            ChiTietDatChuyenService.updateChiTietDatChuyen(ChiTietDatChuyen);
            return Ok(new { status = true, message = "", data = ChiTietDatChuyen });

        }
    }
}
