using lib.Data;
using lib.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lib.Repositories
{

    public interface IPictureRepository : IRepository<Picture>
    {
        public void insertPicture(Picture Picture);
        public void deletePicture(Picture Picture);
        public void updatePicture(Picture Picture);

        public class PictureRepository : RepositoryBase<Picture>, IPictureRepository
        {
            public PictureRepository(ApplicationDbContext dbContext) : base(dbContext)
            {
            }
            public void deletePicture(Picture Picture)
            {
                _dbContext.Picture.Remove(Picture);
            }

            public void insertPicture(Picture Picture)
            {
                _dbContext.Picture.Add(Picture);
            }

            public void updatePicture(Picture Picture)
            {
                _dbContext.Picture.Update(Picture);
            }
        }
    }
}
