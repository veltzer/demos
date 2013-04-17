package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.*;
import java.util.*;

public class GwtBasicsExercises implements EntryPoint {
  public void onModuleLoad() {  // Main entry point
    System.out.println("In onModuleLoad");
    buttonSetup();
    textBoxSetup();
  }
  
  private void buttonSetup() {
    Button randomNumberButton = new Button("Show Date/Time");
    HTML randomNumberResult = new HTML("<i>Date will go here</i>");
    randomNumberButton.addClickHandler
      (new DateHandler(randomNumberResult));
    RootPanel.get("dateButton").add(randomNumberButton);
    RootPanel.get("dateResult").add(randomNumberResult);
  }
  
  private class DateHandler implements ClickHandler {
    private HTML resultRegion;
    
    public DateHandler(HTML resultRegion) {
      this.resultRegion = resultRegion;
    }
    
    public void onClick(ClickEvent event) {
      resultRegion.setText("Time: " + new  Date());
    }
  }
  
  private void textBoxSetup() {
    TextBox num1 = new TextBox();
    TextBox num2 = new TextBox();
    Button sumButton = new Button("Add Numbers");
    HTML sumResult = new HTML("<i>Sum will go here</i>");
    sumButton.addClickHandler(new SumHandler(num1, num2, sumResult));
    RootPanel.get("num1").add(num1);
    RootPanel.get("num2").add(num2);
    RootPanel.get("sumButton").add(sumButton);
    RootPanel.get("sumResult").add(sumResult);
  }
  
  private class SumHandler implements ClickHandler {
    private HTML resultRegion;
    private TextBox num1Field, num2Field;
    
    public SumHandler(TextBox num1Field, TextBox num2Field,
                      HTML resultRegion) {
      this.num1Field = num1Field;
      this.num2Field = num2Field;
      this.resultRegion = resultRegion;
    }
    
    public void onClick(ClickEvent event) {
      double val1 = 0.0;
      double val2 = 0.0;
      try {
        val1 = Double.parseDouble(num1Field.getText());
        val2 = Double.parseDouble(num2Field.getText());
      } catch(NumberFormatException nfe) {
        // do nothing, since val1 and val2 have defaults of 0
      }
      double sum = val1 + val2;
      resultRegion.setText("Sum: " + sum);
    }
  }
  
}