package coreservlets.server;

import com.google.gwt.user.server.rpc.*;
import coreservlets.client.*;

public class DataServiceImpl extends RemoteServiceServlet 
                             implements DataService {
  public String getButton1Data() {
    String result = 
      String.format("Number: %.2f", Math.random()*10);
    return(result);
  } 
  
  public String[] getButton2Data() {
    String[] results = 
      { String.format("%.2f", Math.random()),
        String.format("%.2f", Math.random() * 10),
        String.format("%.2f", Math.random() * 100),
        String.format("%.2f", Math.random() * 1000) };
    return(results);
  }
  
  public RandomNumber getButton3Data(String rangeString) {
    return(new RandomNumber(rangeString));
  } 
}
