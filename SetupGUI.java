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
 * @author Jonathan Chiu A12113428 jhc028@ucsd.edu
 * @author Kevin Lai A12012166 kelai@ucsd.edu
 */
public class SetupGUI {
  GridPane calendarSpace;
  protected Button[] daySpaces;
  protected Label[] dayLabels;
  int startDay;
  int numDays;
  int monthNum;
  int monthLength;
  int year;
  String[] dayNames = {"Su", "M", "T", "W", "Th", "F", "Sa"};



  /**
   * Constructor method to set up GUI.
   * @param month
   * @param year
   */
  public SetupGUI(int month, int year) {   
    calendarSpace = new GridPane();
    daySpaces = new Button[35];
    dayLabels = new Label[7];
    String eventColor = "-fx-background-color: #ECB38A";
    String regColor = "-fx-background-color: #F7DCB4";
    String holidayColor = "-fx-background-color: #EE799F";
    String dayColor = "-fx-background-color: #7CCDB2";
    String calSize = "-fx-font-size: 18px";
    int numColumns = 7;
    int numRows = 7;
    int gWidth = 93;
    int gHeight = 70;
    int offset = 0;
    int totalDays = 0;
    int calGap = 5;
    int row;
    int col;
    int padding = 10;
    int extraPad = 40;
    
    //Set dimensions of the GridPane
    for (int i = 0; i < numColumns; i++) {
      calendarSpace.getColumnConstraints().add(new ColumnConstraints(gWidth));
    }
    for (int i = 0; i < numRows; i++) {
      calendarSpace.getRowConstraints().add(new RowConstraints(gHeight));
    }

    //style the GridPane
    calendarSpace.setAlignment(Pos.CENTER);
    calendarSpace.setHgap(calGap);
    calendarSpace.setVgap(calGap);
    calendarSpace.setPadding(new Insets(extraPad, padding, padding, padding));
    calendarSpace.setStyle(calSize); 

    //determine which day a month starts on and total days
    offset = SetupTools.findDay(month, 1, year);
    totalDays = SetupTools.findTotalDays(month, year);

    //add labels for each day
    row = 0; 
    for (int i = 0; i < numColumns;i++) {
      dayLabels[i] = new Label(dayNames[i]);
      dayLabels[i].setStyle(dayColor);
      dayLabels[i].setPrefWidth(Double.MAX_VALUE);
      dayLabels[i].setPrefHeight(Double.MAX_VALUE);
      dayLabels[i].setAlignment(Pos.CENTER);
      calendarSpace.add(dayLabels[i], i, row, 1, 1);
    }

    //positioning variables for the rest of the calendar
    col = offset;
    row = 1;

    //set up checks to see if a given day is a holiday or event exists
    Holiday holidayCheck = new Holiday();
    ReadEvent newEvent = new ReadEvent();
      
    //create Event Listener to select day
    SelectDay daySelector = new SelectDay();
      
    //populate GridPane
    for (int i = 1; i <= totalDays; i++) {
      daySpaces[i] = new Button("" + i);
      daySpaces[i].setOnAction(daySelector);
      daySpaces[i].setPrefWidth(Double.MAX_VALUE);
      daySpaces[i].setPrefHeight(Double.MAX_VALUE);

      //color holidays a different color
      if (holidayCheck.isHoliday(month, i)) {
        daySpaces[i].setStyle(holidayColor);
      }
      else {
        daySpaces[i].setStyle(regColor);
      }

      //create key for making events hashmap
      String key = month + "-" + i + "-" + year;
      
      //check hashmap to see if an event exists
      if (newEvent.events.containsKey(key)) {
        daySpaces[i].setStyle(eventColor);
      }

      //add day Button to Gridpane
      calendarSpace.add(daySpaces[i], col, row, 1, 1);
      col++;

      //move down one row for next week
      if (col % 7 == 0) {
          col = 0;
          row++;
      }           
    }
  }  
}
