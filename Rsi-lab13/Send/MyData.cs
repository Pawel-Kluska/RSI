using System.Globalization;
using System.Net;
using System.Net.Sockets;
using System.Runtime.InteropServices;

class MyData
{
    public static void info()
    {
        Console.WriteLine("Pawel‚ Kluska, 260391");
        Console.WriteLine("Katya Zyatikava, 245891");
        CultureInfo culture = new CultureInfo("PL");
        Console.WriteLine(DateTime.Now.ToString("dd MMMM HH:mm:ss", culture));
        Console.WriteLine(typeof(string).Assembly.ImageRuntimeVersion);
        Console.WriteLine(System.Security.Principal.WindowsIdentity.GetCurrent().Name);
        Console.WriteLine(RuntimeInformation.OSDescription);
        using (Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, 0))
        {
            socket.Connect("8.8.8.8", 65530);
            IPEndPoint endPoint = socket.LocalEndPoint as IPEndPoint;
            Console.WriteLine(endPoint.Address.ToString());
        }
    }
}


