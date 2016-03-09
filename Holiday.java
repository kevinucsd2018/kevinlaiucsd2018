package calendar;

import java.util.HashMap;

/**
 * Checks if a day is a holiday
 * @author Jonathan Chiu
 */
public class Holiday {
  protected HashMap<String, String> holidays = new HashMap<String, String>();


  /**
   * Check if a day is a holiday
   * @param month
   * @param day
   * @return true if date is holiday, false otherwise
   */
  public boolean isHoliday(int month, int day) {
    boolean isHoliday = false;
    String monthText = String.valueOf(month);
    String dayText = String.valueOf(day);
    String monthDayKey = monthText + "-" + dayText;
    
    //if a day is a holiday, return true
    if (holidays.containsKey(monthDayKey)) {
      isHoliday = true;
    }

    return isHoliday;
  }
  
  
  
  /**
   * Upload holidays into HashMap, constructor class
   */
  public Holiday() {
    
    //grab the year from the label
    int year = Integer.parseInt(Calendar.year.getText());
    int day;
    
    holidays.put("1-1", "New Year's Day");
    holidays.put("7-4", "Independence Day");
    holidays.put("11-11", "Veteran's Day");
    holidays.put("12-25", "Christmas");

    //add Thanksgiving: 4th thursday of november
    day = findHoliday(11, year, 4, 5);
    String thanksgiving = createKey(11, day);
    holidays.put(thanksgiving, "Thanksgiving");
    
    //add MLKJr day: 3rd monday in january
    day = findHoliday(1, year, 3, 2);
    String MLKJr = createKey(1, day);
    holidays.put(MLKJr, "Martin Luther King Jr. Day");
    
    //TESTING
    System.out.println("MLKJR: " + MLKJr + " day: " + day + " for " + year);
    
    //add president's day: 3rd monday in february
    day = findHoliday(2, year, 3, 2);
    String president = createKey(2, day);
    holidays.put(president, "President's Day");
    
    //add labor day: 1st monday in september
    day = findHoliday(9, year, 1, 2);
    String labor = createKey(9, day);
    holidays.put(labor, "Labor Day");
    
    //add columbus day: 2nd monday in october
    day = findHoliday(10, year, 2, 2);
    String columbus = createKey(10, day);
    holidays.put(columbus, "Columbus Day");
    
    //add mother's day: 2nd sunday in may
    day = findHoliday(1, year, 2, 1);
    String mother = createKey(5, day);
    holidays.put(mother, "Mother's Day");
    
    //add father's day: 3rd sunday in june
    day = findHoliday(1, year, 3, 1);
    String father = createKey(6, day);
    holidays.put(father, "Father's Day");
      
  }
  
  
  
  /**
   * Creates a key for the HashMap
   * @param month
   * @param day
   * @return 
   */
  public static String createKey(int month, int day) {
    String key = "";
    String monthText = String.valueOf(month);
    String dayText = String.valueOf(day);
    
    key = monthText + "-" + dayText;
    
    return key;
  
  }
  
  
  
  /**
   * @param month
   * @param year
   * @param week number
   * @param day 1 is Sunday, 2 is Monday... 7 is Saturday
   * @return the day of the month
   */
  public static int findHoliday(int month, int year, int week, int day) {
    int holiday = 0;
    int moreWeeks;
    int firstDay;
    
    //find when the month starts
    int offset = SetupTools.findDay(month, 1, year);
    
    //determine date of first occurence of a day
    firstDay = (day - offset + 7) % 7;
    
    //determine how many more days to add based on how many weeks
    moreWeeks = (week - 1) * 7;
    
    //determine date based on above two
    holiday = firstDay + moreWeeks;
    
    return holiday;
  }
}
