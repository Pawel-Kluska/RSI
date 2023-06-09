using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace WcfServiceLibrary
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, 
        ConcurrencyMode = ConcurrencyMode.Multiple, UseSynchronizationContext = false)]

    public class MyCalculator : ICalculator
    {
        public int iAdd(int n1, int n2)
        {
            checked // kontrola przepełnienia
            {
                return n1 + n2;
            }
        }

        public int iSub(int n1, int n2)
        {
            checked // kontrola przepełnienia
            {
                return n1 - n2;
            }
        }

        public int iMul(int n1, int n2)
        {
            checked // kontrola przepełnienia
            {
                return n1 * n2;
            }
        }

        public int iDiv(int n1, int n2)
        {
            if (n2 == 0)
            {
                throw new FaultException<DivideByZeroException>(new DivideByZeroException(), "Cannot divide by zero");
            }
            return n1 / n2;
        }

        public int iMod(int n1, int n2)
        {
            if (n2 == 0)
            {
                throw new ArgumentException("Cannot modulo by zero");
            }
            return n1 % n2;
        }

        async public Task<(int count, int maxPrime)> iCountPrimes(int n1, int n2)
        {
                int count = 0;
                int maxPrime = 0;

             for (int i = n1; i <= n2; i++)
                {
                    bool isPrime = true;

                    for (int j = 2; j <= Math.Sqrt(i); j++)
                    {
                        if (i % j == 0)
                        {
                            isPrime = false;
                            break;
                        }
                    }

                    if (isPrime && i > 1)
                    {
                        count++;
                        maxPrime = i;
                    }
                }


                return (count, maxPrime);
            }
    }
}
