using lib.Data;
using lib.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lib.Repositories
{

    public interface IDatChuyenRepository : IRepository<DatChuyen>
    {
        public void insertDatChuyen(DatChuyen DatChuyen);
        public void deleteDatChuyen(DatChuyen DatChuyen);
        public void updateDatChuyen(DatChuyen DatChuyen);

        public class DatChuyenRepository : RepositoryBase<DatChuyen>, IDatChuyenRepository
        {
            public DatChuyenRepository(ApplicationDbContext dbContext) : base(dbContext)
            {
            }
            public void deleteDatChuyen(DatChuyen DatChuyen)
            {
                _dbContext.DatChuyen.Remove(DatChuyen);
            }

            public void insertDatChuyen(DatChuyen DatChuyen)
            {
                _dbContext.DatChuyen.Add(DatChuyen);
            }

            public void updateDatChuyen(DatChuyen DatChuyen)
            {
                _dbContext.DatChuyen.Update(DatChuyen);
            }
        }
    }
}
