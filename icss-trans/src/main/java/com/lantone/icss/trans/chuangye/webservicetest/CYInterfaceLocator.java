/**
 * CYInterfaceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lantone.icss.trans.chuangye.webservicetest;

public class CYInterfaceLocator extends org.apache.axis.client.Service implements com.lantone.icss.trans.chuangye.webservicetest.CYInterface {

    public CYInterfaceLocator() {
    }


    public CYInterfaceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CYInterfaceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CYInterfaceSoap
    private java.lang.String CYInterfaceSoap_address = "http://localhost:8081/CYInterface.asmx";

    @Override
	public java.lang.String getCYInterfaceSoapAddress() {
        return CYInterfaceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CYInterfaceSoapWSDDServiceName = "CYInterfaceSoap";

    public java.lang.String getCYInterfaceSoapWSDDServiceName() {
        return CYInterfaceSoapWSDDServiceName;
    }

    public void setCYInterfaceSoapWSDDServiceName(java.lang.String name) {
        CYInterfaceSoapWSDDServiceName = name;
    }

    @Override
	public com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap getCYInterfaceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CYInterfaceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCYInterfaceSoap(endpoint);
    }

    @Override
	public com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap getCYInterfaceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoapStub _stub = new com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoapStub(portAddress, this);
            _stub.setPortName(getCYInterfaceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCYInterfaceSoapEndpointAddress(java.lang.String address) {
        CYInterfaceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoapStub _stub = new com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoapStub(new java.net.URL(CYInterfaceSoap_address), this);
                _stub.setPortName(getCYInterfaceSoapWSDDServiceName());
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
    @Override
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CYInterfaceSoap".equals(inputPortName)) {
            return getCYInterfaceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
	public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "CYInterface");
    }

    private java.util.HashSet ports = null;

    @Override
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CYInterfaceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CYInterfaceSoap".equals(portName)) {
            setCYInterfaceSoapEndpointAddress(address);
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
