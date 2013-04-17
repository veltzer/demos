package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.*;

public class GwtApp1 implements EntryPoint {
  @Override // Java 6 only: remove in Java 5
  public void onModuleLoad() {  // Main entry point
    buttonSetup();
    listSetup();
  }
  
  private void buttonSetup() {
    Button randomNumberButton = new Button("Show Random Number");
    HTML randomNumberResult = new HTML("<i>Num will go here</i>");
    randomNumberButton.addClickHandler
      (new RanNumHandler(randomNumberResult));
    RootPanel.get("numberButtonHolder").add(randomNumberButton);
    RootPanel.get("numberResultHolder").add(randomNumberResult);
  }
  
  private class RanNumHandler implements ClickHandler {
    private HTML resultRegion;
    
    public RanNumHandler(HTML resultRegion) {
      this.resultRegion = resultRegion;
    }
    
    @Override // Java 6 only: remove in Java 5
    public void onClick(ClickEvent event) {
      resultRegion.setText("Number: " + Math.random()*10);
    }
  }
  
  private void listSetup() {
    ListBox stateList = new ListBox();
    populateStateList(stateList);
    stateList.setVisibleItemCount(1);
    ListBox cityList = new ListBox();
    cityList.addItem("Select City");
    cityList.setVisibleItemCount(1);
    cityList.setEnabled(false);
    stateList.addChangeHandler(new StateHandler(stateList, 
                                                cityList));
    RootPanel.get("stateListHolder").add(stateList);
    RootPanel.get("cityListHolder").add(cityList);
  }

  private void populateStateList(ListBox stateList) {
    stateList.addItem("Select State");
    StateInfo[] nearbyStates = 
      StateInfo.getNearbyStates();
    for(StateInfo state: nearbyStates) {
      stateList.addItem(state.getStateName());
    }
  }
  
  private class StateHandler implements ChangeHandler {
    private ListBox stateList, cityList;
    
    public StateHandler(ListBox stateList, ListBox cityList) {
      this.stateList = stateList;
      this.cityList = cityList;
    }
    
    @Override // Java 6 only: remove in Java 5
    public void onChange(ChangeEvent event) {
      int index = stateList.getSelectedIndex();
      String state = stateList.getItemText(index);
      StateInfo[] nearbyStates =
        StateInfo.getNearbyStates();
      String[] cities = 
        StateInfo.findCities(nearbyStates, state);
      cityList.clear();
      for(String city: cities) {
        cityList.addItem(city);
      }
      cityList.setEnabled(true);
    }
  }
}