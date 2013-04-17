package coreservlets.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class PopupHandler implements ClickHandler {
  public void onClick(ClickEvent event) {
    PopupPanel popup = new PopupPanel(true);
    String text = 
      "A PopupPanel contains another widget inside.<br/>" +
      "Here, the PopupPanel contains a VerticalPanel.<br/>" +
      "A PopupPanel has no separate caption, and<br/>" +
      "its size comes entirely from the widget it contains.";
    Widget sampleContent = 
      new PopupSampleContentPanel(text, popup);
    popup.setWidget(sampleContent);
    UIObject button = (UIObject)event.getSource();
    int x = button.getAbsoluteLeft() + 100;
    int y = button.getAbsoluteTop() - 100;
    popup.setPopupPosition(x, y);
    popup.setAnimationEnabled(true);
    popup.show();
  }
}
