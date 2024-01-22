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
    public class Picture
    {
        public Picture(Guid id, byte[] picture)
        {
            Id = id;
            this.picture = picture;
        }

        public Picture()
        {
            Id = Guid.Empty;
            this.picture = null;
        }

        public Guid Id { get; set; }
        public byte[] picture { get; set; }
        
    }
}
