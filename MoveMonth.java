package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Go to next or previous month.
 * 
 * @author Jonathan Chiu
 */
public class MoveMonth implements EventHandler<ActionEvent> {
    private int monthNum;
    private int year;
    
    @Override
    public void handle(ActionEvent e) {
        monthNum = Integer.parseInt(Calendar.monthNum.getText());
        year = Integer.parseInt(Calendar.year.getText());
        SetupGUI newMonth;
        
        //change months
        if (e.getSource() == Calendar.nextMonth) {
            //December to next January
            if (monthNum == 12) {
                monthNum = 1;
                year++;
            }
            //all other months
            else {
                monthNum++;
            }
        }
        else if (e.getSource() == Calendar.prevMonth) {
            //January to December of last year
            if (monthNum == 1) {
                monthNum = 12;
                year--;
            }
            //all other months
            else {
                monthNum--;
            }
        }
        
        
        
        //update month and year, reset day
        Calendar.month.setText(Calendar.monthNames[monthNum - 1]);
        Calendar.year.setText("" + year);
        Calendar.monthNum.setText("" + monthNum);
        Calendar.day.setText("");
        
        //create new GridPane and replace old one
        newMonth = new SetupGUI(monthNum, year);
        Calendar.app.setCenter(newMonth.calendarSpace);
        Calendar.calendar = newMonth;
    } 
}
