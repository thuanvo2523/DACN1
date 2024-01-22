using lib.Entities;
using lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static lib.Repositories.ITaiXeRepository;
using static lib.Repositories.ITaiXeRepository;

namespace lib.Service
{
    public class TaiXeService
    {
        private ApplicationDbContext applicationDbContext;
        private TaiXeRepository taiXeRepository;

        public TaiXeService(ApplicationDbContext applicationDbContext)
        {
            this.applicationDbContext = applicationDbContext;
            this.taiXeRepository = new TaiXeRepository(applicationDbContext);
        }
        public void Save()
        {
            this.applicationDbContext.SaveChanges();
        }
        public void insertTaiXe(TaiXe TaiXes)
        {
            taiXeRepository.insertTaiXe(TaiXes);
            Save();
        }
        public List<TaiXe> getTaiXesList()
        {
            return applicationDbContext.TaiXe.ToList();
        }
        public TaiXe getTaiXes(string taiKhoan)
        {
            return applicationDbContext.TaiXe.FirstOrDefault(x => x.taiKhoan == taiKhoan);
        }
        public void deleteTaiXe(string taiKhoan)
        {
            TaiXe tam = applicationDbContext.TaiXe.FirstOrDefault(x => x.taiKhoan == taiKhoan);
            taiXeRepository.deleteTaiXe(tam);
            Save();
        }

        public void updateTaiXe(TaiXe TaiXes)
        {
            TaiXe tam = applicationDbContext.TaiXe.FirstOrDefault(x => x.taiKhoan == TaiXes.taiKhoan);
            taiXeRepository.updateTaiXe(tam);
            Save();
        }

        public List<TaiKhoan> getListTaiXeHoatDong()
        {
            List<TaiXe> ListTaiXe = applicationDbContext.TaiXe.Where(x => x.hoatDong == true).ToList();
            List<TaiKhoan> ListTaiKhoan = new List<TaiKhoan>();
            foreach (TaiXe temp in ListTaiXe)
            {
                TaiKhoan tempTK = applicationDbContext.TaiKhoan.FirstOrDefault(x => x.taiKhoan == temp.taiKhoan);
                if (tempTK != null)
                {
                    ListTaiKhoan.Add(tempTK);
                }
            }
            return ListTaiKhoan;
        }

    }
}
