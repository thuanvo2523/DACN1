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
    public class DatChuyen
    {
        public DatChuyen(Guid maChuyen, string tkKhach, DateTime thoiGianDatXe)
        {
            this.maChuyen = maChuyen;
            this.tkKhach = tkKhach;
            this.thoiGianDatXe = thoiGianDatXe;
        }
        public DatChuyen()
        {
            this.maChuyen = Guid.Empty;
            this.tkKhach = string.Empty;
            this.thoiGianDatXe = DateTime.Now;
        }
        [Key]
        public Guid maChuyen { get; set; }
        public string tkKhach { get; set; }
        public DateTime thoiGianDatXe { get; set; }

    }
}
