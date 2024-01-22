using lib.Entities;
using lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using static lib.Repositories.ITaiKhoanRepository;
using static lib.Repositories.ITaiKhoanRepository;

namespace lib.Service
{
    public class TaiKhoanService
    {
        
        private ApplicationDbContext applicationDbContext;
        private TaiKhoanRepository taiKhoanRepository;

        public TaiKhoanService(ApplicationDbContext applicationDbContext)
        {
            this.applicationDbContext = applicationDbContext;
            this.taiKhoanRepository = new TaiKhoanRepository(applicationDbContext);
        }
        public void Save()
        {
            this.applicationDbContext.SaveChanges();
        }
        public void insertTaiKhoan(TaiKhoan TaiKhoans)
        {
            taiKhoanRepository.insertTaiKhoan(TaiKhoans);
            Save();
        }
        public List<TaiKhoan> getTaiKhoansList()
        {
            return applicationDbContext.TaiKhoan.ToList();
        }
        public TaiKhoan getTaiKhoans(string taiKhoan)
        {
            return applicationDbContext.TaiKhoan.FirstOrDefault(x => x.taiKhoan == taiKhoan);
        }
        public void deleteTaiKhoan(string taiKhoan)
        {
            TaiKhoan tam = applicationDbContext.TaiKhoan.FirstOrDefault(x => x.taiKhoan == taiKhoan);
            taiKhoanRepository.deleteTaiKhoan(tam);
            Save();
        }

        public void updateTaiKhoan(TaiKhoan TaiKhoans)
        {
            TaiKhoan tam = applicationDbContext.TaiKhoan.FirstOrDefault(x => x.taiKhoan == TaiKhoans.taiKhoan);
            taiKhoanRepository.updateTaiKhoan(tam);
            Save();

        }
    }
}
