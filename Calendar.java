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
        month.setStyle("-fx-background-color: #48D1CC");
        year.setStyle("-fx-background-color: #48D1CC");
        month.setPrefWidth(100);
        year.setPrefWidth(100);
        
        
        //set up hidden monthNum and day labels
        monthNum = new Label(String.valueOf(monthYear[0]));
        day = new Label();
        
        //add prevMonth, month, year, nextMonth to calControls HBox
        calControls = new HBox(6);
        nextMonth = new Button(">");
        prevMonth = new Button("<");
        nextMonth.setPrefWidth(200);
        prevMonth.setPrefWidth(200);
        prevMonth.setStyle("-fx-background-color: #EED8AE");
        nextMonth.setStyle("-fx-background-color: #EED8AE");
        calControls.getChildren().addAll(prevMonth, month, year, nextMonth, day, monthNum);
        
        //Event controls in VBox
        eventControls = new VBox(2);
        eventControls.setSpacing(10);
        viewEvent = new Button("View");
        addEvent = new Button("Add");
        viewEvent.setStyle("-fx-background-color: #48D1CC");
        addEvent.setStyle("-fx-background-color: #48D1CC");
        viewEvent.setPrefWidth(Double.MAX_VALUE);
        addEvent.setPrefWidth(Double.MAX_VALUE);
        eventControls.getChildren().addAll(viewEvent, addEvent);
          
        //add event listeners 
        MoveMonth monthAction = new MoveMonth();
        AddEvent newEvent = new AddEvent();
        nextMonth.setOnAction(monthAction);
        prevMonth.setOnAction(monthAction);
        addEvent.setOnAction(newEvent);
        
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
