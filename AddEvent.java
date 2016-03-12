package calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Adds an Event
 * @author Jonathan Chiu
 * @author Kevin Lai
 */
public class AddEvent implements EventHandler<ActionEvent> {
  protected GridPane addGrid;
  protected Label titleLabel;
  protected TextArea reminderText;
  protected Button submit;
  protected Button cancel;
  
  @Override
  public void handle(ActionEvent e) {
    String month = Calendar.month.getText();
    String monthNum = Calendar.monthNum.getText();
    String day = Calendar.day.getText();
    String year = Calendar.year.getText();
    String titleDate = month + " " + day + ", " + year;
    String key = monthNum + "-" + day + "-" + year;
    String existReminder = "";
    String errorTitle = "Uh oh";
    String errorHeader = "No day was selected";
    String errorContent = "Please select a day before adding a reminder";
    String newlineTag = "<newline>";
    String newlineSym = "\n";

    //check that date label is not empty
    if (day.equals("")) {
      //alert
      Alert errorAlert = new Alert(Alert.AlertType.ERROR);
      errorAlert.setTitle(errorTitle);
      errorAlert.setHeaderText(errorHeader);
      errorAlert.setContentText(errorContent);
      errorAlert.showAndWait();
      return;
    }
    
    //check if a reminder already exists
    ReadEvent reader = new ReadEvent();
    if (reader.events.containsKey(key)) {
      
      //grab exising reminder and replace all <newline> with /n
      existReminder = reader.events.get(key).replace(newlineTag, newlineSym);
    }
    
    //initialize controls
    addGrid = new GridPane();
    addGrid.setAlignment(Pos.CENTER);
    titleLabel = new Label("Reminder for: " + titleDate);
    reminderText = new TextArea(existReminder);
    submit = new Button("Remember!");
    cancel = new Button("Forget it.");
    
    
    //add event listeners
    SaveEvent save = new SaveEvent();
    GoBack back = new GoBack();
    submit.setOnAction(save);
    cancel.setOnAction(back);
    addGrid.add(titleLabel, 1, 1, 3, 1);
    addGrid.add(reminderText, 1, 2, 3, 2);
    addGrid.add(submit, 2, 4, 1, 1);
    addGrid.add(cancel, 3, 4, 1, 1);
    Calendar.app.setCenter(addGrid);
  }
  
  /**
   * To save details from add event
   */
  public class SaveEvent implements EventHandler<ActionEvent> {
    
    @Override
    public void handle(ActionEvent e) {
      String month = Calendar.monthNum.getText();
      String day = Calendar.day.getText();
      String year = Calendar.year.getText();
      String key = month + "-" + day + "-" + year;
      String reminder = reminderText.getText();
      boolean created = true;
      String reminderTitle = "Uh oh";
      String reminderHeader = "No reminder was written";
      String reminderContent = "Please write a reminder";
      String save_file = "save.txt";
      String newlineTag = "<newline>";
      String newlineSym = "\n";
      
      //check that reminder field is not empty
      if (reminder.equals("")) {
        //alert
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(reminderTitle);
        errorAlert.setHeaderText(reminderHeader);
        errorAlert.setContentText(reminderContent);
        errorAlert.showAndWait();
        return;
      }
      
      try {
        File saveFile = new File(save_file);
        
        //if file does not exist, create it and exit
        if (!saveFile.exists()) {
          saveFile.createNewFile();
          created = false; 
        }
        
        //replace all newline tags with newline symbols
        reminder = reminder.replace(newlineSym, newlineTag);
        
        //create filewriter and bufferedwriter
        FileWriter fw = new FileWriter(saveFile.getAbsolutePath(), created);
        BufferedWriter buffAdd = new BufferedWriter(fw);
            
        //write to file
        buffAdd.write(key);
        buffAdd.newLine();
        buffAdd.write(reminder);
        buffAdd.newLine();
        buffAdd.close();
      }
      catch (IOException exception) {
        //alert
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(reminderTitle);
        errorAlert.setHeaderText(reminderHeader);
        errorAlert.setContentText("" + exception);
        errorAlert.showAndWait();
      }
 
      //return to calendar view
      int monthNum = Integer.parseInt(Calendar.monthNum.getText());
      int yearNum = Integer.parseInt(Calendar.year.getText());
      SetupGUI backToCal = new SetupGUI(monthNum, yearNum);
      Calendar.app.setCenter(backToCal.calendarSpace);
      Calendar.calendar = backToCal;  
    }
  }
}
