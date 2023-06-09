using System;
using System.Collections.Generic;
using System.Linq;
using System.Resources;
using System.ServiceModel;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using WcfClient.ServiceReference1;

namespace WcfClient
{
    internal class Program
    {
        static void Main(string[] args)
        {
            MyData.info();
            Console.WriteLine("... The client is started");
            // Step 1: Create client proxy based on communication channel.
            // base address:
            Uri baseAddress;
            // binding, address, endpoint address:
            BasicHttpBinding myBinding = new BasicHttpBinding();
            baseAddress = new
            Uri("http://localhost:8080/ServiceBaseName/endpoint1");
            EndpointAddress eAddress = new EndpointAddress(baseAddress);
            // channel factory:
            ChannelFactory<ICalculator> myCF = new
            ChannelFactory<ICalculator>(myBinding, eAddress);
            // client proxy (here myClient) based on channel
            ICalculator myClient = myCF.CreateChannel();



            bool exit = false;
            while (!exit)
            {
                Console.WriteLine("Calculator Menu:");
                Console.WriteLine("1. Add");
                Console.WriteLine("2. Subtract");
                Console.WriteLine("3. Multiply");
                Console.WriteLine("4. Divide");
                Console.WriteLine("5. Modulo");
                Console.WriteLine("6. Prime");
                Console.WriteLine("7. Exit");
                Console.Write("Enter your choice: ");

                try
                {
                    string choice = Console.ReadLine();
                    switch (choice)
                    {
                        case "1":
                            Console.Write("...calling Add (for entpoint1) ");
                            Console.Write("Enter first number: ");
                            int x = int.Parse(Console.ReadLine());
                            Console.Write("Enter second number: ");
                            int y = int.Parse(Console.ReadLine());
                            Console.WriteLine("Result: {0}", myClient.iAdd(x, y));
                            break;
                        case "2":
                            Console.Write("Enter first number: ");
                            int x1 = int.Parse(Console.ReadLine());
                            Console.Write("Enter second number: ");
                            int y1 = int.Parse(Console.ReadLine());
                            Console.WriteLine("Result: {0}", myClient.iSub(x1, y1));
                            break;
                        case "3":
                            CalculatorClient myClient2 = new CalculatorClient("WSHttpBinding_ICalculator");
                            Console.Write("...calling Multiply (for endpoint2) - ");
                            Console.Write("Enter first number: ");
                            int x2 = int.Parse(Console.ReadLine());
                            Console.Write("Enter second number: ");
                            int y2 = int.Parse(Console.ReadLine());
                            Console.WriteLine("Result: {0}", myClient2.iMul(x2, y2));
                            break;
                        case "4":
                            Console.Write("Enter first number: ");
                            int x3 = int.Parse(Console.ReadLine());
                            Console.Write("Enter second number: ");
                            int y3 = int.Parse(Console.ReadLine());
                            Console.WriteLine("Result: {0}", myClient.iDiv(x3, y3));
                            break;
                        case "5":
                            Console.Write("Enter first number: ");
                            int x4 = int.Parse(Console.ReadLine());
                            Console.Write("Enter second number: ");
                            int y4 = int.Parse(Console.ReadLine());
                            Console.WriteLine("Result: {0}", myClient.iMod(x4, y4));
                            break;
                        case "6":
                            Console.Write("Enter first number: ");
                            int x5 = int.Parse(Console.ReadLine());
                            Console.Write("Enter second number: ");
                            int y5 = int.Parse(Console.ReadLine());
                            Console.WriteLine("...calling HMultiply ASYNCHRONOUSLY !!!");
                            Task<(int count, int maxPrime)> asyResult = callHMultiplyAsync(x5, y5, myClient);

                            new Thread(() =>
                            {
                                Thread.CurrentThread.IsBackground = true;
                                var result = asyResult.Result;
                                Console.WriteLine("Ilość liczb pierwszych: " + result.count);
                                Console.WriteLine("Największa liczba pierwsza " + result.maxPrime);
                                Console.WriteLine("...HMultiplyAsync Result = \n" + asyResult.Result);
                            }).Start();

                            break;
                        case "7":
                            exit = true;
                            break;
                        default:
                            Console.WriteLine("Invalid choice. Please try again.");
                            break;
                    }
                }
                catch (FaultException<DivideByZeroException> ex)
                {
                    Console.WriteLine(ex.Detail.Message);
                }
                catch (FaultException<OverflowException> ex)
                {
                    Console.WriteLine(ex.Detail.Message);
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }

        static async Task<(int count, int maxPrime)> callHMultiplyAsync(int n1, int n2, ICalculator myClient2)
        {
            Console.WriteLine("......called callHMultiplyAsync");
            var reply = await myClient2.iCountPrimes(n1, n2);
            Console.WriteLine("......finished HMultipleAsync");
            return reply;
        }
           
    }
}
