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
    public class TaiXe
    {
        public TaiXe(string taiKhoan, string moTa, string thongTinXe, string bienSo)
        {
            this.taiKhoan = taiKhoan;
            this.moTa = moTa;
            this.hoatDong = false;
            this.thongTinXe = thongTinXe;
            this.bienSo = bienSo;
            this.hinhBangLai = null;
            this.hinhCMND = null;
            this.hinhXe = null;
            this.hinhGiayToXe = null;
        }
        public TaiXe()
        {
            this.taiKhoan = string.Empty;
            this.moTa = string.Empty;
            this.hoatDong = false;
            this.thongTinXe = string.Empty;
            this.bienSo = string.Empty;
            this.hinhBangLai = null;
            this.hinhCMND = null;
            this.hinhXe = null;
            this.hinhGiayToXe = null;
        }

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public string taiKhoan { get; set; }
        public string? moTa { get; set; }
        public Boolean? hoatDong {  get; set; }
        public string? thongTinXe { get; set; }
        public string? bienSo { get; set; }

        public byte[]? hinhBangLai { get; set; }
        public byte[]? hinhCMND { get; set; }
        public byte[]? hinhXe { get; set; }
        public byte[]? hinhGiayToXe { get; set; }


    }
}
