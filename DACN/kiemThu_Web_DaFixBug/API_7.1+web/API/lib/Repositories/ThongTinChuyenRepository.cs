using lib.Data;
using lib.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lib.Repositories
{

    public interface IThongTinChuyenRepository : IRepository<ThongTinChuyen>
    {
        public void insertThongTinChuyen(ThongTinChuyen thongTinChuyen);
        public void deleteThongTinChuyen(ThongTinChuyen thongTinChuyen);
        public void updateThongTinChuyen(ThongTinChuyen thongTinChuyen);

        public class ThongTinChuyenRepository : RepositoryBase<ThongTinChuyen>, IThongTinChuyenRepository
        {
            public ThongTinChuyenRepository(ApplicationDbContext dbContext) : base(dbContext)
            {
            }
            public void deleteThongTinChuyen(ThongTinChuyen thongTinChuyen)
            {
                _dbContext.ThongTinChuyen.Remove(thongTinChuyen);
            }

            public void insertThongTinChuyen(ThongTinChuyen thongTinChuyen)
            {
                _dbContext.ThongTinChuyen.Add(thongTinChuyen);
            }

            public void updateThongTinChuyen(ThongTinChuyen thongTinChuyen)
            {
                _dbContext.ThongTinChuyen.Update(thongTinChuyen);
            }
        }
    }
}
