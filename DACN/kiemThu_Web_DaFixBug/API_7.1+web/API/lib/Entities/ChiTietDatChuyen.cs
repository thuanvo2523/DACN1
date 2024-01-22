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
    public class ChiTietDatChuyen
    {
        public ChiTietDatChuyen(Guid maChiTiet, string diemBatDau, string diemDen, float soKm, decimal soTien, string tkTaiXe)
        {
            this.maChiTiet = maChiTiet;
            this.diemBatDau = diemBatDau;
            this.diemDen = diemDen;
            this.soKm = soKm;
            this.soTien = soTien;
            this.tkTaiXe = tkTaiXe;
        }
        public ChiTietDatChuyen()
        {
            this.maChiTiet = Guid.Empty;
            this.diemBatDau = string.Empty;
            this.diemDen = string.Empty;
            this.soKm = 0;
            this.soTien = 0;
            this.tkTaiXe = string.Empty;
        }
        [Key]
        public Guid maChiTiet { get; set; }
        public string diemBatDau { get; set; }
        public string diemDen { get; set; }
        public float soKm { get; set; }
        public decimal soTien { get; set; }
        public string tkTaiXe { get; set; }

    }
}
