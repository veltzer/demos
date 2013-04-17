package coreservlets.client;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

public class ModifierMenuBar extends MenuBar {
  public ModifierMenuBar(final SuggestBox box) {
    super(true); // Vertical (dropdown) menu
    Command upperCaseCommand = new Command() {
      public void execute() {
        box.setText(box.getText().toUpperCase());
      }
    };
    addItem("Upper Case", upperCaseCommand);
    Command lowerCaseCommand = new Command() {
      public void execute() {
        box.setText(box.getText().toLowerCase());
      }
    };
    addItem("Lower Case", lowerCaseCommand);
    Command clearCommand = new Command() {
      public void execute() {
        box.setText("");
      }
    };
    addItem("Clear", clearCommand);
  }
}
