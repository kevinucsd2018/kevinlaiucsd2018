/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import java.awt.Color;


/**
 *
 * @author Jonathan
 */
public class Calendar extends Application {
    private GridPane calendarSpace;
    private TextField[] daySpaces;
    
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Calendar");
        
        //set up gridpane with predefined widths and heights
        calendarSpace = new GridPane();
        //seven columns
        for (int i = 0; i < 7; i++) {
            calendarSpace.getColumnConstraints().add(new ColumnConstraints(80));
        }
        //five rows
        for (int i = 0; i < 5; i++) {
            calendarSpace.getRowConstraints().add(new RowConstraints(60));
        }
        
        
        
        //set up each box in the grid
        daySpaces = new TextField[35];
        int col = 0; //each day
        int row = 0; //each row
        
        for (int i = 1; i < daySpaces.length; i++) {

            daySpaces[i] = new TextField("" + i);
            calendarSpace.add(daySpaces[i], col, row, 1, 1);
            
            col++;
            
            if (col % 7 == 0) {
                col = 0;
                row++;
            
            }
            
        }
        
        Scene scene = new Scene(calendarSpace, 600, 500);
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
