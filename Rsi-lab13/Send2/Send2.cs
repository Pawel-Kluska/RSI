using System;
using System.Text;
using RabbitMQ.Client;
using System.Threading;
using Newtonsoft.Json;

public class Program
{
    private const int DurationSeconds = 12;
    private const string EndMarkerMessage = "EndMarker";


    public static void Main()
    {
        MyData.info();

        var factory = new ConnectionFactory { HostName = "10.182.6.242", Port = 5672, UserName = "guest", Password = "guest" };
        // var factory = new ConnectionFactory { HostName = "localhost" };

        using var connection = factory.CreateConnection();
        using var channel = connection.CreateModel();

        DateTime endTime = DateTime.Now.AddSeconds(DurationSeconds);
        int counter = 0;
        while (DateTime.Now < endTime)
        {

            string message = JsonConvert.SerializeObject(new { name = "Katya", time = DateTime.Now.TimeOfDay, counter = counter++ });

            var body = Encoding.UTF8.GetBytes(message);

            channel.BasicPublish(exchange: "",
                                 routingKey: "01",
                                 basicProperties: null,
                                 body: body);

            Console.WriteLine($" [x] Sent {message}");

            Random rnd = new Random();
            int sleep = rnd.Next(2000, 3000);
            Thread.Sleep(sleep);
        }

        var endMarkerBody = Encoding.UTF8.GetBytes(EndMarkerMessage);
        channel.BasicPublish(exchange: "",
                             routingKey: "01",
                             basicProperties: null,
                             body: endMarkerBody);

        Console.WriteLine($" [x] Sent end marker '{EndMarkerMessage}'");

        Console.WriteLine(" Press [enter] to exit.");
        Console.ReadLine();
    }
}