package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * TODO: CHECK FOR HOLIDAYS AND ADD OK BUTTON
 * @author Jonathan Chiu
 */
public class ViewEvent implements EventHandler<ActionEvent> {
  Label titleLabel;
  Label reminderLabel;
  VBox reminderContainer;
  
  @Override
  public void handle(ActionEvent e) {
    String month = Calendar.monthNum.getText();
    String monthName = Calendar.month.getText();
    String day = Calendar.day.getText();
    String year = Calendar.year.getText();
    
    //check that date label is not empty
    if (day.equals("")) {
      //alert
      Alert errorAlert = new Alert(Alert.AlertType.ERROR);
      errorAlert.setTitle("Uh oh");
      errorAlert.setHeaderText("No day was selected.");
      errorAlert.setContentText("Please select a day before adding a reminder.");

      errorAlert.showAndWait();
      return;
    }
    
    //create key for readevent hashmap
    ReadEvent reader = new ReadEvent();
    String key = month + "-" + day + "-" + year;
    
    //grab reminder if exists
    if (reader.events.containsKey(key)) {
      String reminder = reader.events.get(key).replace("<newline>", "\n");
      reminderLabel = new Label(reminder);
    }
    else {
      reminderLabel = new Label("No reminders here!");
    }
    
    //assign to label and output
    String title = "For: " + monthName + " " + day + ", " + year;
    titleLabel = new Label(title);
    
    //TESTING
    System.out.println(title);
    
    
    reminderContainer = new VBox(2);
    reminderContainer.getChildren().addAll(titleLabel, reminderLabel);
    
    //display in app
    Calendar.app.setCenter(reminderContainer);
    
  }
  
}
