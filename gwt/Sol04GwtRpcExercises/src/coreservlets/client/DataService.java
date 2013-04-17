package coreservlets.client;

import com.google.gwt.user.client.rpc.*;
import java.util.*;

@RemoteServiceRelativePath("data-service")
public interface DataService extends RemoteService {
  public Date getDate();
  public double getSum(double d1, double d2);
  public Customer getCustomerById(String id);
}