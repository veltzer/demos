package coreservlets.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.*;

public class SearchHandler implements ClickHandler {
  private SuggestBox box;
  
  public SearchHandler(SuggestBox box) {
    this.box = box;
  }
  
  public void onClick(ClickEvent event) {
    String url = "http://www.google.com/search?q=" +
                 URL.encode(box.getText());
    Window.Location.assign(url);
  }
}
