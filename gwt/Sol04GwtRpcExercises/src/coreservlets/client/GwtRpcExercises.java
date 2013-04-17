package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.*;
import java.util.*;

public class GwtRpcExercises implements EntryPoint {
  private DataServiceAsync serviceProxy;
  private HTML label1, label2, label3;
  private TextBox num1, num2, idBox;

  public void onModuleLoad() {  // Main entry point
    serviceProxy = GWT.create(DataService.class);
    Button button1 = new Button("Show Time on Server");
    label1 = new HTML("<i>Time will go here</i>");
    button1.addClickHandler(new Button1Handler());
    RootPanel.get("button1").add(button1);
    RootPanel.get("label1").add(label1);
    Button button2 = new Button("Show Sum");
    num1 = new TextBox();
    num2 = new TextBox();
    label2 = new HTML("<i>Sum will go here</i>");
    button2.addClickHandler(new Button2Handler());
    RootPanel.get("button2").add(button2);
    RootPanel.get("sumInputs").add(num1);
    RootPanel.get("sumInputs").add(num2);
    RootPanel.get("label2").add(label2);
    Button button3 = new Button("Show Customer");
    idBox = new TextBox();
    label3 = new HTML("<i>Customer will go here</i>");
    button3.addClickHandler(new Button3Handler());
    RootPanel.get("button3").add(button3);
    RootPanel.get("customerId").add(idBox);
    RootPanel.get("label3").add(label3);
  }
  
  private class Button1Handler implements ClickHandler {
    public void onClick(ClickEvent event) {
      serviceProxy.getDate(new Button1Callback());
    }
  }
  
  private class Button2Handler implements ClickHandler {
    public void onClick(ClickEvent event) {
      double val1 = 0.0;
      double val2 = 0.0;
      try {
        val1 = Double.parseDouble(num1.getText());
        val2 = Double.parseDouble(num2.getText());
      } catch(NumberFormatException nfe) {
        // do nothing, since val1 and val2 have defaults of 0
      }
      serviceProxy.getSum(val1, val2,
                          new Button2Callback());
    }
  }
  
  private class Button3Handler implements ClickHandler {
    public void onClick(ClickEvent event) {
      serviceProxy.getCustomerById(idBox.getText(), 
                                   new Button3Callback());
    }
  }
  
  private class Button1Callback implements AsyncCallback<Date> {
    public void onSuccess(Date serverTime) {
      label1.setHTML("Time on server is " + serverTime);
    }
      
    public void onFailure(Throwable caught) {
      Window.alert("Unable to get data from server.");  
    }
  }
  
  private class Button2Callback implements AsyncCallback<Double> {
    public void onSuccess(Double sum) {
      label2.setHTML("Sum: " + sum);
    }
      
    public void onFailure(Throwable caught) {
      Window.alert("Unable to get data from server.");
    }
  }
  
  private class Button3Callback implements AsyncCallback<Customer> {
    public void onSuccess(Customer customer) {
      String result = ResultUtils.makeBulletedList(customer);
      label3.setHTML(result);
    } 
      
    public void onFailure(Throwable caught) {
      Window.alert("Unable to get data from server.");
    }
  }
}

