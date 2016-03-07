package calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
        Button submit = new Button("Submit");
        GridPane eventGrid = new GridPane();
        
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
        
        eventGrid.add(monthEnd, 1, 3);
        eventGrid.add(dayEnd, 2, 3);
        eventGrid.add(yearEnd, 3, 3);
        eventGrid.add(timeEnd, 4, 3);
        
        //replace old GridPane with this one
        Calendar.app.setCenter(eventGrid);
        return;
    }
}
