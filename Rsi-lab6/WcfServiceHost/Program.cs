﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel.Description;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using WcfServiceLibrary;

namespace WcfServiceHost
{
    internal class Program
    {
        static void Main(string[] args)
        {
            MyData.info();
            // Krok 1 URI dla bazowego adresu serwisu
            Uri baseAddress = new Uri("http://localhost:8080/ServiceBaseName");
            // Krok 2 Instancja serwisu
            ServiceHost myHost = new
            ServiceHost(typeof(MyCalculator), baseAddress);
            // Krok 3 Endpoint serwisu
            BasicHttpBinding myBinding = new BasicHttpBinding();
            ServiceEndpoint endpoint1 = myHost.AddServiceEndpoint(
            typeof(ICalculator), myBinding,"endpoint1");

            WSHttpBinding binding2 = new WSHttpBinding();
            binding2.Security.Mode = SecurityMode.None;
            ServiceEndpoint endpoint2 = myHost.AddServiceEndpoint(
            typeof(ICalculator),
            binding2, "endpoint2");

            // Krok 4 Ustawienie metadanych
            ServiceMetadataBehavior smb = new ServiceMetadataBehavior();
            smb.HttpGetEnabled = true;
            myHost.Description.Behaviors.Add(smb);
            try
            {
                // Krok 5 Uruchomienie serwisu.
                myHost.Open();
                Console.WriteLine("Service is started and running.");
                Console.WriteLine("\n---> Endpoints:");
                Console.WriteLine("\nService endpoint {0}:", endpoint1.Name);
                Console.WriteLine("Binding: {0}", endpoint1.Binding.ToString());
                Console.WriteLine("ListenUri: {0}", endpoint1.ListenUri.ToString());
                Console.WriteLine("Press <ENTER> to STOP service...");
                Console.WriteLine();
                Console.ReadLine(); // aby nie kończyć natychmiast:
                myHost.Close();
            }
            catch (CommunicationException ce)
            {
                Console.WriteLine("Exception occured: {0}", ce.Message);
                myHost.Abort();
            }
        }
    }
}
