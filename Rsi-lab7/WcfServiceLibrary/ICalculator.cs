using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Security;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace WcfServiceLibrary
{
    [ServiceContract(ProtectionLevel = ProtectionLevel.None)]
    public interface ICalculator
    {

        [OperationContract]
        int iAdd(int n1, int n2);
        [OperationContract]
        int iSub(int n1, int n2);
        [OperationContract]
        int iMul(int n1, int n2);
        [OperationContract]
        int iDiv(int n1, int n2);
        [OperationContract]
        int iMod(int n1, int n2);

        [OperationContract]
        Task<int> iCountPrimes(int n1, int n2);
    }
}
