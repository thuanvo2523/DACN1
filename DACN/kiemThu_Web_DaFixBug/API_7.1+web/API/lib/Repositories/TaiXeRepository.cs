using lib.Data;
using lib.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lib.Repositories
{

    public interface ITaiXeRepository : IRepository<TaiXe>
    {
        public void insertTaiXe(TaiXe taiXe);
        public void deleteTaiXe(TaiXe taiXe);
        public void updateTaiXe(TaiXe taiXe);

        public class TaiXeRepository : RepositoryBase<TaiXe>, ITaiXeRepository
        {
            public TaiXeRepository(ApplicationDbContext dbContext) : base(dbContext)
            {
            }
            public void deleteTaiXe(TaiXe taiXe)
            {
                _dbContext.TaiXe.Remove(taiXe);
            }

            public void insertTaiXe(TaiXe taiXe)
            {
                _dbContext.TaiXe.Add(taiXe);
            }

            public void updateTaiXe(TaiXe taiXe)
            {
                _dbContext.TaiXe.Update(taiXe);
            }
        }
    }
}
