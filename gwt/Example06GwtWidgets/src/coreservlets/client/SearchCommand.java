package coreservlets.client;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

public abstract class SearchCommand implements Command {
  protected SuggestBox box;
  
  public abstract String getBaseUrl();
  
  public void execute() {
    String url = getBaseUrl() +
                 URL.encode(box.getText());
    Window.Location.assign(url);
  }
}
