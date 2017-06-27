/**
 * EtrackInterfaceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lantone.icss.trans.yiqian.webservicetest;

public interface EtrackInterfaceSoap extends java.rmi.Remote {
    public int etrack_ProcInterface(java.lang.String content, javax.xml.rpc.holders.StringHolder retXml) throws java.rmi.RemoteException;
    public java.lang.String funMain(java.lang.String content) throws java.rmi.RemoteException;
    public java.lang.String process(java.lang.String content) throws java.rmi.RemoteException;
    public java.lang.String provInsureTrade(java.lang.String content) throws java.rmi.RemoteException;
    public int runService(java.lang.String tradeType, java.lang.String tradeMsg, javax.xml.rpc.holders.StringHolder tradeMsgOut) throws java.rmi.RemoteException;
    public int assistTreat(java.lang.String tradeMsg, javax.xml.rpc.holders.StringHolder tradeMsgOut) throws java.rmi.RemoteException;
}
