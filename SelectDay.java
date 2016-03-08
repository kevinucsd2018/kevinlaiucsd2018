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
        String day = ((Button)e.getSource()).getText(); //text from button
        String dayLabel = Calendar.day.getText(); //text from day Label
        String select= "-fx-background-color: #FFB5C5";
        String deselect = "-fx-background-color: #FFD39B";
        int dayNum;
        
        
        //if a day is previousy selected, undo color change
        if (!dayLabel.equals("")) {
            dayNum = Integer.parseInt(dayLabel);
            {
            ((SetupGUI)Calendar.calendar).daySpaces[dayNum].setStyle(deselect);
            }
        }

        //color selected Button 
        ((Button)e.getSource()).setStyle(select);
        Calendar.day.setText(day);
        
        
    }
    
}
