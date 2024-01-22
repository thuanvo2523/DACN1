using lib.Data;
using lib.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lib.Repositories
{

    public interface IChiTietDatChuyenRepository : IRepository<ChiTietDatChuyen>
    {
        public void insertChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen);
        public void deleteChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen);
        public void updateChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen);

        public class ChiTietDatChuyenRepository : RepositoryBase<ChiTietDatChuyen>, IChiTietDatChuyenRepository
        {
            public ChiTietDatChuyenRepository(ApplicationDbContext dbContext) : base(dbContext)
            {
            }
            public void deleteChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen)
            {
                _dbContext.ChiTietDatChuyen.Remove(ChiTietDatChuyen);
            }

            public void insertChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen)
            {
                _dbContext.ChiTietDatChuyen.Add(ChiTietDatChuyen);
            }

            public void updateChiTietDatChuyen(ChiTietDatChuyen ChiTietDatChuyen)
            {
                _dbContext.ChiTietDatChuyen.Update(ChiTietDatChuyen);
            }
        }
    }
}
