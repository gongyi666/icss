/**
 * EtrackInterfaceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lantone.icss.trans.yiqian.webservice;

public interface EtrackInterfaceSoap extends java.rmi.Remote {
    public int etrack_Interface(java.lang.String content, int isTable, javax.xml.rpc.holders.StringHolder msg) throws java.rmi.RemoteException;
    public int etrack_ProcInterface(java.lang.String content, javax.xml.rpc.holders.StringHolder retXml) throws java.rmi.RemoteException;
    public java.lang.String[] updateConfigParser(java.lang.String node, java.lang.String nodeAttr) throws java.rmi.RemoteException;
    public boolean isFilesExist(java.lang.String[] list) throws java.rmi.RemoteException;
}
