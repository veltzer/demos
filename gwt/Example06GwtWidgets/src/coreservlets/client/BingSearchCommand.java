package coreservlets.client;

import com.google.gwt.user.client.ui.*;

public class BingSearchCommand extends SearchCommand {
  public BingSearchCommand(SuggestBox box) {
    this.box = box;
  }
  
  @Override
  public String getBaseUrl() {
    return("http://www.bing.com/search?q=");
  }
}
