using API_DEMO.Controllers.API;
using lib.Entities;
using lib.Service;
using NUnit.Framework;

namespace API_DEMO
{

    [TestFixture]

    public class unit
    {
        private TaiKhoanService TaiKhoanService;
        [Test]
        public void Test_DN()
        {
            TaiKhoanController temp = new TaiKhoanController(TaiKhoanService);
            String ketqua= temp.dangNhapTaiKhoan2("thuanvo234","12345aA@");
            Assert.That(temp,Is.EqualTo(ketqua));
        }
    }
}
