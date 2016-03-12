package calendar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;




/**
 * Calendar application.
 * @author Jonathan Chiu
 * @author Kevin Lai
 */

public class Calendar extends Application {
    protected static String[] monthNames = {
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
    
    protected static BorderPane app;
    protected static VBox eventControls;
    protected static HBox calControls;
    protected static Label month;
    protected static Label year;
    protected static Label day;
    protected static Label monthNum;
    protected static Button nextMonth;
    protected static Button prevMonth;
    protected static Button addEvent;
    protected static Button viewEvent;
    protected static SetupGUI calendar;
    

    
    @Override
    public void start(Stage stage) throws Exception {
        
        //width and height for month label and year label
        int width = 140;
        int height = 25;
        
        //width for prevMonth and nextMonth buttons
        int width_button = 200;
        
        //declaring variables for add reminder and view day
        String reminder = "Add reminder";
        String view_day = "View Day";
        
        //create the label for month and year
        month = new Label();
        year = new Label();
        
        //variable for number of things in HBox and VBox
        int HBOX_num = 4;
        int VBOX_num = 2;
        
        //variable holding the string "Calendar"
        String name = "Calendar";
        
        //catches output of SetupTools.getCurrentDate()
        int[] monthYear = SetupTools.getCurrentDate();
        
        //get corresponding month name; set month and year
        month.setText(monthNames[monthYear[0] - 1]);
        year.setText("" + monthYear[1]);
        month.setStyle("-fx-background-color: #00B2EE");
        year.setStyle("-fx-background-color: #00B2EE");
        month.setPrefWidth(width);
        year.setPrefWidth(width);
        month.setPrefHeight(height);
        year.setPrefHeight(height);
        
        //set up hidden monthNum and day labels
        monthNum = new Label(String.valueOf(monthYear[0]));
        day = new Label();
        
        //add prevMonth, month, year, nextMonth to calControls HBox
        calControls = new HBox(HBOX_num);
        nextMonth = new Button(">");
        prevMonth = new Button("<");
        nextMonth.setPrefWidth(width_button);
        prevMonth.setPrefWidth(width_button);
        prevMonth.setStyle("-fx-background-color: #66CDAA");
        nextMonth.setStyle("-fx-background-color: #66CDAA");
        calControls.getChildren().addAll(prevMonth, month, year, 
        nextMonth);
        
        //reminder control VBox
        eventControls = new VBox(VBOX_num);
        addEvent = new Button(reminder);
        viewEvent = new Button(view_day);
        addEvent.setStyle("-fx-background-color: #66CDAA");
        viewEvent.setStyle("-fx-background-color: #66CDAA");
        addEvent.setPrefWidth(Double.MAX_VALUE);
        viewEvent.setPrefWidth(Double.MAX_VALUE);
        eventControls.getChildren().addAll(addEvent, viewEvent);
        
        //add event listeners 
        MoveMonth monthAction = new MoveMonth();
        AddEvent newEvent = new AddEvent();
        ViewEvent view = new ViewEvent();
        nextMonth.setOnAction(monthAction);
        prevMonth.setOnAction(monthAction);
        addEvent.setOnAction(newEvent);
        viewEvent.setOnAction(view);
        
        //add calendar and event controls to BorderPane
        app = new BorderPane();
        app.setTop(calControls);
        app.setBottom(eventControls);
        
        //add calendar to BorderPane
        calendar = new SetupGUI(monthYear[0], monthYear[1]);
        app.setCenter(calendar.calendarSpace);
        
        //create the scene
        stage.setTitle(name);
        Scene scene = new Scene(app, 700, 650);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
