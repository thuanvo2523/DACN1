using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace lib.Entities
{
    public class ThongTinChuyen
    {
        public ThongTinChuyen()
        {
            this.maChuyen = Guid.Empty;
            this.diemBatDau = string.Empty;
            this.diemDen = string.Empty;
            this.soKm = 0;
            this.soTien = 0;
        }
        public ThongTinChuyen(Guid maChuyen, string diemBatDau, string diemDen, float soKm, decimal soTien)
        {
            this.maChuyen = maChuyen;
            this.diemBatDau = diemBatDau;
            this.diemDen = diemDen;
            this.soKm = soKm;
            this.soTien = soTien;
        }
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid maChuyen {  get; set; }
        public string diemBatDau {  get; set; }
        public string diemDen { get; set; }
        public float soKm{ get; set; }
        public decimal soTien { get; set; }


    }
}
