package com.lantone.icss.trans.chuangye.webservicetest;

public class CYInterfaceSoapProxy implements com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap {
  private String _endpoint = null;
  private com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap CYInterfaceSoap = null;
  
  public CYInterfaceSoapProxy() {
    _initCYInterfaceSoapProxy();
  }
  
  public CYInterfaceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCYInterfaceSoapProxy();
  }
  
  private void _initCYInterfaceSoapProxy() {
    try {
      CYInterfaceSoap = (new com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceLocator()).getCYInterfaceSoap();
      if (CYInterfaceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)CYInterfaceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)CYInterfaceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (CYInterfaceSoap != null)
      ((javax.xml.rpc.Stub)CYInterfaceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap getCYInterfaceSoap() {
    if (CYInterfaceSoap == null)
      _initCYInterfaceSoapProxy();
    return CYInterfaceSoap;
  }
  
  @Override
public int CY_ProcInterface(java.lang.String content, javax.xml.rpc.holders.StringHolder retXml) throws java.rmi.RemoteException{
    if (CYInterfaceSoap == null)
      _initCYInterfaceSoapProxy();
    return CYInterfaceSoap.CY_ProcInterface(content, retXml);
  }
  
  @Override
public java.lang.String funMain(java.lang.String content) throws java.rmi.RemoteException{
    if (CYInterfaceSoap == null)
      _initCYInterfaceSoapProxy();
    return CYInterfaceSoap.funMain(content);
  }
  
  @Override
public java.lang.String process(java.lang.String content) throws java.rmi.RemoteException{
    if (CYInterfaceSoap == null)
      _initCYInterfaceSoapProxy();
    return CYInterfaceSoap.process(content);
  }
  
  @Override
public java.lang.String provInsureTrade(java.lang.String content) throws java.rmi.RemoteException{
    if (CYInterfaceSoap == null)
      _initCYInterfaceSoapProxy();
    return CYInterfaceSoap.provInsureTrade(content);
  }
  
  @Override
public int runService(java.lang.String tradeType, java.lang.String tradeMsg, javax.xml.rpc.holders.StringHolder tradeMsgOut) throws java.rmi.RemoteException{
    if (CYInterfaceSoap == null)
      _initCYInterfaceSoapProxy();
    return CYInterfaceSoap.runService(tradeType, tradeMsg, tradeMsgOut);
  }
  
  @Override
public int assistTreat(java.lang.String tradeMsg, javax.xml.rpc.holders.StringHolder tradeMsgOut) throws java.rmi.RemoteException{
    if (CYInterfaceSoap == null)
      _initCYInterfaceSoapProxy();
    return CYInterfaceSoap.assistTreat(tradeMsg, tradeMsgOut);
  }
  
  
}