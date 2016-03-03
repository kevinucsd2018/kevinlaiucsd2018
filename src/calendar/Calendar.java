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
import java.util.HashMap;




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
    private BorderPane app;
    private Button nextMonth;
    private Button prevMonth;
    createCalendar calFunctions = new createCalendar();
    
    @Override
    public void start(Stage stage) throws Exception {
        
        int gridWidth = 80;
        int gridHeight = 60;
        int numColumns = 7; //7 days per week
        int numRows = 5; //4 weeks per month, plus overflow
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

            daySpaces[i] = new TextField("" + i);
            daySpaces[i].setEditable(false);
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
        displayMonthYear = new HBox(4);
        nextMonth = new Button(">");
        prevMonth = new Button("<");
        
        //add event listeners for next and previous buttons
        moveMonth monthAction = new moveMonth();
        nextMonth.setOnAction(monthAction);
        prevMonth.setOnAction(monthAction);
        
        displayMonthYear.getChildren().addAll(month, year, prevMonth, nextMonth);
        
        //add everything to the borderpane
        app = new BorderPane();
        app.setCenter(calendarSpace);
        app.setTop(displayMonthYear);
        
        //create the scene
        stage.setTitle("Calendar");
        Scene scene = new Scene(app, 600, 500);
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
            if (e.getSource() == prevMonth) {
                System.out.println("previous");
                
                //grab the month integer from the textfield, to be added later
                // add or subtract one depending on which button was pressed (account for 0 index)
                //grab the year from the textfield using name.getText()
                //put new month, day=1, year into findDay() method in createCalendar
                //create new gridpane
                //add constraints to gridpane (see above)
                //use two for loops (as above) to put textfields into gridpane
                //replace old gridpane with new gridpane

            
            
            
            }
            else if (e.getSource() == nextMonth) {
                System.out.println("next");
            
            }
    
    } 
}

    
}
