package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.*;
import com.google.gwt.user.client.*;
import java.util.*;

public class GwtWidgets implements EntryPoint {
  public void onModuleLoad() {
    addPanelForPopups();
    addTabPanel();  
    addStackPanel();  
    addDatePanel();
    addSuggestPanel();
    addMenuPanel();
  }
  
  private void addPanelForPopups() {
    HorizontalPanel buttonPanel = new HorizontalPanel();
    buttonPanel.setSpacing(10);
    Button button1 = new Button("DialogBox");
    button1.addClickHandler(new DialogHandler());
    buttonPanel.add(button1);
    Button button2 = new Button("PopupPanel");
    button2.addClickHandler(new PopupHandler());
    buttonPanel.add(button2);
    Button button3 = 
      new Button("Native Dialog (Alert) Box");
    button3.addClickHandler(new AlertHandler());
    buttonPanel.add(button3);
    RootPanel.get("popup-buttons").add(buttonPanel);
  }
  
  private void addTabPanel() {
    DecoratedTabPanel tPanel = new DecoratedTabPanel();
    tPanel.setWidth("450px");
    for(int i=1; i <= 5; i++) {
      Widget tabContent =
        new TabPanelSampleContent(i);
      String tabLabel = "Panel " + i;
      tPanel.add(tabContent, tabLabel);             
    }
    tPanel.selectTab(0);
    tPanel.setAnimationEnabled(true);
    RootPanel.get("tab-panel").add(tPanel);
  }
  
  private void addStackPanel() {
    DecoratedStackPanel sPanel = new DecoratedStackPanel();
    sPanel.setWidth("450px");
    for(int i=1; i <= 5; i++) {
      Widget tabContent =
        new TabPanelSampleContent(i);
      String tabLabel = "Panel " + i;
      sPanel.add(tabContent, tabLabel);             
    }
    RootPanel.get("stack-panel").add(sPanel);
  }
  
  private void addDatePanel() {
    Grid datePanel = new Grid(2, 2);
    datePanel.setText(0, 0, "Departure Date:");
    DateBox departureBox = new DateBox();
    Date departureDate = new Date();
    departureBox.setValue(departureDate);
    datePanel.setWidget(0, 1, departureBox);
    datePanel.setText(1, 0, "Return Date:");
    DateBox returnBox = new DateBox();
    Date returnDate = 
      DateUtils.datePlusWeek(departureDate); 
    returnBox.setValue(returnDate);
    datePanel.setWidget(1, 1, returnBox);
    RootPanel.get("date-panel").add(datePanel);
  }
  
  private void addSuggestPanel() {
    HorizontalPanel langPanel = 
      new HorizontalPanel();
    langPanel.setSpacing(5);
    langPanel.add(new HTML("Enter language:"));
    MultiWordSuggestOracle langChoices = 
      new ProgrammingLanguageOracle();  
    SuggestBox langBox = 
      new SuggestBox(langChoices);
    langPanel.add(langBox);
    Button searchButton = 
      new Button("Send to Google");
    searchButton.addClickHandler(new SearchHandler(langBox));
    langPanel.add(searchButton);
    RootPanel.get("suggest-panel").add(langPanel);
  }
  
  private void addMenuPanel() {
    HorizontalPanel searchPanel = 
      new HorizontalPanel();
    searchPanel.setSpacing(5);
    searchPanel.add(new HTML("Enter language:"));
    MultiWordSuggestOracle langChoices = 
      new ProgrammingLanguageOracle();  
    final SuggestBox langBox = 
      new SuggestBox(langChoices);
    searchPanel.add(langBox);
    MenuBar mainMenu = new MenuBar();
    MenuBar searchMenu = 
      new SearchMenuBar(langBox);
    mainMenu.addItem("Search", searchMenu);
    Command alertCommand = new Command() {
      public void execute() {
        Window.alert("Selection is " + langBox.getText());
      }
    };
    mainMenu.addItem("Alert", alertCommand);
    MenuBar modifierMenu =
      new ModifierMenuBar(langBox);
    mainMenu.addItem("Modify", modifierMenu);
    searchPanel.add(mainMenu);
    RootPanel.get("menu-panel").add(searchPanel);
  }
}
