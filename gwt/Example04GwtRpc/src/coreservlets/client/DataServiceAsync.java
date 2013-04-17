package coreservlets.client;

import com.google.gwt.user.client.rpc.*;

public interface DataServiceAsync {
  public void getButton1Data(AsyncCallback<String> callback);
  public void getButton2Data(AsyncCallback<String[]> callback);
  public void getButton3Data(String range, 
                             AsyncCallback<RandomNumber> callback);
}