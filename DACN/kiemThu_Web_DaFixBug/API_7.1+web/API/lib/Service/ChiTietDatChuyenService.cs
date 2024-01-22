using lib.Entities;
using lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static lib.Repositories.IChiTietDatChuyenRepository;
using static lib.Repositories.IChiTietDatChuyenRepository;

namespace lib.Service
{
    public class ChiTietDatChuyenService
    {
        private ApplicationDbContext applicationDbContext;
        private ChiTietDatChuyenRepository ChiTietDatChuyenRepository;

        public ChiTietDatChuyenService(ApplicationDbContext applicationDbContext)
        {
            this.applicationDbContext = applicationDbContext;
            this.ChiTietDatChuyenRepository = new ChiTietDatChuyenRepository(applicationDbContext);
        }
        public void Save()
        {
            this.applicationDbContext.SaveChanges();
        }
        public void insertChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen)
        {
            ChiTietDatChuyenRepository.insertChiTietDatChuyen(ChiTietDatChuyen);
            Save();
        }
        public List<ChiTietDatChuyen> getChiTietDatChuyenList()
        {
            return applicationDbContext.ChiTietDatChuyen.ToList();
        }
        public ChiTietDatChuyen getChiTietDatChuyen(Guid maChiTiet)
        {
            return applicationDbContext.ChiTietDatChuyen.FirstOrDefault(x => x.maChiTiet == maChiTiet);
        }
        public void deleteChiTietDatChuyen(Guid maChiTiet)
        {
            ChiTietDatChuyen tam = applicationDbContext.ChiTietDatChuyen.FirstOrDefault(x => x.maChiTiet == maChiTiet);
            ChiTietDatChuyenRepository.deleteChiTietDatChuyen(tam);
            Save();
        }

        public void updateChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen)
        {
            ChiTietDatChuyen tam = applicationDbContext.ChiTietDatChuyen.FirstOrDefault(x => x.maChiTiet == ChiTietDatChuyen.maChiTiet);
            ChiTietDatChuyenRepository.updateChiTietDatChuyen(tam);
            Save();

        }
    }
}
