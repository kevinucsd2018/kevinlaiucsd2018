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
  Button confirm;
  VBox reminderContainer;
  
  @Override
  public void handle(ActionEvent e) {
    String month = Calendar.monthNum.getText();
    String monthName = Calendar.month.getText();
    String day = Calendar.day.getText();
    String year = Calendar.year.getText();
    String reminder;
    String holidayName = "";
    int monthNum = Integer.parseInt(month);
    int dayNum = Integer.parseInt(day);
    
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
    
    //setup check for holiday
    Holiday holidayCheck = new Holiday();
    String holidayKey = holidayCheck.createKey(monthNum, dayNum);
    
    if (holidayCheck.holidays.containsKey(holidayKey)) {
      holidayName = holidayCheck.holidays.get(holidayKey);
    }
    
    
    //create key for readevent hashmap
    ReadEvent reader = new ReadEvent();
    String key = month + "-" + day + "-" + year;
    
    //grab reminder if exists
    if (reader.events.containsKey(key)) {
      reminder = reader.events.get(key).replace("<newline>", "\n"); 
    }
    else {
      reminder = "No reminders here!";
    }
    
    //assign to label and output
    String title = "For: " + monthName + " " + day + ", " + year;
    reminder += "\n" + holidayName;
    titleLabel = new Label(title);
    reminderLabel = new Label(reminder);
    
    //set up button
    GoBack back = new GoBack();
    confirm = new Button("Thanks");
    confirm.setOnAction(back);
    
    reminderContainer = new VBox(3);
    reminderContainer.getChildren().addAll(titleLabel, reminderLabel, confirm);
    
    //display in app
    Calendar.app.setCenter(reminderContainer);
    
  }
  
}
