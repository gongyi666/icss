package com.lantone.icss.trans.shaoyifu.webservice;

public class ServiceAlipaySoapProxy implements com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoap {
  private String _endpoint = null;
  private com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoap serviceAlipaySoap = null;
  
  public ServiceAlipaySoapProxy() {
    _initServiceAlipaySoapProxy();
  }
  
  public ServiceAlipaySoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceAlipaySoapProxy();
  }
  
  private void _initServiceAlipaySoapProxy() {
    try {
      serviceAlipaySoap = (new com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipayLocator()).getServiceAlipaySoap();
      if (serviceAlipaySoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceAlipaySoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceAlipaySoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceAlipaySoap != null)
      ((javax.xml.rpc.Stub)serviceAlipaySoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoap getServiceAlipaySoap() {
    if (serviceAlipaySoap == null)
      _initServiceAlipaySoapProxy();
    return serviceAlipaySoap;
  }
  
  public java.lang.String apply(java.lang.String _Input) throws java.rmi.RemoteException{
    if (serviceAlipaySoap == null)
      _initServiceAlipaySoapProxy();
    return serviceAlipaySoap.apply(_Input);
  }
  
  public java.lang.String getInsuRate(java.lang.String _ItemCode, java.lang.String _ItemType, java.lang.String _Rylb, java.lang.String _Jzlx, java.lang.String _Ffbz, java.lang.String _Bzbm) throws java.rmi.RemoteException{
    if (serviceAlipaySoap == null)
      _initServiceAlipaySoapProxy();
    return serviceAlipaySoap.getInsuRate(_ItemCode, _ItemType, _Rylb, _Jzlx, _Ffbz, _Bzbm);
  }
  
  
}