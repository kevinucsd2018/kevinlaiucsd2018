package calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 * Store events from save.txt into a HashMap
 * @author Jonathan Chiu A12113428 jhc028@ucsd.edu
 * @author Kevin Lai A12012166 kelai@ucsd.edu
 */
public class ReadEvent {
  protected static HashMap<String, String> events;
  
  
  
  /**
   * Open save.txt and upload events onto a HashMap.
   */
  public ReadEvent() {
    String fileLocation = "save.txt";
    String key;
    String value;
    String deleteTag = "<delete>";
    String errorTitle = "Uh oh!";
    String errorHeader = "There was a problem with reading events.";
    String errorMsg = "Please try again.";
    
    //initialize HashMap
    events = new HashMap<String, String>();
    
    //try to read in save file
    try {
      File saveFile = new File(fileLocation);
      
      //if file does not exist, create it and exit
      if (!saveFile.exists()) {
        saveFile.createNewFile();
        return;
      }
      
      //begin reading in events, if file exists
      Scanner readEvents = new Scanner(saveFile);
      
      //begin uploading events onto hashmap
      while (readEvents.hasNextLine()) {
        key = readEvents.nextLine();
        value = readEvents.nextLine();
        
        //if value is <delete>, delete mapping
        if (value.equals(deleteTag)) {
          events.remove(key);
        }
        //otherwise, add to hashmap
        else {
          events.put(key, value);
        }
      }
    }
    catch (FileNotFoundException e) {
      //display alert message
      Alert errorAlert = new Alert(Alert.AlertType.ERROR);
      errorAlert.setTitle(errorTitle);
      errorAlert.setHeaderText(errorHeader);
      errorAlert.setContentText(errorMsg);
      errorAlert.showAndWait();
      return;
    }
    catch (IOException ioe) {
      //display alert message
      Alert errorAlert = new Alert(Alert.AlertType.ERROR);
      errorAlert.setTitle(errorTitle);
      errorAlert.setHeaderText(errorHeader);
      errorAlert.setContentText(errorMsg);
      errorAlert.showAndWait();
      return;
    }
  }
}
