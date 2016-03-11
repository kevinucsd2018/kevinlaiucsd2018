package calendar;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Sets up a GridPane for each month
 * @author Jonathan Chiu
 */
public class SetupGUI {
  
    //then you set to your node
    GridPane calendarSpace;
    protected Button[] daySpaces;
    protected Label[] dayLabels;
    int startDay;
    int numDays;
    int monthNum;
    int monthLength;
    int year;
    String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private Object root;
    
    
    /**
     * Constructor method to set up GUI.
     * @param month
     * @param year
     */
    public SetupGUI(int month, int year) {
      
        calendarSpace = new GridPane();
        daySpaces = new Button[35];
        dayLabels = new Label[7];
        int numColumns = 7;
        int numRows = 7;
        int gridWidth = 93;
        int gridHeight = 70;
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
       
        calendarSpace.setStyle("-fx-background-image: "
        + "url('http://previews.123rf.com/"
        + "images/markovka/markovka1401/markovka140100676/26688543"
        + "-Vector-abstract-background-Cool-cell-structure-Constellation"
        + "-seamless-pattern-Endless-texture-with-s-Stock-Vector.jpg\')");
        calendarSpace.setHgap(5);
        calendarSpace.setVgap(5);
        calendarSpace.setPadding(new Insets(40, 10, 10, 10));
       // int name_of_month = month.getText();
        System.out.print(monthNum);
        
        //determine offset
        offset = SetupTools.findDay(month, 1, year);
        
        //determine total number of days
        totalDays = SetupTools.findTotalDays(month, year);
        
        //create Event Listener
        SelectDay daySelector = new SelectDay();
        
        //add Day Labels first
        int row = 0; 
        for (int i = 0; i < numColumns;i++) {
          dayLabels[i] = new Label(dayNames[i]);
          dayLabels[i].setStyle("-fx-background-color: #00E5EE");
          dayLabels[i].setPrefWidth(Double.MAX_VALUE);
          dayLabels[i].setPrefHeight(Double.MAX_VALUE);
          dayLabels[i].setAlignment(Pos.CENTER);
          calendarSpace.add(dayLabels[i], i, row, 1, 1);
        }
        
        //positioning variables for the rest of the calendar
        int col = offset; //each day
        row = 1;
        
        //set up checks to see if a given day is a holiday or event exists
        Holiday holidayCheck = new Holiday();
        ReadEvent newEvent = new ReadEvent();
        
        //populate GridPane
        for (int i = 1; i <= totalDays; i++) {
            daySpaces[i] = new Button("" + i);
            daySpaces[i].setOnAction(daySelector);
            daySpaces[i].setPrefWidth(Double.MAX_VALUE);
            daySpaces[i].setPrefHeight(Double.MAX_VALUE);
            daySpaces[i].setStyle("-fx-font-size: 150pt;");
            
            //color holidays different color
            if (holidayCheck.isHoliday(month, i)) {

              daySpaces[i].setStyle("-fx-background-color: #4EEE94");
             // daySpaces[i].setStyle("-fx-font-size: 14px");

              daySpaces[i].setStyle("-fx-background-color: #70db70");

            
            }
            else {
              daySpaces[i].setStyle("-fx-background-color: #FFE1FF");
            }
            
            //create key for making events hashmap
            String key = month + "-" + i + "-" + year;
            if (newEvent.events.containsKey(key)) {
              daySpaces[i].setStyle("-fx-background-color: #ff66ff");
            }
            
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
