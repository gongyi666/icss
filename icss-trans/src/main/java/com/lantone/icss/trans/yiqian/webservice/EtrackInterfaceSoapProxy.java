package com.lantone.icss.trans.yiqian.webservice;

public class EtrackInterfaceSoapProxy implements com.lantone.icss.trans.yiqian.webservice.EtrackInterfaceSoap {
  private String _endpoint = null;
  private com.lantone.icss.trans.yiqian.webservice.EtrackInterfaceSoap etrackInterfaceSoap = null;
  
  public EtrackInterfaceSoapProxy() {
    _initEtrackInterfaceSoapProxy();
  }
  
  public EtrackInterfaceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initEtrackInterfaceSoapProxy();
  }
  
  private void _initEtrackInterfaceSoapProxy() {
    try {
      etrackInterfaceSoap = (new com.lantone.icss.trans.yiqian.webservice.EtrackInterfaceLocator()).getEtrackInterfaceSoap();
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
  
  public com.lantone.icss.trans.yiqian.webservice.EtrackInterfaceSoap getEtrackInterfaceSoap() {
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap;
  }
  
  @Override
public int etrack_Interface(java.lang.String content, int isTable, javax.xml.rpc.holders.StringHolder msg) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.etrack_Interface(content, isTable, msg);
  }
  
  @Override
public int etrack_ProcInterface(java.lang.String content, javax.xml.rpc.holders.StringHolder retXml) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.etrack_ProcInterface(content, retXml);
  }
  
  @Override
public java.lang.String[] updateConfigParser(java.lang.String node, java.lang.String nodeAttr) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.updateConfigParser(node, nodeAttr);
  }
  
  @Override
public boolean isFilesExist(java.lang.String[] list) throws java.rmi.RemoteException{
    if (etrackInterfaceSoap == null)
      _initEtrackInterfaceSoapProxy();
    return etrackInterfaceSoap.isFilesExist(list);
  }
  
  
}