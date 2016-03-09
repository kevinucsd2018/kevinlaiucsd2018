package calendar;

import java.io.File;
import java.io.FileNotFoundException;
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
    
    //initialize HashMap
    events = new HashMap<String, String>();
    
    //try to read in save file
    try {
      File saveFile = new File(fileLocation);
      Scanner readEvents = new Scanner(saveFile);
      
      while (readEvents.hasNextLine()) {
        key = readEvents.nextLine();
        value = readEvents.nextLine();
        events.put(key, value);
      }
    
    }
    catch (FileNotFoundException e) {
      
    }
  }
}
