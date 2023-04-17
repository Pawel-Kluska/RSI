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
            // Step 2: service operations call.
            Console.Write("...calling Add (for entpoint1) ");
            int result = myClient.iAdd(-3, 9);
            Console.WriteLine("Result = " + result);

            CalculatorClient myClient2 = new
            CalculatorClient("WSHttpBinding_ICalculator");
            Console.Write("...calling Multiply (for endpoint2) - ");
            result = myClient2.iMul(-3, 9);
            Console.WriteLine("Result = " + result);

            Console.WriteLine("...calling HMultiply ASYNCHRONOUSLY !!!");
            Task<int> asyResult = callHMultiplyAsync(1, 100000, myClient);

            Console.Write("...calling Division (for entpoint1) ");
            result = myClient.iDiv(27, 9);
            Console.WriteLine("Result = " + result);

            Console.Write("...calling Division (for entpoint1) ");
            result = myClient.iMod(27, 8);
            Console.WriteLine("Result = " + result);

            exceptionTest(myClient2);

            result = asyResult.Result;
            Console.WriteLine("...HMultiplyAsync Result = " + result);

            Console.WriteLine("...press <ENTER> to STOP client...");
            Console.WriteLine();
            Console.ReadLine();
            ((IClientChannel)myClient).Close();
            Console.WriteLine("...Client closed - FINISHED");
        }

        static async Task<int> callHMultiplyAsync(int n1, int n2, ICalculator myClient2)
        {
            Console.WriteLine("......called callHMultiplyAsync");
            int reply = await myClient2.iCountPrimes(n1, n2);
            Console.WriteLine("......finished HMultipleAsync");
            return reply;
        }

        static void exceptionTest(CalculatorClient client1)
        {
            try
            {
                // int n1 = 2147483647;
                int n1 = 56;
                int n2 = 0;

                int result = client1.iDiv(n1, n2);
                Console.WriteLine($"{n1} / {n2} = {result}");
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
}
