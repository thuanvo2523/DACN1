namespace API_DEMO.Models
{
    public class InformationTemp
    {
        public InformationTemp(string taiKhoan, IFormFile imageFile)
        {
            this.taiKhoan = taiKhoan;
            this.imageFile = imageFile;
        }

        public InformationTemp()
        {
            this.taiKhoan = "";
            this.imageFile = null;
        }

        public string taiKhoan { get; set; }
        public IFormFile imageFile { get; set; }

    }
}
