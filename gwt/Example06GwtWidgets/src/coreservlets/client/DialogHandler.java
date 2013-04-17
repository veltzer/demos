package coreservlets.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class DialogHandler implements ClickHandler {
  public void onClick(ClickEvent event) {
    DialogBox dialog = new DialogBox(true);
    String text = 
      "A DialogBox contains another widget inside. "  +
      "Here, the DialogBox contains a VerticalPanel. " +
      "It has a separate caption, and its size " +
      "can be independent from the widget it contains.";
    Widget sampleContent = 
      new PopupSampleContentPanel(text, dialog);
    dialog.setWidget(sampleContent);
    dialog.setText("Dialog Box Caption");
    UIObject button = (UIObject)event.getSource();
    int x = button.getAbsoluteLeft() + 100;
    int y = button.getAbsoluteTop() - 100;
    dialog.setPopupPosition(x, y);
    dialog.setAnimationEnabled(true);
    dialog.setWidth("350px");
    dialog.show();
  }
}
