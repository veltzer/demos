package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.*;

public class GwtEventsExercises implements EntryPoint {
  public void onModuleLoad() {  // Main entry point
    helloButtonSetup();
    textBoxSetup();
  }
  
  private void helloButtonSetup() {
    HTML languagePrompt = new HTML("Language: ");
    TextBox languageBox = new TextBox();
    Button greetButton = new Button("Give Greeting");
    HTML greetingResult = new HTML("<i>Greeting will go here</i>");
    greetButton.addClickHandler
      (new GreetingHandler(languageBox, greetingResult));
    RootPanel.get("languagePrompt").add(languagePrompt);
    RootPanel.get("languageBox").add(languageBox);
    RootPanel.get("greetButton").add(greetButton);
    RootPanel.get("greetingResult").add(greetingResult);
  }
  
  private class GreetingHandler implements ClickHandler {
    private TextBox languageBox;
    private HTML resultRegion;
    
    public GreetingHandler(TextBox languageBox, HTML resultRegion) {
      this.languageBox = languageBox;
      this.resultRegion = resultRegion;
    }
    
    public void onClick(ClickEvent event) {
      String greeting = "<b>Unknown language. Goodbye!</b>";
      String language = languageBox.getText();
      if (language.equalsIgnoreCase("English")) {
        greeting = "<b>Hello</b>";
      } else if (language.equalsIgnoreCase("Spanish")) {
        greeting = "<b>Hola</b>";
      }
      resultRegion.setHTML(greeting);
    }
  }
  
  private void textBoxSetup() {
    TextBox num1 = new TextBox();
    TextBox num2 = new TextBox();
    HTML sumResult = new HTML("<i>Sum will go here</i>");
    num1.addKeyUpHandler(new SumHandler(num1, num2, sumResult));
    num2.addKeyUpHandler(new SumHandler(num1, num2, sumResult));
    RootPanel.get("num1").add(num1);
    RootPanel.get("num2").add(num2);
    RootPanel.get("sumResult").add(sumResult);
  }
  
  private class SumHandler implements KeyUpHandler {
    private HTML resultRegion;
    private TextBox num1Field, num2Field;
    
    public SumHandler(TextBox num1Field, TextBox num2Field,
                      HTML resultRegion) {
      this.num1Field = num1Field;
      this.num2Field = num2Field;
      this.resultRegion = resultRegion;
    }
    
    public void onKeyUp(KeyUpEvent event) {
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