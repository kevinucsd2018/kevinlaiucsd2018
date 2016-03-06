package calendar;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Sets up a GridPane for each month
 * @author Jonathan Chiu
 */
public class SetupGUI {
    GridPane calendarSpace;
    protected Button[] daySpaces;
    int startDay;
    int numDays;
    int monthNum;
    int monthLength;
    int year;
    
    /**
     * Constructor method to set up GUI.
     * @param month
     * @param year
     */
    public SetupGUI(int month, int year) {
        calendarSpace = new GridPane();
        daySpaces = new Button[35];
        int numColumns = 7;
        int numRows = 6;
        int gridWidth = 87;
        int gridHeight = 75;
        int offset = 0;
        int totalDays = 0;
        
        //add H & V constraints to the grid
        for (int i = 0; i < numColumns; i++) {
            calendarSpace.getColumnConstraints().add(new ColumnConstraints(gridWidth));
        }
        for (int i = 0; i < numRows; i++) {
            calendarSpace.getRowConstraints().add(new RowConstraints(gridHeight));
        }
        
        //style the GridPane
        calendarSpace.setStyle("-fx-background-color: #FFFAF0");
        
        //determine offset
        offset = SetupTools.findDay(month, 1, year);
        
        //determine total number of days
        totalDays = SetupTools.findTotalDays(month, year);
        
        //positioning variables
        int col = offset; //each day
        int row = 0; //each week
        
        //create Event Listener
        SelectDay daySelector = new SelectDay();
        
        //populate GridPane
        for (int i = 1; i <= totalDays; i++) {
            daySpaces[i] = new Button("" + i);
            daySpaces[i].setStyle("-fx-background-color: #EED8AE");
            daySpaces[i].setOnAction(daySelector);
            calendarSpace.add(daySpaces[i], col, row, 1, 1);
            col++;
            
            //move down one row
            if (col % 7 == 0) {
                col = 0;
                row++;
            }           
        }
    }
    
}
