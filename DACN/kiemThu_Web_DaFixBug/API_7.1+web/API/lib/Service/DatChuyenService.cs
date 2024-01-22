using lib.Entities;
using lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static lib.Repositories.IDatChuyenRepository;
using static lib.Repositories.IDatChuyenRepository;

namespace lib.Service
{
    public class DatChuyenService
    {
        private ApplicationDbContext applicationDbContext;
        private DatChuyenRepository DatChuyenRepository;

        public DatChuyenService(ApplicationDbContext applicationDbContext)
        {
            this.applicationDbContext = applicationDbContext;
            this.DatChuyenRepository = new DatChuyenRepository(applicationDbContext);
        }
        public void Save()
        {
            this.applicationDbContext.SaveChanges();
        }
        public void insertDatChuyen(DatChuyen DatChuyen)
        {
            DatChuyenRepository.insertDatChuyen(DatChuyen);
            Save();
        }
        public List<DatChuyen> getDatChuyenList()
        {
            return applicationDbContext.DatChuyen.ToList();
        }
        public DatChuyen getDatChuyen(Guid maChuyen)
        {
            return applicationDbContext.DatChuyen.FirstOrDefault(x => x.maChuyen == maChuyen);
        }
        public void deleteDatChuyen(Guid maChuyen)
        {
            DatChuyen tam = applicationDbContext.DatChuyen.FirstOrDefault(x => x.maChuyen == maChuyen);
            DatChuyenRepository.deleteDatChuyen(tam);
            Save();
        }

        public void updateDatChuyen(DatChuyen DatChuyen)
        {
            DatChuyen tam = applicationDbContext.DatChuyen.FirstOrDefault(x => x.maChuyen == DatChuyen.maChuyen);
            DatChuyenRepository.updateDatChuyen(tam);
            Save();

        }
    }
}
