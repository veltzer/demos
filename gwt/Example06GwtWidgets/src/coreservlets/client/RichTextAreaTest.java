package coreservlets.client;

import com.google.gwt.user.client.ui.*;

public class RichTextAreaTest {
  public void addRichTextArea() {
    VerticalPanel resumePanel = 
      new VerticalPanel();
    resumePanel.setSpacing(5);
    resumePanel.setHorizontalAlignment
      (HasHorizontalAlignment.ALIGN_CENTER);
    String heading = "<h3>Edit resume</h3>";
    resumePanel.add(new HTML(heading));

    RichTextArea resumeArea =
      new RichTextArea();
    resumeArea.setHTML("<h1>foo</h1><h1>bar</h1>");
    resumeArea.setSize("400ps", "300px");
    resumePanel.add(resumeArea);

    Button uploadButton =
      new Button("Send to gwt-jobs.net");
    resumePanel.add(uploadButton);
    RootPanel.get("rich-text-panel").add(resumePanel);
  }
}
