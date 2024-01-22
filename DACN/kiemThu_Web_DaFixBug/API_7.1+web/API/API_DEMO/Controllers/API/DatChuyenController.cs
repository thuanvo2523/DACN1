using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Reflection;

namespace API_DEMO.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class DatChuyenController : ControllerBase
    {
        private DatChuyenService DatChuyenService;

        public DatChuyenController(DatChuyenService DatChuyenService)
        {
            this.DatChuyenService = DatChuyenService;
        }
        [HttpPost]
        [Route("insertDatChuyen")]
        public IActionResult insertDatChuyen(string tkKhach, DateTime thoiGianDatXe)
        {
            DatChuyen DatChuyen = new DatChuyen();
            DatChuyen.maChuyen=Guid.NewGuid();
            DatChuyen.tkKhach=tkKhach;
            DatChuyen.thoiGianDatXe=thoiGianDatXe;
            DatChuyenService.insertDatChuyen(DatChuyen);
            return Ok(new { status = true, message = "" });
        }

        [HttpGet]
        [Route("Get-DatChuyen-List")]
        public IActionResult GetDatChuyenList()
        {

            List<DatChuyen> DatChuyenList = DatChuyenService.getDatChuyenList();
            return Ok(new { status = true, message = "", data = DatChuyenList });
        }

        [HttpPost]
        [Route("Delete")]
        public IActionResult deleteDatChuyen(Guid maChuyen)
        {
            DatChuyenService.deleteDatChuyen(maChuyen);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("updateDatChuyen")]
        public IActionResult updateDatChuyen(Guid maChuyen, string tkKhach, DateTime thoiGianDatXe)
        {
            DatChuyen DatChuyen = DatChuyenService.getDatChuyen(maChuyen);
            DatChuyen.tkKhach = tkKhach;
            DatChuyen.thoiGianDatXe = thoiGianDatXe;
            DatChuyenService.updateDatChuyen(DatChuyen);
            return Ok(new { status = true, message = "", data = DatChuyen });

        }
    }
}
