 using lib.Entities;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace lib
{
    public class ApplicationDbContext:IdentityDbContext
    {
        public DbSet<TaiKhoan> TaiKhoan { get; set; }
        public DbSet<TaiXe> TaiXe { get; set; }
        public DbSet<ThongTinChuyen> ThongTinChuyen { get; set; }
        public DbSet<Picture> Picture { get; set; }
        public DbSet<DatChuyen> DatChuyen { get; set; }
        public DbSet<ChiTietDatChuyen> ChiTietDatChuyen { get; set; }
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options) 
        {
        }
    }
}