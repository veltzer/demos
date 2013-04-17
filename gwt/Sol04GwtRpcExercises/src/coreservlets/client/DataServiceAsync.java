package coreservlets.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.*;

public interface DataServiceAsync {
  public void getDate(AsyncCallback<Date> callback);
  public void getSum(double d1, double d2, 
                     AsyncCallback<Double> callback);
  public void getCustomerById(String id, AsyncCallback<Customer> callback);
  

}