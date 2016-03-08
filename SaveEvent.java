package calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;

/**
 * Saves an event.
 * @author Jonathan Chiu
 */
public class SaveEvent {
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
     * Constructor that saves the event
     * @param eventDetails must have 11 elements
     */
    public SaveEvent(String[] eventDetails) {
        boolean created = false;
        title = eventDetails[0];
        monthBegin = eventDetails[1];
        dayBegin = eventDetails[2];
        yearBegin = eventDetails[3];
        timeBegin = eventDetails[4];
        AMPMBegin = eventDetails[5];
        
        monthFinish = eventDetails[6];
        dayFinish = eventDetails[7];
        yearFinish = eventDetails[8];
        timeFinish = eventDetails[9];
        AMPMFinish = eventDetails[10];
        
        try {
            String filePath = "events/" + monthBegin + ".txt";
            File save = new File(filePath);
            
            //create file if it does not exist
            if (!save.exists()) {
                save.createNewFile();
                created = true;
            }
            
            //create filewriter and bufferedwriter
            FileWriter fw = new FileWriter(save.getAbsolutePath(), created);
            BufferedWriter buffAdd = new BufferedWriter(fw);
            
            //write to file
            buffAdd.write("");
            buffAdd.close();
            
            
        }
        catch (IOException e) {
            //alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error with adding event.");
            errorAlert.setContentText("Please try again.");

            errorAlert.showAndWait();
            return;
        }
    }  
}
