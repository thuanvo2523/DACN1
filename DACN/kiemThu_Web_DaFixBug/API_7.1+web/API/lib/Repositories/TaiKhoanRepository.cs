using lib.Data;
using lib.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lib.Repositories
{

    public interface ITaiKhoanRepository : IRepository<TaiKhoan>
    {
        public void insertTaiKhoan(TaiKhoan taiKhoan);
        public void deleteTaiKhoan(TaiKhoan taiKhoan);
        public void updateTaiKhoan(TaiKhoan taiKhoan);

        public class TaiKhoanRepository : RepositoryBase<TaiKhoan>, ITaiKhoanRepository
        {
            public TaiKhoanRepository(ApplicationDbContext dbContext) : base(dbContext)
            {
            }
            public void deleteTaiKhoan(TaiKhoan taiKhoan)
            {
                _dbContext.TaiKhoan.Remove(taiKhoan);
            }

            public void insertTaiKhoan(TaiKhoan taiKhoan)
            {
                _dbContext.TaiKhoan.Add(taiKhoan);
            }

            public void updateTaiKhoan(TaiKhoan taiKhoan)
            {
                _dbContext.TaiKhoan.Update(taiKhoan);
            }
        }
    }
}
