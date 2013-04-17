package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;

public class GwtEvents1 implements EntryPoint {
  private TextBox textfield;
  public HTML resultArea;
  
  @Override
  public void onModuleLoad() {
    textfield = new TextBox();
    textfield.addKeyUpHandler(new WowHandler(this));
    resultArea = new HTML("<i>Result will go here</i>");
    RootPanel.get("textfieldID").add(textfield);
    RootPanel.get("resultID").add(resultArea);
  }
  
  public void backgroundRed() {
    RootPanel.get().addStyleName("red");
  }
  
  public void backgroundNormal() {
    RootPanel.get().removeStyleName("red");
  }
}
