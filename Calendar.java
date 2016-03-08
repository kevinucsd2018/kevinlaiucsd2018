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
 * @author Jonathan
 * @author Kevin
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
        
        //create the label for month and year
        month = new Label();
        year = new Label();
        //catches output of SetupTools.getCurrentDate()
        int[] monthYear = SetupTools.getCurrentDate();
        
        //get corresponding month name; set month and year
        month.setText(monthNames[monthYear[0] - 1]);
        year.setText("" + monthYear[1]);
        month.setStyle("-fx-background-color: #DA8566");
        year.setStyle("-fx-background-color: #DA8566");
        month.setPrefWidth(140);
        year.setPrefWidth(140);
        month.setPrefHeight(25);
        year.setPrefHeight(25);
        
        
        //set up hidden monthNum and day labels
        monthNum = new Label(String.valueOf(monthYear[0]));
        day = new Label();
        
        //add prevMonth, month, year, nextMonth to calControls HBox
        calControls = new HBox(6);
        nextMonth = new Button(">");
        prevMonth = new Button("<");
        nextMonth.setPrefWidth(200);
        prevMonth.setPrefWidth(200);
        prevMonth.setStyle("-fx-background-color: #ECC9AC");
        nextMonth.setStyle("-fx-background-color: #ECC9AC");
        calControls.getChildren().addAll(prevMonth, month, year, nextMonth);
        
          
        //add event listeners 
        MoveMonth monthAction = new MoveMonth();
        nextMonth.setOnAction(monthAction);
        prevMonth.setOnAction(monthAction);
        
        //add controls to BorderPane
        app = new BorderPane();
        app.setTop(calControls);
        app.setBottom(eventControls);
        
        //add calendar to BorderPane
        calendar = new SetupGUI(monthYear[0], monthYear[1]);
        app.setCenter(calendar.calendarSpace);
        
        //create the scene
        stage.setTitle("Calendar");
        Scene scene = new Scene(app, 680, 560);
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
