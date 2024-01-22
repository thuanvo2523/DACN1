using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Twilio;
using Twilio.Rest.Api.V2010.Account;
using Twilio.Types;

namespace ConsoleApp1
{
    using System;
    using Twilio;
    using Twilio.Rest.Verify.V2;


    class Program
    {
        static void Main(string[] args)
        {
            // Đảm bảo bạn đã đặt giá trị thực sự cho các biến accountSid và authToken.
            string accountSid = "ACfd2340b66ac70dec63d03050a2fe1aa0";
            string authToken = "a5270355d18174bcce1d7f7545906128";

            TwilioClient.Init(accountSid, authToken);

            var service = ServiceResource.Create(friendlyName: "My First Verify Service");

            Console.WriteLine(service.Sid);
        }
    }
}
