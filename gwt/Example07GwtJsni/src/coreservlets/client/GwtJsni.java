package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

public class GwtJsni implements EntryPoint {
  private TextBox textfield;
  private HTML resultArea;
  
  public void onModuleLoad() {
    textfield = new TextBox();
    textfield.addKeyUpHandler(new TextfieldHandler());
    resultArea = new HTML("<i>Result will go here</i>");
    RootPanel.get("textfieldID").add(textfield);
    RootPanel.get("resultID").add(resultArea);
    Button circleButton1 = new Button("Show Circle Areas (1)");
    circleButton1.addClickHandler(new CircleAreaHandler1());
    RootPanel.get("circle-button-1").add(circleButton1);
    Button circleButton2 = new Button("Show Circle Areas (2)");
    circleButton2.addClickHandler(new CircleAreaHandler2());
    RootPanel.get("circle-button-2").add(circleButton2);
  }
  
  private class TextfieldHandler implements KeyUpHandler {
    public void onKeyUp(KeyUpEvent event) {
      String text = textfield.getText();
      resultArea.setHTML(text);
      if(text.equalsIgnoreCase("test1")) {
        alert1(text);
      } else if(text.equalsIgnoreCase("test2")) {
        highlight1("resultID");
      } else if(text.equalsIgnoreCase("test3")) {
        highlight2("resultID", text);
      }
    }
  }
  
  private native void alert1(String message) /*-{
   $wnd.alert(message);
  }-*/;
  
  private native void highlight1(String id) /*-{
    new $wnd.Effect.Highlight(id);
    new $wnd.Effect.Shake(id);
  }-*/;
  
  private double randomTime(double n) {
    return(Math.random() * n);
  }
  
  private void alert2(String text) {
    Window.alert("Value: " + text);
  }
  
  private native void highlight2(String id, String text) /*-{
    var time = 
      this.@coreservlets.client.GwtJsni::randomTime(D)(10);
    this.@coreservlets.client.GwtJsni::alert2(Ljava/lang/String;)(text);
    new $wnd.Effect.Highlight(id, { duration: time});
    new $wnd.Effect.Shake(id, { duration: time});
  }-*/;
  
  private native Circle getCircle(int i) /*-{
    return $wnd.circles[i]; 
  }-*/;
  
  
  private native JsArray<JsCircle> getCircles() /*-{
    return $wnd.circles; 
  }-*/;
  
  private void showCircleAlert(Circle circle) {
    String info = "Original Circle: " + 
    circle.getInfo() + "\n";
    circle.setRadius(Math.random() * 100);
    info += "Modified Circle: " + circle.getInfo();
    Window.alert(info);
  }
  
  private class CircleAreaHandler1 implements ClickHandler {
    public void onClick(ClickEvent event) {
      int numCircles = 3; // Hard-coded length
      for(int i=0; i<numCircles; i++) {
        Circle circle = getCircle(i);
        showCircleAlert(circle);
      }
    }
  }
  
  private class CircleAreaHandler2 implements ClickHandler {
    public void onClick(ClickEvent event) {
      JsArray<JsCircle> circles = getCircles();
      for(int i=0; i<circles.length(); i++) {
        Circle circle = circles.get(i);
        showCircleAlert(circle);
      }
    }
  }
}