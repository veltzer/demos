package coreservlets.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

public class SearchMenuBar extends MenuBar {
  public SearchMenuBar(SuggestBox box) {
    super(true); // Vertical (dropdown) menu
    Command googleCommand =
      new GoogleSearchCommand(box);
    addItem("Google", googleCommand);
    Command yahooCommand =
      new YahooSearchCommand(box);
    addItem("Yahoo", yahooCommand);
    Command bingCommand =
      new BingSearchCommand(box);
    addItem("Bing", bingCommand);
  }
}
