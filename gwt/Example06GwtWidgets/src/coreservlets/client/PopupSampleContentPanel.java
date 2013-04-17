package coreservlets.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class PopupSampleContentPanel extends VerticalPanel {
  public PopupSampleContentPanel(String text, 
                                 final PopupPanel container) {
    setSpacing(5); 
    setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    add(new HTML(text));
    Button button = new Button("Close");
    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        container.hide();
      }
    });
    add(button);
  }
}
