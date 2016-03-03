/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.awt.Color;
import javafx.event.ActionEvent;
import javafx.scene.control.Control;




/**
 * Calendar application.
 * @author Jonathan
 */

public class Calendar extends Application {
    private String[] months = {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
    };
    
    private int[] numDays = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    
    private GridPane calendarSpace;
    private TextField[] daySpaces;
    public TextField month;
    public TextField year;
    private HBox displayMonthYear;
    private HBox addevent;
    private BorderPane app;
    private Button nextMonth;
    private Button prevMonth;
    private Button adding_events;
    private TextField month_int;
    createCalendar calFunctions = new createCalendar();
    
    @Override
    public void start(Stage stage) throws Exception {
        
        int gridWidth = 87;
        int gridHeight = 75;
        int numColumns = 7; //7 days per week
        int numRows = 6; //4 weeks per month, plus overflow
        int[] monthYear = new int[2];
        int offset = 0;
        int totalDays = 0;
        String currentMonth = "";
        
        //set up 7 x 5 gridpane with predefined widths and heights
        calendarSpace = new GridPane();
        for (int i = 0; i < numColumns; i++) {
            calendarSpace.getColumnConstraints().add(new ColumnConstraints(gridWidth));
        }
        for (int i = 0; i < numRows; i++) {
            calendarSpace.getRowConstraints().add(new RowConstraints(gridHeight));
        }
        
        //get the current month and year 
        monthYear = calFunctions.getCurrentDate(); 
        
        //find out how many squares to skip to print the first of the month
        offset = calFunctions.findDay(monthYear[0], 1, monthYear[1]);
        
        //set up each box in the grid in the calendar
        daySpaces = new TextField[35];
        int col = offset; //each day
        int row = 0; //each row
        
        //number of days for a given month
        totalDays = numDays[monthYear[0] - 1] + 1;
        
        for (int i = 1; i < totalDays; i++) {

            daySpaces[i] = new TextField("       " + i);
            daySpaces[i].setEditable(false);
            daySpaces[i].setStyle("-fx-background-color: #EED8AE");
            calendarSpace.add(daySpaces[i], col, row, 1, 1);
            col++;
            if (col % 7 == 0) {
                col = 0;
                row++;
            }           
        }
        
        //create the label for month and year
        month = new TextField();
        year = new TextField();
        
        //determine which month we are currently in
        currentMonth = months[monthYear[0] - 1];
        
        month.setText(currentMonth);
        year.setText("" + monthYear[1]);
        
        //HBox includes Month, Year, Previous, and Next
        displayMonthYear = new HBox(5);
        nextMonth = new Button(">                             ");
        prevMonth = new Button("                           <");
        String month_num = String.valueOf(monthYear[0]);
        month_int = new TextField(month_num);
        //HBox inclues the button to add events
        addevent = new HBox(1);
        adding_events = new Button("                                                                             +                                                                               ");
        adding_events.setStyle("-fx-background-color: #48D1CC");
        month.setStyle("-fx-background-color: #48D1CC");
        year.setStyle("-fx-background-color: #48D1CC");
        prevMonth.setStyle("-fx-background-color: #EED8AE");
        nextMonth.setStyle("-fx-background-color: #EED8AE");
        //add event listeners for next and previous buttons
        moveMonth monthAction = new moveMonth();
        nextMonth.setOnAction(monthAction);
        prevMonth.setOnAction(monthAction);
        
        displayMonthYear.getChildren().addAll(prevMonth, month, year, nextMonth);
        addevent.getChildren().addAll(adding_events);
        calendarSpace.setStyle("-fx-background-color: #FFFAF0");
        
        //add everything to the borderpane
        app = new BorderPane();
        app.setCenter(calendarSpace);
        app.setTop(displayMonthYear);
        app.setBottom(addevent);
        
        //create the scene
        stage.setTitle("Calendar");
        Scene scene = new Scene(app, 599, 500);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
     
 
    /**
     * Event Handler for previous and next buttons.
     */
    public class moveMonth implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {
            int gridWidth = 87;
            int gridHeight = 75;
            int numColumns = 7; //7 days per week
            int numRows = 6; //4 weeks per month, plus overflow
            int offset = 0;
            int totalDays = 0;
            String currentMonth = "";
            int num_of_months = 12;
            //create a new gridpane for new month and add constraints
            calendarSpace = new GridPane();
            for (int i = 0; i < numColumns; i++) {
                calendarSpace.getColumnConstraints().add(new ColumnConstraints(gridWidth));
            }
            for (int i = 0; i < numRows; i++) {
                calendarSpace.getRowConstraints().add(new RowConstraints(gridHeight));
            }
            //if button being pressed is "<"
            if (e.getSource() == prevMonth) {
                String month_ds = month.getText(); 
                String year_ds = year.getText(); 
                int year_num = Integer.parseInt(year_ds); //converts to year to an int
                int month_num = 0;
                String prevMonth_display = "";
                //determining previous month
                month_num = Integer.parseInt(month_int.getText());
                if (month_num == 1) {
                    month_num = 12;
                    year_num -= 1;
                    prevMonth_display = months[month_num - 1];
                }
                else {
                    month_num -= 1;
                    prevMonth_display = months[month_num - 1];
                }
                //find out how many squares to skip to print the first of the month
                offset = calFunctions.findDay(month_num, 1, year_num);
                //set up each box in the grid in the calendar
                daySpaces = new TextField[35];
                int col = offset; //each day
                int row = 0; //each row
                //number of days for a given month
                totalDays = numDays[month_num - 1] + 1;
                
                for (int i = 1; i < totalDays; i++) {
                    daySpaces[i] = new TextField("       " + i);
                    daySpaces[i].setEditable(false);
                    daySpaces[i].setStyle("-fx-background-color: #EED8AE");
                    calendarSpace.add(daySpaces[i], col, row, 1, 1);
                    col++;
                     if (col % 7 == 0) {
                        col = 0;
                        row++;
                     }           
                }     
                //background color for the scene
                calendarSpace.setStyle("-fx-background-color: #FFFAF0");
                //update text fields
                String year_in_strings = "" + year_num;
                String month_in_strings = "" + month_num;
                month.setText(prevMonth_display);
                year.setText(year_in_strings);
                month_int.setText(month_in_strings); 
                app.setCenter(calendarSpace);
            }
            else if (e.getSource() == nextMonth) {
                String month_ds = month.getText(); 
                String year_ds = year.getText(); 
                int year_num = Integer.parseInt(year_ds); //converts to year to an int
                int month_num = 0;
                String nextMonth_display = "";
                //determining next month
                month_num = Integer.parseInt(month_int.getText());
                if (month_num == 12) {
                    month_num = 1;
                    nextMonth_display = months[month_num - 1];
                    year_num += 1;
                }
                else {
                    month_num += 1;
                    nextMonth_display = months[month_num - 1];
                }
                //find out how many squares to skip to print the first of the month
                offset = calFunctions.findDay(month_num, 1, year_num);
                //set up each box in the grid in the calendar
                daySpaces = new TextField[35];
                int col = offset; //each day
                int row = 0; //each row
                //number of days for a given month
                totalDays = numDays[month_num - 1] + 1;
                
                for (int i = 1; i < totalDays; i++) {
                    daySpaces[i] = new TextField("       " + i);
                    daySpaces[i].setEditable(false);
                    daySpaces[i].setStyle("-fx-background-color: #EED8AE");
                    calendarSpace.add(daySpaces[i], col, row, 1, 1);
                    col++;
                     if (col % 7 == 0) {
                        col = 0;
                        row++;
                     }           
                }     
                //background color for the scene
                calendarSpace.setStyle("-fx-background-color: #FFFAF0");
                //update text fields
                String year_in_strings = "" + year_num;
                String month_in_strings = "" + month_num;
                month.setText(nextMonth_display);
                year.setText(year_in_strings);
                month_int.setText(month_in_strings); 
                app.setCenter(calendarSpace);
            
            }
    
    } 
}

    
}
