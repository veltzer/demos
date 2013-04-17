package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;

public class GwtPanelsExercises implements EntryPoint {
  public void onModuleLoad() {
    addTabPanel1();  
    addTabPanel2();  
    addTabPanel3(); 
    addGrid();
  }
  
  private void addTabPanel1() {
    DecoratedTabPanel tPanel = new DecoratedTabPanel();
    tPanel.setWidth("400px");
    for(int i=1; i <= 4; i++) {
      String panelText = 
        "<h1>Your number is " + Math.random() + "</h1>";
      tPanel.add(new HTML(panelText), "Panel " + i);             
    }
    tPanel.selectTab(0);
    tPanel.setAnimationEnabled(true);
    RootPanel.get("tab-panel-1").add(tPanel);
  }
  
  private void addTabPanel2() {
    DecoratedTabPanel tPanel = new DecoratedTabPanel();
    for(int i=1; i <= 4; i++) {
      tPanel.add(makeHorizontalPanel1(), "Panel " + i);             
    }
    tPanel.selectTab(0);
    tPanel.setAnimationEnabled(true);
    RootPanel.get("tab-panel-2").add(tPanel);
  }
  
  private HorizontalPanel makeHorizontalPanel1() {
    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setSpacing(5); 
    hPanel.setVerticalAlignment
      (HasVerticalAlignment.ALIGN_MIDDLE);
    hPanel.add(new HTML("<h1>" + Math.random() + "</h1>"));
    hPanel.add(new Button("Click Me"));
    hPanel.add(new TextBox());
    String listText = 
      "<ul><li>Foo</li><li>Bar</li><li>Baz</li></ul>";
    hPanel.add(new HTML(listText));
    return(hPanel);
  }
  
  private void addTabPanel3() {
    DecoratedTabPanel tPanel = new DecoratedTabPanel();
    for(int i=1; i <= 4; i++) {
      tPanel.add(makeHorizontalPanel2(), "Panel " + i);             
    }
    tPanel.selectTab(0);
    tPanel.setAnimationEnabled(true);
    RootPanel.get("tab-panel-3").add(tPanel);
  }
  
  private HorizontalPanel makeHorizontalPanel2() {
    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setSpacing(5); 
    hPanel.setVerticalAlignment
      (HasVerticalAlignment.ALIGN_MIDDLE);
    hPanel.add(new HTML("<h1>" + Math.random() + "</h1>"));
    hPanel.add(makeVerticalPanel(4));
    hPanel.add(new TextBox());
    String listText = 
      "<ul><li>Foo</li><li>Bar</li><li>Baz</li></ul>";
    hPanel.add(new HTML(listText));
    return(hPanel);
  }
  
  private VerticalPanel makeVerticalPanel(int numButtons) {
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setSpacing(5); 
    for (int i=1; i <= numButtons; i++) {
      vPanel.add(new Button("Button " + i));
    }
    return(vPanel);
  }
  
  private void addGrid() {  // Class is Grid, not GridPanel
    Grid grid = new Grid(5, 3);
    for(int i=0; i<grid.getColumnCount(); i++) {
      grid.setText(i, 0, "Enter value " + (i+1));
      grid.setWidget(i, 1, new TextBox());
      grid.setWidget(i, 2, new Button("Do Something Cool with Value"));
    }
    RootPanel.get("grid").add(grid);
  }
}
