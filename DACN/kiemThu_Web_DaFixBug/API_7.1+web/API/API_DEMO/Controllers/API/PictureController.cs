using lib.Entities;
using lib.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Reflection;

namespace API_DEMO.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class PictureController : ControllerBase
    {
        private PictureService PictureService;

        public PictureController(PictureService PictureService)
        {
            this.PictureService = PictureService;
        }
        [HttpPost]
        [Route("insertPicture")]
        public IActionResult insertPicture( byte[] picture)
        {
            Picture Picture = new Picture();
           Picture.picture = picture;
            PictureService.insertPicture(Picture);
            return Ok(new { status = true, message = "" });
        }

        [HttpGet]
        [Route("Get-Picture-List")]
        public IActionResult GetPictureList()
        {

            List<Picture> PictureList = PictureService.getPicturesList();
            return Ok(new { status = true, message = "", data = PictureList });
        }

        [HttpPost]
        [Route("Delete")]
        public IActionResult deletePicture(Guid Id)
        {
            PictureService.deletePicture(Id);
            return Ok(new { status = true, message = "" });
        }

        [HttpPost]
        [Route("updatePicture")]
        public IActionResult updatePicture(Guid id, byte[] picture)
        {
            Picture Picture = PictureService.getPictures(id);
           
            Picture.picture = picture;
              
            PictureService.updatePicture(Picture);
            return Ok(new { status = true, message = "", data = Picture });

        }
    }
}
