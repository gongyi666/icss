package com.lantone.icss.trans.yiqian_new.webservicetest;

public class EtrackInterfaceSoapProxy implements com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap {
  private String _endpoint = null;
  private com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap etrackInterfaceSoap = null;
  
  public EtrackInterfaceSoapProxy() {
    _initEtrackInterfaceSoapProxy();
  }
  
  public EtrackInterfaceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initEtrackInterfaceSoapProxy();
  }
  
  private void _initEtrackInterfaceSoapProxy() {
    try {
      etrackInterfaceSoap = (new com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceLocator()).getEtrackInterfaceSoap();
      if (etrackInterfaceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)etrackInterfaceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)etrackInterfaceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (etrackInterfaceSoap != null)
      ((javax.xml.rpc.Stub)etrackInterfaceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap getEtrackInterfaceSoap() {
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap;
  }
  
  @Override
public int etrack_ProcInterface(java.lang.String content, javax.xml.rpc.holders.StringHolder retXml) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.etrack_ProcInterface(content, retXml);
  }
  
  @Override
public java.lang.String funMain(java.lang.String content) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.funMain(content);
  }
  
  @Override
public java.lang.String process(java.lang.String content) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.process(content);
  }
  
  @Override
public java.lang.String provInsureTrade(java.lang.String content) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.provInsureTrade(content);
  }
  
  @Override
public int runService(java.lang.String tradeType, java.lang.String tradeMsg, javax.xml.rpc.holders.StringHolder tradeMsgOut) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.runService(tradeType, tradeMsg, tradeMsgOut);
  }
  
  @Override
public int assistTreat(java.lang.String tradeMsg, javax.xml.rpc.holders.StringHolder tradeMsgOut) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.assistTreat(tradeMsg, tradeMsgOut);
  }
  
  
}