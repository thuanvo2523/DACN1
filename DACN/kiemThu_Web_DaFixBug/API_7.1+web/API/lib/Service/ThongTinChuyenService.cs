using lib.Entities;
using lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static lib.Repositories.IThongTinChuyenRepository;
using static lib.Repositories.IThongTinChuyenRepository;

namespace lib.Service
{
    public class ThongTinChuyenService
    {
        private ApplicationDbContext applicationDbContext;
        private ThongTinChuyenRepository ThongTinChuyenRepository;

        public ThongTinChuyenService(ApplicationDbContext applicationDbContext)
        {
            this.applicationDbContext = applicationDbContext;
            this.ThongTinChuyenRepository = new ThongTinChuyenRepository(applicationDbContext);
        }
        public void Save()
        {
            this.applicationDbContext.SaveChanges();
        }
        public void insertThongTinChuyen(ThongTinChuyen thongTinChuyen)
        {
            ThongTinChuyenRepository.insertThongTinChuyen(thongTinChuyen);
            Save();
        }
        public List<ThongTinChuyen> getThongTinChuyenList()
        {
            return applicationDbContext.ThongTinChuyen.ToList();
        }
        public ThongTinChuyen getThongTinChuyen(Guid maChuyen)
        {
            return applicationDbContext.ThongTinChuyen.FirstOrDefault(x => x.maChuyen == maChuyen);
        }
        public void deleteThongTinChuyen(Guid maChuyen)
        {
            ThongTinChuyen tam = applicationDbContext.ThongTinChuyen.FirstOrDefault(x => x.maChuyen == maChuyen);
            ThongTinChuyenRepository.deleteThongTinChuyen(tam);
            Save();
        }

        public void updateThongTinChuyen(ThongTinChuyen thongTinChuyen)
        {
            ThongTinChuyen tam = applicationDbContext.ThongTinChuyen.FirstOrDefault(x => x.maChuyen == thongTinChuyen.maChuyen);
            ThongTinChuyenRepository.updateThongTinChuyen(tam);
            Save();

        }
    }
}
