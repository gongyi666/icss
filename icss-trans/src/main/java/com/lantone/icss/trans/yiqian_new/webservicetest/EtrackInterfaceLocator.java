/**
 * EtrackInterfaceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lantone.icss.trans.yiqian_new.webservicetest;

public class EtrackInterfaceLocator extends org.apache.axis.client.Service implements com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterface {

    public EtrackInterfaceLocator() {
    }


    public EtrackInterfaceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EtrackInterfaceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EtrackInterfaceSoap
    private java.lang.String EtrackInterfaceSoap_address = "http://localhost:8081/EtrackInterface.asmx";

    @Override
	public java.lang.String getEtrackInterfaceSoapAddress() {
        return EtrackInterfaceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EtrackInterfaceSoapWSDDServiceName = "EtrackInterfaceSoap";

    public java.lang.String getEtrackInterfaceSoapWSDDServiceName() {
        return EtrackInterfaceSoapWSDDServiceName;
    }

    public void setEtrackInterfaceSoapWSDDServiceName(java.lang.String name) {
        EtrackInterfaceSoapWSDDServiceName = name;
    }

    @Override
	public com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap getEtrackInterfaceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EtrackInterfaceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEtrackInterfaceSoap(endpoint);
    }

    @Override
	public com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap getEtrackInterfaceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoapStub _stub = new com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoapStub(portAddress, this);
            _stub.setPortName(getEtrackInterfaceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEtrackInterfaceSoapEndpointAddress(java.lang.String address) {
        EtrackInterfaceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoapStub _stub = new com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoapStub(new java.net.URL(EtrackInterfaceSoap_address), this);
                _stub.setPortName(getEtrackInterfaceSoapWSDDServiceName());
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
        if ("EtrackInterfaceSoap".equals(inputPortName)) {
            return getEtrackInterfaceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
	public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "EtrackInterface");
    }

    private java.util.HashSet ports = null;

    @Override
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "EtrackInterfaceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EtrackInterfaceSoap".equals(portName)) {
            setEtrackInterfaceSoapEndpointAddress(address);
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
