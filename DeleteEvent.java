package calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Jonathan
 */
public class DeleteEvent implements EventHandler<ActionEvent> {
  String month;
  String day;
  String year;



  @Override
  public void handle(ActionEvent e) {
    month = Calendar.monthNum.getText();
    day = Calendar.day.getText();
    year = Calendar.year.getText();
    boolean created = true;

    //Create key and value for events hashmap
    String key = month + "-" + day + "-" + year;
    String value = "<delete>";

    //append save.txt with date as key and value of <delete>
    try {
      File saveFile = new File("save.txt");

      //if file does not exist, create it
      if (!saveFile.exists()) {
        saveFile.createNewFile();
        created = false; 
      }

      //create filewriter and bufferedwriter
      FileWriter fw = new FileWriter(saveFile.getAbsolutePath(), created);
      BufferedWriter buffAdd = new BufferedWriter(fw);

      //write to file
      buffAdd.write(key);
      buffAdd.newLine();
      buffAdd.write(value);
      buffAdd.newLine();
      buffAdd.close();
    }
    catch (IOException exception) {}
    
    //return to calendar view
    int monthNum = Integer.parseInt(Calendar.monthNum.getText());
    int yearNum = Integer.parseInt(Calendar.year.getText());
    SetupGUI backToCal = new SetupGUI(monthNum, yearNum);
    Calendar.app.setCenter(backToCal.calendarSpace);
    Calendar.calendar = backToCal;
  }
  
}