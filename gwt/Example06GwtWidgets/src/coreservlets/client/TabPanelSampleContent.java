package coreservlets.client;

import com.google.gwt.user.client.ui.*;

public class TabPanelSampleContent extends VerticalPanel {
  public TabPanelSampleContent(int i) {
    setSpacing(5); 
    setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    String heading = 
      "<h2>This is Content for Panel " + i + "</h2><hr/>";
    add(new HTML(heading));
    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setSpacing(5); 
    hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    String text =
      "The content can be any<br/>" +
      "GWT Widget, but is usually</br>" +
      "one of the Panel types.";
    hPanel.add(new HTML(text));
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setSpacing(5);
    for(int j=1; j<=3; j++) {
      vPanel.add(new Button("Button " + j));
    }
    hPanel.add(vPanel);
    String list =
      "<ul><li>Foo</li><li>Bar</li><li>Baz</li></ul>";
    hPanel.add(new HTML(list));
    add(hPanel);
  }
}
