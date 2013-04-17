package coreservlets.client;

import com.google.gwt.user.client.ui.*;

public class YahooSearchCommand extends SearchCommand {
  public YahooSearchCommand(SuggestBox box) {
    this.box = box;
  }
  
  @Override
  public String getBaseUrl() {
    return("http://search.yahoo.com/search?p=");
  }
}
