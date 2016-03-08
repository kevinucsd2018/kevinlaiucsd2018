package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import static calendar.Calendar.month;
import javafx.scene.control.TextField;

/**
 * Sets up a GridPane for each month
 * @author Jonathan Chiu
 */
public class SetupGUI extends Calendar{
    GridPane calendarSpace;
    protected Button[] daySpaces;
    int startDay;
    int numDays;
    private int monthNum;
    int monthLength;
    int year;
    int height_of_days_of_week = 45;
    int width_of_days_of_week = 190;
    //int height_of_days = 
    /**
     * Constructor method to set up GUI.
     * @param month
     * @param year
     */
    public SetupGUI(int month, int year) {
        calendarSpace = new GridPane();
        daySpaces = new Button[35];
        Label Sunday = new Label("Sunday");
        Label Monday = new Label("Monday");
        Label Tuesday = new Label("Tuesday");
        Label Wednesday = new Label("Wednesday");
        Label Thursday = new Label("Thursday");
        Label Friday = new Label("Friday");
        Label Saturday = new Label("Saturday");
        int numColumns = 7;
        int numRows = 6;
        int gridWidth = 93;
        int gridHeight = 70;
        int offset = 0;
        int totalDays = 0;
        String dayLabel = Calendar.day.getText(); //text from day Label
        String monthLabel = Calendar.month.getText();
        String yearLabel = Calendar.year.getText();
        
        //add H & V constraints to the grid
        for (int i = 0; i < numColumns; i++) {
            calendarSpace.getColumnConstraints().add(new ColumnConstraints(gridWidth));
        }
        for (int i = 0; i <= numRows; i++) {
            calendarSpace.getRowConstraints().add(new RowConstraints(gridHeight));
        }
        
        //style the GridPane
        calendarSpace.setStyle("-fx-background-color: #FFFAF0");
        calendarSpace.setHgap(5);
        calendarSpace.setVgap(5);
        
        //determine offset
        offset = SetupTools.findDay(month, 1, year);
        
        //determine total number of days
        totalDays = SetupTools.findTotalDays(month, year);
        
        
        Sunday.setStyle("-fx-background-color: #FED0ED");
        Sunday.setPrefWidth(width_of_days_of_week);
        Sunday.setPrefHeight(height_of_days_of_week);
        calendarSpace.add(Sunday, 0, 0, 1, 1);
        Monday.setStyle("-fx-background-color: #FECDBC");
        Monday.setPrefWidth(width_of_days_of_week);
        Monday.setPrefHeight(height_of_days_of_week);
        calendarSpace.add(Monday, 1, 0, 1, 1);
        Tuesday.setStyle("-fx-background-color: #FEAB70");
        Tuesday.setPrefWidth(width_of_days_of_week);
        Tuesday.setPrefHeight(height_of_days_of_week);
        calendarSpace.add(Tuesday, 2, 0, 1, 1);
        Wednesday.setStyle("-fx-background-color: #FEB660");
        Wednesday.setPrefWidth(width_of_days_of_week);
        Wednesday.setPrefHeight(height_of_days_of_week);
        calendarSpace.add(Wednesday, 3, 0, 1, 1);
        Thursday.setStyle("-fx-background-color:  #FEAB70");
        Thursday.setPrefWidth(width_of_days_of_week);
        Thursday.setPrefHeight(height_of_days_of_week);
        calendarSpace.add(Thursday, 4, 0, 1, 1);
        Friday.setStyle("-fx-background-color: #FECDBC");
        Friday.setPrefWidth(width_of_days_of_week);
        Friday.setPrefHeight(height_of_days_of_week);
        calendarSpace.add(Friday, 5, 0, 1, 1);
        Saturday.setStyle("-fx-background-color: #FED0ED");
        Saturday.setPrefWidth(width_of_days_of_week);
        Saturday.setPrefHeight(height_of_days_of_week);
        calendarSpace.add(Saturday, 6, 0, 1, 1);
       
        //
       
        //positioning variables
        int col = offset; //each day
        int row = 1; //each week
       // buttonday days = new buttonday();
        SelectDay daySelector = new SelectDay();
        //buttonday days = new buttonday();
        //populate GridPane
        for (int i = 1; i <= totalDays; i++) {
            
            daySpaces[i] = new Button("" + i); 
            daySpaces[i].setStyle("-fx-background-color: #FFD39B");
            daySpaces[i].setOnAction(days);
            daySpaces[i].setOnAction(daySelector);
            //daySpaces[i].setOnAction(days);
            daySpaces[i].setPrefWidth(Double.MAX_VALUE);
            daySpaces[i].setPrefHeight(Double.MAX_VALUE);
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
    

