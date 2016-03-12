package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Jonathan Chiu
 * @author Kevin Lai
 */
public class GoBack implements EventHandler<ActionEvent> {
  
  @Override
  public void handle(ActionEvent e) {
    int monthNum = Integer.parseInt(Calendar.monthNum.getText());
    int yearNum = Integer.parseInt(Calendar.year.getText());
    
    SetupGUI back = new SetupGUI(monthNum, yearNum);
    Calendar.app.setCenter(back.calendarSpace);
    Calendar.calendar = back;

  }
  
}
