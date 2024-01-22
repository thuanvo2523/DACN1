using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lib.Entities
{
    public class TaiKhoan
    {
        public TaiKhoan()
        {
            this.taiKhoan = string.Empty;
            this.matKhau = string.Empty;
            this.ten = string.Empty;
            this.avatar = null;
            this.moMo = string.Empty;
            this.soDienThoai = string.Empty;
            this.diaChi = string.Empty;
            this.otp = string.Empty;
        }
        public TaiKhoan(string taiKhoan, string matKhau, string ten, byte[] avatar, string moMo, string soDienThoai, string diaChi, string otp)
        {
            this.taiKhoan = taiKhoan;
            this.matKhau = matKhau;
            this.ten = ten;
            this.avatar = avatar;
            this.moMo = moMo;
            this.soDienThoai = soDienThoai;
            this.diaChi = diaChi;
            this.otp = otp;
        }

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public string taiKhoan { get; set; }
        public string matKhau { get; set; }
        public string? ten { get; set; }
        public byte[]? avatar {  get; set; }
        public string? moMo { get; set; }
        public string? soDienThoai { get; set; }
        public string? diaChi { get; set; }
        public string? otp {  get; set; }
    }
}
