package calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Adds an Event
 * @author Jonathan Chiu
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
    String existingReminder = "";
    
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
    
    //TESTING:
    System.out.println("kEY IS: " + key);
    
    //check if a reminder already exists
    ReadEvent reader = new ReadEvent();
    if (reader.events.containsKey(key)) {
        
      //grab exising reminder and replace all <newline> with /n
      existingReminder = reader.events.get(key).replace("<newline>", "\n");
    }
    
    
    
    //initialize controls
    addGrid = new GridPane();
    titleLabel = new Label("Reminder for: " + titleDate);
    reminderText = new TextArea(existingReminder);
    submit = new Button("Remember!");
    cancel = new Button("Forget it.");
    
    //add event listeners
    SaveEvent save = new SaveEvent();
    submit.setOnAction(save);
    
    addGrid.add(titleLabel, 1, 1, 3, 1);
    addGrid.add(reminderText, 1, 2, 3, 2);
    addGrid.add(submit, 2, 4, 1, 1);
    addGrid.add(cancel, 2, 5, 1, 1);
    
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
      
      //check that reminder field is not empty
      if (reminder.equals("")) {
        //alert
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Uh oh");
        errorAlert.setHeaderText("No reminder was written.");
        errorAlert.setContentText("Please write a reminder.");

        errorAlert.showAndWait();
        return;
      }
      
      try {
        File saveFile = new File("save.txt");
      
        //if file does not exist, create it
        if (!saveFile.exists()) {
          saveFile.createNewFile();
          created = false; 
        }
        
        //TESTING: get rid of all newlines in reminder
        reminder = reminder.replace("\n", "<newline>");
        System.out.println(reminder);
        
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
      
      }
 
      //return to calendar view
      int monthNum = Integer.parseInt(Calendar.monthNum.getText());
      int yearNum = Integer.parseInt(Calendar.year.getText());
      SetupGUI backToCal = new SetupGUI(monthNum, yearNum);
      Calendar.app.setCenter(backToCal.calendarSpace);

    }
  }
}
