package calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Jonathan Chiu
 */
public class ReadEvent {
  protected static HashMap<String, String> events;
  
  public ReadEvent() {
    String fileLocation = "save.txt";
    String key;
    String value;
    String deleteTag = "<delete>";
    
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
      
      //begin reading in events
      Scanner readEvents = new Scanner(saveFile);
      
      while (readEvents.hasNextLine()) {
        key = readEvents.nextLine();
        value = readEvents.nextLine();
        
        //if value is <delete>, delete mapping
        if (value.equals(deleteTag)) {
          events.remove(key);
        }
        else {
          events.put(key, value);
        }
      }
    }
    catch (FileNotFoundException e) {}
    catch (IOException ioe) {}
  }
}
