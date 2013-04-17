package coreservlets.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class DialogHandler implements ClickHandler {
  public void onClick(ClickEvent event) {
    DialogBox dialog = new DialogBox(true);
    String text = 
      "<h1>Wow! Button was pressed!</h1>";
    dialog.setWidget(new HTML(text));
    dialog.setText("Very Cool DialogBox");
    UIObject button = (UIObject)event.getSource();
    int x = button.getAbsoluteLeft() + 100;
    int y = button.getAbsoluteTop() - 100;
    dialog.setPopupPosition(x, y);
    dialog.setAnimationEnabled(true);
    dialog.setWidth("250px");
    dialog.show();
  }
}
