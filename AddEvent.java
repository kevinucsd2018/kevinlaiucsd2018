package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jonathan Chiu
 */
public class AddEvent implements EventHandler<ActionEvent> {
    protected String day;
    protected String month;
    protected String year;
    protected TextField dayStart;
    protected TextField monthStart;
    protected TextField yearStart;
    protected TextField timeStart;
    protected TextField dayEnd;
    protected TextField monthEnd;
    protected TextField yearEnd;
    protected TextField timeEnd;
    protected TextField titleText;
    protected TextField AMPMStart;
    protected TextField AMPMEnd;
    
    
    
    /**
     * Displays the GridPane with the new event form.
     * @param e 
     */
    @Override
    public void handle(ActionEvent e) {
        //grab currently selected date
        day = Calendar.day.getText();
        month = Calendar.month.getText();
        year = Calendar.year.getText();
        
        //initialize all required items
        dayStart = new TextField(day);
        monthStart = new TextField(month);
        yearStart = new TextField(year);
        timeStart = new TextField();
        dayEnd = new TextField(day);
        monthEnd = new TextField(month);
        yearEnd = new TextField(year);
        timeEnd = new TextField();
        titleText = new TextField();
        Label addNew = new Label("Add New Event");
        Label title = new Label("Title: ");
        Label start = new Label("Starts: ");
        Label end = new Label("Ends: ");
        GridPane eventGrid = new GridPane();
        AMPMStart = new TextField();
        AMPMEnd = new TextField();
        
        //set event listener to button
        Button submit = new Button("Submit");
        HandleEvent processEvent = new HandleEvent();
        submit.setOnAction(processEvent);
        
        //set prompt text
        titleText.setPromptText("Untitled Event");
        monthStart.setPromptText("March");
        monthEnd.setPromptText("March");
        dayStart.setPromptText("01");
        dayEnd.setPromptText("01");
        yearStart.setPromptText("2016");
        yearEnd.setPromptText("2016");
        timeStart.setPromptText("8:00");
        timeEnd.setPromptText("9:00");
        AMPMStart.setPromptText("AM");
        AMPMEnd.setPromptText("PM");
        
        //add everything to GridPane
        eventGrid.add(addNew, 1, 0, 1, 3);
        eventGrid.add(title, 0, 1);
        eventGrid.add(start, 0, 2);
        eventGrid.add(end, 0, 3);
        eventGrid.add(submit, 2, 4);
        
        eventGrid.add(titleText, 1, 1, 3, 1);
        eventGrid.add(monthStart, 1, 2);
        eventGrid.add(dayStart, 2, 2);
        eventGrid.add(yearStart, 3, 2);
        eventGrid.add(timeStart, 4, 2);
        eventGrid.add(AMPMStart, 5, 2);
        
        eventGrid.add(monthEnd, 1, 3);
        eventGrid.add(dayEnd, 2, 3);
        eventGrid.add(yearEnd, 3, 3);
        eventGrid.add(timeEnd, 4, 3);
        eventGrid.add(AMPMEnd, 5, 3);
        
        //replace old GridPane with this one
        Calendar.app.setCenter(eventGrid);
    }
    
    
    
    /**
     * Inner class that listens to the "Add Event" button
     */
    public class HandleEvent implements EventHandler<ActionEvent> {
        protected String title;
        protected String monthBegin;
        protected String dayBegin;
        protected String yearBegin;
        protected String timeBegin;
        protected String AMPMBegin;
        protected String monthFinish;
        protected String dayFinish;
        protected String yearFinish;
        protected String timeFinish;
        protected String AMPMFinish;
        
        
        
        /**
         * Checks and saves the data in the new event form.
         * @param e 
         */
        @Override
        public void handle(ActionEvent e) {
            
            //perform checks to ensure all fields are filled out
            title = titleText.getText();
            monthBegin = monthStart.getText();
            dayBegin = dayStart.getText();
            yearBegin = yearStart.getText();
            timeBegin = timeStart.getText();
            AMPMBegin = AMPMStart.getText();
            
            monthFinish = monthEnd.getText();
            dayFinish = dayEnd.getText();
            yearFinish = yearEnd.getText();
            timeFinish = timeEnd.getText();
            AMPMFinish = AMPMEnd.getText();
            Alert errorAlert;
            
            //no title
            if (title.equals("")) {
                //alert
                errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No event title specified: ");
                errorAlert.setContentText("Please name your event.");
            
                errorAlert.showAndWait();
                return;
            }
            //no month
            if (monthBegin.equals("") || monthFinish.equals("")) {
                //alert
                errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No event month specified: ");
                errorAlert.setContentText("Please specify a month.");
            
                errorAlert.showAndWait();
                return;
            }
            //no day
            if (dayBegin.equals("") || dayFinish.equals("")) {
                //alert
                errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No event day specified: ");
                errorAlert.setContentText("Please specify a day.");
            
                errorAlert.showAndWait();
                return;
            }
            //no year
            if (yearBegin.equals("") || yearFinish.equals("")) {
                //alert
                errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No event year specified: ");
                errorAlert.setContentText("Please specify a year.");
            
                errorAlert.showAndWait();
                return;
            }
            //no time
            if (timeBegin.equals("") || timeBegin.equals("")) {
                //alert
                errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No event time specified: ");
                errorAlert.setContentText("Please specify a time.");
            
                errorAlert.showAndWait();
                return;
            }
            //no AM/PM
            if (AMPMBegin.equals("") || AMPMFinish.equals("")) {
                //alert
                errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("AM/PM not specified: ");
                errorAlert.setContentText("Please specify whether the event is in the morning or night");
            
                errorAlert.showAndWait();
                return;
            }
            
            
            //return to calendar view
            int month = Integer.parseInt(Calendar.monthNum.getText());
            int year = Integer.parseInt(Calendar.year.getText());
            SetupGUI backToCal = new SetupGUI(month, year);
            Calendar.app.setCenter(backToCal.calendarSpace);
 
        }
    }
}
