/**
 * ServiceAlipayLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lantone.icss.trans.shaoyifu.webservice;

public class ServiceAlipayLocator extends org.apache.axis.client.Service implements com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipay {

    public ServiceAlipayLocator() {
    }


    public ServiceAlipayLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceAlipayLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServiceAlipaySoap
    private java.lang.String ServiceAlipaySoap_address = "http://192.1.3.160:8211/ServiceAlipay.asmx";

    public java.lang.String getServiceAlipaySoapAddress() {
        return ServiceAlipaySoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceAlipaySoapWSDDServiceName = "ServiceAlipaySoap";

    public java.lang.String getServiceAlipaySoapWSDDServiceName() {
        return ServiceAlipaySoapWSDDServiceName;
    }

    public void setServiceAlipaySoapWSDDServiceName(java.lang.String name) {
        ServiceAlipaySoapWSDDServiceName = name;
    }

    public com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoap getServiceAlipaySoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceAlipaySoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceAlipaySoap(endpoint);
    }

    public com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoap getServiceAlipaySoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoapStub _stub = new com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoapStub(portAddress, this);
            _stub.setPortName(getServiceAlipaySoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceAlipaySoapEndpointAddress(java.lang.String address) {
        ServiceAlipaySoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoapStub _stub = new com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoapStub(new java.net.URL(ServiceAlipaySoap_address), this);
                _stub.setPortName(getServiceAlipaySoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServiceAlipaySoap".equals(inputPortName)) {
            return getServiceAlipaySoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ServiceAlipay");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ServiceAlipaySoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceAlipaySoap".equals(portName)) {
            setServiceAlipaySoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
