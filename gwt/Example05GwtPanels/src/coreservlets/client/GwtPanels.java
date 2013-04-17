package coreservlets.client;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class GwtPanels implements EntryPoint {
  public void onModuleLoad() {
    addHorizontalPanel();
    addVerticalPanel();
    addTabPanel(); 
    addDockPanel();
    addDockLayoutPanel();
    addGrid();
    addHorizontalSplitPanel();
    addVerticalSplitPanel();
    addButtonForPopup();
  }
  
  private void addHorizontalPanel() {
    String text = "<b>This is a simple<br/>HorizontalPanel</b>";
    HorizontalPanel hPanel = makeHorizontalPanel(text, 5);
    RootPanel.get("horizontal-panel").add(hPanel);
  }
  
  private HorizontalPanel makeHorizontalPanel(String text, 
                                              int numButtons) {
    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setSpacing(5); 
    hPanel.add(new HTML(text));
    for (int i=1; i <= numButtons; i++) {
      hPanel.add(new Button("Button " + i));
    }
    return(hPanel);
  }
  
  private void addVerticalPanel() {
    String text = 
      "<b>This is a simple<br/>VerticalPanel</b>";
    VerticalPanel vPanel = makeVerticalPanel(text, 5);
    RootPanel.get("vertical-panel").add(vPanel);
  }
  
  private VerticalPanel makeVerticalPanel(String text, 
                                          int numButtons) {
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setSpacing(5); 
    vPanel.add(new HTML(text));
    for (int i=1; i <= numButtons; i++) {
      vPanel.add(new Button("Button " + i));
    }
    return(vPanel);
  }
  
  private void addTabPanel() {
    DecoratedTabPanel tPanel = new DecoratedTabPanel();
    tPanel.setWidth("400px");
    for(int i=1; i <= 5; i++) {
      String panelText = 
        "<h1>This is Panel " + i + ". <i>Wow!</i></h1>";
      tPanel.add(new HTML(panelText), "Panel " + i);             
    }
    tPanel.selectTab(0);
    tPanel.setAnimationEnabled(true);
    RootPanel.get("tab-panel").add(tPanel);
  }
  
  private void addDockPanel() {
    DockPanel dPanel = new DockPanel();
    dPanel.setSpacing(5);
    dPanel.setHorizontalAlignment
      (HasHorizontalAlignment.ALIGN_CENTER);
    VerticalPanel westPanel = 
      makeVerticalPanel("<b>This is<br/>WEST</b>", 9);
    dPanel.add(westPanel, DockPanel.WEST);
    VerticalPanel eastPanel = 
      makeVerticalPanel("<b>This is<br/>EAST</b>", 9);
    dPanel.add(eastPanel, DockPanel.EAST);
    HorizontalPanel northPanel = 
      makeHorizontalPanel("<b>This is<br/>NORTH</b>", 3);
    dPanel.add(northPanel, DockPanel.NORTH);
    HorizontalPanel southPanel = 
      makeHorizontalPanel("<b>This is<br/>SOUTH</b>", 3);
    dPanel.add(southPanel, DockPanel.SOUTH);
    ScrollPanel sPanel = 
      new ScrollPanel(new HTML(getCenterText()));
    sPanel.setSize("475px", "300px");
    dPanel.add(sPanel, DockPanel.CENTER);
    RootPanel.get("dock-panel").add(dPanel);
  }
  
  private void addDockLayoutPanel() {
    DockLayoutPanel dLayoutPanel = new DockLayoutPanel(Unit.EM);
    dLayoutPanel.addNorth(new HTML("<h1>Header</h1>"), 4);
    dLayoutPanel.addSouth(new HTML("<h1>Footer</h1>"), 4);
    dLayoutPanel.addWest(new HTML("<h2>Navigation</h2>"), 10);
    dLayoutPanel.add(new ScrollPanel(new HTML(getRandomText())));
    dLayoutPanel.setSize("50em", "12em");
    RootPanel.get("dock-layout-panel").add(dLayoutPanel);
  }
  
  private String getCenterText() {
    String text =
      "<h2>This is CENTER</h2> " + 
      "<p>Note that in GWT, unlike in Swing or AWT, you can " +
      "add more than one entry to NORTH, SOUTH, EAST, or WEST, " +
      "and the entries come out next to each other.</p> " +
      "<p>Also, in the CENTER, you can add a ScrollPanel and " +
      "get scrollbars without needing an IFRAME.</p>" + 
      "<p>Random text so that scrollbars are needed. " +
      getRandomText();
    return(text);
  }
  
  private String getRandomText() {
    String text =
      "Blah, blah, blah, blah, blah, blah, blah. " +
      "Yadda, yadda, yadda, yadda, yadda, yadda. ";
    return(text + text + text + text + text + text + text);
  }
  
  private void addGrid() {  // Class is Grid, not GridPanel
    Grid grid = new Grid(5, 3);
    grid.setCellPadding(10);
    for(int i=0; i<grid.getRowCount(); i++) {
      grid.setText(i, 0, "Text in row " + i);
      grid.setHTML(i, 1, "<b>HTML</b> in row <i>" + i + "</i>");
      grid.setWidget(i, 2, new Button("Button in row " + i));
    }
    RootPanel.get("grid").add(grid);
  }
  
  private void addHorizontalSplitPanel() {
    HorizontalSplitPanel hsPanel = 
      new HorizontalSplitPanel();
    hsPanel.setSize("475px", "300px");
    hsPanel.setSplitPosition("30%");
    String text = getSplitPanelText();
    hsPanel.setLeftWidget(new HTML(text));
    hsPanel.setRightWidget(new HTML(text));
    RootPanel.get("horizontal-split-panel").add(hsPanel);
  }
  
  private String getSplitPanelText() {
    String text = 
      "<p><b>Here is some text for " +
      "each side of the splitter. " +
      "Drag the splitter and the text " +
      "will be rearranged.</b></p>" +
      "<p>" + getRandomText() + "</p>";
    return(text);
  }
  
  private void addVerticalSplitPanel() {
    VerticalSplitPanel vsPanel = 
      new VerticalSplitPanel();
    vsPanel.setSize("475px", "300px");
    vsPanel.setSplitPosition("30%");
    String text = getSplitPanelText();
    vsPanel.setTopWidget(new HTML(text));
    vsPanel.setBottomWidget(new HTML(text));
    RootPanel.get("vertical-split-panel").add(vsPanel);
  }
  
  private void addButtonForPopup() {
    Button button = new Button("Click to Make Popup");
    button.addClickHandler(new PopupHandler());
    RootPanel.get("button-for-popup").add(button);
  }
  
  private class PopupHandler implements ClickHandler {
    public void onClick(ClickEvent event) {
      PopupPanel popup = new PopupPanel(true);
      String text = 
        "Click <i>outside</i> popup<br/>to close it";
      VerticalPanel vPanel = makeVerticalPanel(text, 2);
      popup.setWidget(vPanel);
      UIObject button = (UIObject)event.getSource();
      int x = button.getAbsoluteLeft() + 100;
      int y = button.getAbsoluteTop() - 100;
      popup.setPopupPosition(x, y);
      popup.setAnimationEnabled(true);
      popup.show();
    }
  }
}
