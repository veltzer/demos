package coreservlets.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.*;

public class WowHandler implements KeyUpHandler {
  private GwtEvents1 app;
  
  public WowHandler(GwtEvents1 app) {
    this.app = app;
  }
  
  @Override
  public void onKeyUp(KeyUpEvent event) {
    TextBox textfield = (TextBox)event.getSource();
    String text = textfield.getText();
    if(text.equalsIgnoreCase("gwt")) {
      app.resultArea.setHTML("<span class='wow'>Wow!</span>");
      app.backgroundRed();
    } else {
      app.resultArea.setHTML(text);
      app.backgroundNormal();
    }
  }
}
