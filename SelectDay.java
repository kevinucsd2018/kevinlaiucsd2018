package calendar;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Updates the hidden day Label in SetupGUI
 * @author Jonathan Chiu
 */

public class SelectDay implements EventHandler<ActionEvent> {
    
  /**
   * Get text of button and updates day Label
   * @param e 
   */
  @Override
  public void handle(ActionEvent e) {
    String newDayLabel = ((Button)e.getSource()).getText(); //text from button
    String oldDayLabel = Calendar.day.getText(); //text from day Label

    String select = "-fx-background-color: #FFE1FF";

    String deselect = "-fx-background-color: #EED8AE";

    String year = Calendar.year.getText();
    int oldDayNum;
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
      oldDayNum = Integer.parseInt(oldDayLabel);

      //if it is not a holiday, undo color change
      if (!check.isHoliday(month, oldDayNum) && !reader.events.containsKey(key)) {
        ((SetupGUI)Calendar.calendar).daySpaces[oldDayNum].setStyle(deselect);
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