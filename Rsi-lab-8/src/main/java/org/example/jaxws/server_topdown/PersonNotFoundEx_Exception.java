
package org.example.jaxws.server_topdown;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.2
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PersonNotFoundEx", targetNamespace = "http://server.jaxws.example.org/")
public class PersonNotFoundEx_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PersonNotFoundEx faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PersonNotFoundEx_Exception(String message, PersonNotFoundEx faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public PersonNotFoundEx_Exception(String message, PersonNotFoundEx faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.example.jaxws.server_topdown.PersonNotFoundEx
     */
    public PersonNotFoundEx getFaultInfo() {
        return faultInfo;
    }

}
