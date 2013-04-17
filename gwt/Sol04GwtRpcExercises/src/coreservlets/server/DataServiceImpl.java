package coreservlets.server;

import com.google.gwt.user.server.rpc.*;
import java.util.*;
import coreservlets.client.*;

@SuppressWarnings("serial")
public class DataServiceImpl extends RemoteServiceServlet 
                             implements DataService {
  public Date getDate() {
    return(new Date());
  } 
  
  public double getSum(double d1, double d2) {
    return(d1 + d2);
  }

  public Customer getCustomerById(String id) {
    return(CustomerUtils.getCustomerOrDefault(id));
  }
}
