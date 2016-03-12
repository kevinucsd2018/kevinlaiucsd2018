package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;



/**
 * Updates the hidden day Label in Calendar class and highlights selected day.
 * @author Jonathan Chiu A12113428 jhc028@ucsd.edu
 * @author Kevin Lai A12012166 kelai@ucsd.edu
 */
public class SelectDay implements EventHandler<ActionEvent> {
    
  
  
  /**
   * Get text of pressed button and updates day Label
   * @param e ActionEvent
   */
  @Override
  public void handle(ActionEvent e) {
    String newDayLabel = ((Button)e.getSource()).getText(); //text from button
    String oldDayLabel = Calendar.day.getText(); //text from day Label
    String deselect = "-fx-background-color: #FFE1FF";
    String select = "-fx-background-color: #EED8AE";
    String year = Calendar.year.getText();
    int oldDay;
    int newDayNum = Integer.parseInt(newDayLabel);
    int month = Integer.parseInt(Calendar.monthNum.getText());

    //create key for previous day to check against readevents hashmap
    ReadEvent reader = new ReadEvent();
    String key = month + "-" + oldDayLabel + "-" + year;

    //set up holiday checker
    Holiday check = new Holiday();

    //deselect previous button only if a previous button was pressed
    if (!oldDayLabel.equals("")) {

      //grab day and month from calendar
      oldDay = Integer.parseInt(oldDayLabel);

      //if it is not a holiday, undo color change
      if (!check.isHoliday(month, oldDay) && !reader.events.containsKey(key)) {
        ((SetupGUI)Calendar.calendar).daySpaces[oldDay].setStyle(deselect);
      }
    }

    //create key for current day to check against readevent hashmap
    key = month + "-" + newDayLabel + "-" + year;

    //if it is not a holiday and not an event, change colors
    if (!check.isHoliday(month, newDayNum) && !reader.events.containsKey(key)) {
      ((Button)e.getSource()).setStyle(select);
    }

    //update dayLabel
    Calendar.day.setText(newDayLabel);
  }   
}