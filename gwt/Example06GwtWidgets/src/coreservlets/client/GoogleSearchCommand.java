package coreservlets.client;

import com.google.gwt.user.client.ui.*;

public class GoogleSearchCommand extends SearchCommand {
  public GoogleSearchCommand(SuggestBox box) {
    this.box = box;
  }
  
  @Override
  public String getBaseUrl() {
    return("http://www.google.com/search?q=");
  }
}
