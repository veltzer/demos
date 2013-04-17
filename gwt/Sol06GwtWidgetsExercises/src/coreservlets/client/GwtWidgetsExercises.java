package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.datepicker.client.*;
import java.util.*;

public class GwtWidgetsExercises implements EntryPoint {
  public void onModuleLoad() {
    addButtonForDialog();
    addStackPanel();  
    addDatePanel(); 
    addSuggestPanel();
    addMenuPanel1();
  }
  
  private void addButtonForDialog() {
    Button button = new Button("Show DialogBox");
    button.addClickHandler(new DialogHandler());
    RootPanel.get("dialog-button").add(button);
  }
  
  private void addStackPanel() {
    DecoratedStackPanel sPanel = new DecoratedStackPanel();
    for(int i=1; i <= 4; i++) {
      sPanel.add(makeStackPanelContent(), "Panel " + i);             
    }
    sPanel.showStack(0);
    RootPanel.get("stack-panel").add(sPanel);
  }
  
  private HorizontalPanel makeStackPanelContent() {
    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setSpacing(5); 
    hPanel.setVerticalAlignment
      (HasVerticalAlignment.ALIGN_MIDDLE);
    hPanel.add(new HTML("<h1>" + Math.random() + "</h1>"));
    hPanel.add(makeVerticalButtonPanel(4));
    hPanel.add(new TextBox());
    String listText = 
      "<ul><li>Foo</li><li>Bar</li><li>Baz</li></ul>";
    hPanel.add(new HTML(listText));
    return(hPanel);
  }
  
  private VerticalPanel makeVerticalButtonPanel(int numButtons) {
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setSpacing(5); 
    for (int i=1; i <= numButtons; i++) {
      vPanel.add(new Button("Button " + i));
    }
    return(vPanel);
  }
  
  private void addDatePanel() {
    HorizontalPanel datePanel = new HorizontalPanel();
    datePanel.setSpacing(5);
    datePanel.add(new Label("Enter date: "));
    final DateBox dateField = new DateBox();
    datePanel.add(dateField);
    Button showYearButton = new Button("Show Year");
    datePanel.add(showYearButton);
    final HTML yearDisplay = new HTML("");
    datePanel.add(yearDisplay);
    showYearButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Date selectedDate = dateField.getValue();
        @SuppressWarnings("deprecation")
        int year = selectedDate.getYear();
        yearDisplay.setText("You chose the year " + (1900 + year));
      }
   });
    RootPanel.get("date-panel").add(datePanel);
  }
  
  private void addSuggestPanel() {
    HorizontalPanel cityPanel = new HorizontalPanel();
    cityPanel.setSpacing(5);
    cityPanel.add(new HTML("Enter city starting with A or B: "));
    MultiWordSuggestOracle cityChoices = new CityOracle();  
    SuggestBox cityBox = new SuggestBox(cityChoices);
    cityPanel.add(cityBox);
    Button searchButton = new Button("Send to Google");
    searchButton.addClickHandler(new SearchHandler(cityBox));
    cityPanel.add(searchButton);
    RootPanel.get("suggest-panel").add(cityPanel);
  }
  
  private void addMenuPanel1() {
    Command alertCommand = new Command() {
      public void execute() {
        Window.alert("A state was selected");
      }
    };
    MenuBar stateMenuM = new MenuBar(true);
    String[] mStates = 
      "Maine,Maryland,Massachusetts,Michigan,Minnesota,Mississippi,Missouri,Montana".split(",");
    for(String state: mStates) {
      stateMenuM.addItem(state, alertCommand);
    }
    MenuBar stateMenuN = new MenuBar(true);
    String[] nStates = 
      "Nebraska,Nevada,New Hampshire,New Jersey,New Mexico,New York,North Carolina,North Dakota".split(",");
    for(String state: nStates) {
      stateMenuN.addItem(state, alertCommand);
    }
    MenuBar stateMenuO = new MenuBar(true);
    String[] oStates = 
      "Ohio,Oklahoma,Oregon".split(",");
    for(String state: oStates) {
      stateMenuO.addItem(state, alertCommand);
    }
    MenuBar mainMenu = new MenuBar();
    mainMenu.addItem("M", stateMenuM);
    mainMenu.addItem("N", stateMenuN);
    mainMenu.addItem("O", stateMenuO);
    RootPanel.get("menu-panel-1").add(mainMenu);
  }
}
