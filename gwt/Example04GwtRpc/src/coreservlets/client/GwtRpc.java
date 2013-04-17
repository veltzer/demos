package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.*;

public class GwtRpc implements EntryPoint {
  private DataServiceAsync serviceProxy;
  private HTML label1, label2, label3;
  private TextBox rangeBox;

  public void onModuleLoad() {  // Main entry point
    serviceProxy = GWT.create(DataService.class);
    Button button1 = new Button("Show Random Number");
    label1 = new HTML("<i>Num will go here</i>");
    button1.addClickHandler(new Button1Handler());
    RootPanel.get("button1").add(button1);
    RootPanel.get("label1").add(label1);
    Button button2 = new Button("Show Random Numbers");
    label2 = new HTML("<i>List will go here</i>");
    button2.addClickHandler(new Button2Handler());
    RootPanel.get("button2").add(button2);
    RootPanel.get("label2").add(label2);
    Button button3 = new Button("Show Random Number");
    rangeBox = new TextBox();
    label3 = new HTML("<i>Num will go here</i>");
    button3.addClickHandler(new Button3Handler());
    RootPanel.get("button3").add(button3);
    RootPanel.get("rangeBox").add(rangeBox);
    RootPanel.get("label3").add(label3);
  }
  
  private class Button1Handler implements ClickHandler {
    public void onClick(ClickEvent event) {
      serviceProxy.getButton1Data(new Button1Callback());
    }
  }
  
  private class Button2Handler implements ClickHandler {
    public void onClick(ClickEvent event) {
      serviceProxy.getButton2Data(new Button2Callback());
    }
  }
  
  private class Button3Handler implements ClickHandler {
    public void onClick(ClickEvent event) {
      String range = rangeBox.getText();
      serviceProxy.getButton3Data(range, 
                                 new Button3Callback());
    }
  }
  
  private class Button1Callback implements AsyncCallback<String> {
    public void onSuccess(String result) {
      label1.setHTML(result);
    }
      
    public void onFailure(Throwable caught) {
      Window.alert("Unable to get data from server.");  
    }
  }
  
  private class Button2Callback implements AsyncCallback<String[]> {
    public void onSuccess(String[] listItems) {
      String result = "<ul>\n";
      for(String item: listItems) {
        result = result + "<li>" + item + "</li>\n"; 
      }
      result = result + "</ul>";
      label2.setHTML(result);
    }
      
    public void onFailure(Throwable caught) {
      Window.alert("Unable to get data from server.");
    }
  }
  
  private class Button3Callback implements AsyncCallback<RandomNumber> {
    public void onSuccess(RandomNumber number) {
      String range = "Range: " + number.getRange();
      if (!number.isRangeLegal()) {
        range = range + " (default due to illegal range)";
      }
      String value = "Value: " + number.getValue();
      String result = "<ul>\n" +
                      "<li>" + range + "</li>\n" +
                      "<li>" + value + "</li>\n" +
                      "</ul>";
      label3.setHTML(result);
    } 
      
    public void onFailure(Throwable caught) {
      Window.alert("Unable to get data from server.");
    }
  }
}

