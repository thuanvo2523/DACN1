using lib.Entities;
using lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static lib.Repositories.IPictureRepository;
using static lib.Repositories.IPictureRepository;

namespace lib.Service
{
    public class PictureService
    {
        private ApplicationDbContext applicationDbContext;
        private PictureRepository PictureRepository;

        public PictureService(ApplicationDbContext applicationDbContext)
        {
            this.applicationDbContext = applicationDbContext;
            this.PictureRepository = new PictureRepository(applicationDbContext);
        }
        public void Save()
        {
            this.applicationDbContext.SaveChanges();
        }
        public void insertPicture(Picture Pictures)
        {
            PictureRepository.insertPicture(Pictures);
            Save();
        }
        public List<Picture> getPicturesList()
        {
            return applicationDbContext.Picture.ToList();
        }
        public Picture getPictures(Guid id)
        {
            return applicationDbContext.Picture.FirstOrDefault(x => x.Id == id);
        }
        public void deletePicture(Guid id)
        {
            Picture tam = applicationDbContext.Picture.FirstOrDefault(x => x.Id == id);
            PictureRepository.deletePicture(tam);
            Save();
        }

        public void updatePicture(Picture Pictures)
        {
            Picture tam = applicationDbContext.Picture.FirstOrDefault(x => x.Id == Pictures.Id);
            PictureRepository.updatePicture(tam);
            Save();

        }
    }
}
