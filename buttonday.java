package calendar;



import static calendar.Calendar.month;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * Updates the hidden day Label in SetupGUI
 * @author Jonathan Chiu
 */

public class buttonday implements EventHandler<ActionEvent> 
{
    
    /**
     * Get text of button and updates day Label
     * @param e 
     */

    
    @Override
    public void handle(ActionEvent e) {
        String day = ((Button)e.getSource()).getText(); //text from button
        String dayLabel = Calendar.day.getText(); //text from day Label
        String monthLabel = Calendar.month.getText();
        String yearLabel = Calendar.year.getText();
        //if a day is previousy selected, undo color change
       if(!(yearLabel.equals("")))
                 {
            if (day.equals("1")&& monthLabel.equals("January")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Happy New Year!");
            alert.setContentText("We wish a great year ahead of you!");
            alert.showAndWait();
            }
            if (day.equals("14")&& monthLabel.equals("February")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Happy Valentines!");
            alert.setContentText("Whether it's a get-together with your partner"
                    + " or singles awareness day, make the most out of it!");
            alert.showAndWait();
            }
            if (day.equals("15")&& monthLabel.equals("February")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Presidents' Day!");
            alert.setContentText("Let us all show gratitude for all the "
                    + "Presidents who have served in office and this" +
                     " free day off of work");
            alert.showAndWait();
            }
            if (day.equals("14")&& monthLabel.equals("March")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("National Pi Day!");
            alert.setContentText("Here is a good joke to go along with it: " +
             " 3.14 percent of sailors are pi-rates.");
            alert.showAndWait();
            }
             if (day.equals("1")&& monthLabel.equals("April")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("April Fool's!");
            alert.setContentText("Today is actually April 2");
            alert.showAndWait();
            }
            if (day.equals("22")&& monthLabel.equals("April")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Earth Day!");
            alert.setContentText("Keep it GREEN, Keep it CLEAN!");
            alert.showAndWait();
            }
            if (day.equals("4")&& monthLabel.equals("July")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Independence Day!");
            alert.setContentText("Celebrate Independence Day with fireworks," +
                    " friends, food, and family!");
            alert.showAndWait();
            }
            if (day.equals("31")&& monthLabel.equals("October")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Happy Halloween!");
            alert.setContentText("Trick or Treat?");
            alert.showAndWait();
            }
            if (day.equals("11")&& monthLabel.equals("November")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Veteran's Day!");
            alert.setContentText("Let us give thanks to all those who" +
                    "served and gave so much for our country");
            alert.showAndWait();
            }
            if (day.equals("25")&& monthLabel.equals("December")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("Merry Christmas and Happy Holidays!");
            alert.setContentText("Have a wonderful Holiday Season!");
            alert.showAndWait();
            }
            if (day.equals("31")&& monthLabel.equals("December")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Annual Events reminder");
            alert.setHeaderText("New Year's Eve!");
            alert.setContentText("We hope you've had a great year"
             + " and a even better one next year!");
            alert.showAndWait();
            }
               }
        }        
        
    }
    
