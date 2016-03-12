package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;



/**
 * Display Event Details in a GridPane
 * @author Jonathan Chiu A12113428 jhc028@ucsd.edu
 * @author Kevin Lai A12012166 kelai@ucsd.edu
 */
public class ViewEvent implements EventHandler<ActionEvent> {
  Label titleLab;
  Label remindLab;
  Button confirm;
  Button delete;
  VBox viewContainer;
  
  @Override
  public void handle(ActionEvent e) {
    String month = Calendar.monthNum.getText();
    String monthName = Calendar.month.getText();
    String day = Calendar.day.getText();
    String year = Calendar.year.getText();
    String reminder;
    String holidayName = "";
    String errorTitle = "Uh oh!";
    String errorHeader = "No day was selected.";
    String errorMsg = "Please select a day before adding a reminder.";
    String newlineTag = "<newline>";
    String newlineSym = "\n";
    String thanksMsg = "Thanks!";
    String deleteMsg = "Delete Reminder";
    int monthNum;
    int dayNum;
    
    //check that date label is not empty
    if (day.equals("")) {
      //display alert message
      Alert errorAlert = new Alert(Alert.AlertType.ERROR);
      errorAlert.setTitle(errorTitle);
      errorAlert.setHeaderText(errorHeader);
      errorAlert.setContentText(errorMsg);
      errorAlert.showAndWait();
      return;
    }
    
    //grab month and day if exists
    monthNum = Integer.parseInt(month);
    dayNum = Integer.parseInt(day);
    
    //setup check for holiday
    Holiday holidayCheck = new Holiday();
    String holidayKey = holidayCheck.createKey(monthNum, dayNum);
    
    //obtain holiday details if exists
    if (holidayCheck.holidays.containsKey(holidayKey)) {
      holidayName = holidayCheck.holidays.get(holidayKey);
    }
    
    //create key for readevent hashmap
    ReadEvent reader = new ReadEvent();
    String key = month + "-" + day + "-" + year;
    
    //grab reminder if exists
    if (reader.events.containsKey(key)) {
      reminder = reader.events.get(key).replace(newlineTag, newlineSym); 
    }
    else {
      reminder = "No reminders here!";
    }
    
    //assign to label and output
    String title = "For: " + monthName + " " + day + ", " + year;
    reminder += "\n" + holidayName;
    titleLab = new Label(title);
    remindLab = new Label(reminder);
    
    //set up buttons
    GoBack back = new GoBack();
    DeleteEvent deleteEvent = new DeleteEvent();
    confirm = new Button(thanksMsg);
    delete = new Button(deleteMsg);
    confirm.setOnAction(back);
    delete.setOnAction(deleteEvent);
    
    //add to VBox
    viewContainer = new VBox(4);
    viewContainer.setAlignment(Pos.CENTER);
    viewContainer.getChildren().addAll(titleLab, remindLab, confirm, delete);
    
    //display in program
    Calendar.app.setCenter(viewContainer);
    
  }
}
