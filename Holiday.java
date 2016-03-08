package calendar;

import java.util.HashMap;

/**
 * Checks if a day is a holiday
 * @author Jonathan Chiu
 */
public class Holiday {
  protected static HashMap<String, String> holidays = new HashMap<String, String>();


  /**
   * Check if a day is a holiday
   * @param month
   * @param day
   * @return true if date is holiday, false otherwise
   */
  public static boolean isHoliday(int month, int day) {
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
   * Upload holidays into HashMap
   */
  protected static void uploadHolidays() {
    
    //grab the year from the label
    int year = Integer.parseInt(Calendar.year.getText());
    
    holidays.put("1-1", "New Year's Day");
    holidays.put("7-4", "Independence Day");
    holidays.put("11-11", "Veteran's Day");
    holidays.put("12-25", "Christmas");

    //TESTING: determine when thanksgiving is: 4th thursday of november
    int day = findHoliday(11, year, 4, 5);
    String thanksgiving = "11-" + String.valueOf(day);
    holidays.put(thanksgiving, "Thanksgiving");
    
  }
  
  
  
  /**
   * @param month
   * @param year
   * @param week number
   * @param day 1 is sunday, 2 is monday... 7 is saturday
   * @return the day of the month
   */
  public static int findHoliday(int month, int year, int week, int day) {
    int holiday = 0;
    int moreWeeks;
    int firstDay;
    
    //find when the month starts
    int offset = SetupTools.findDay(month, 1, year);
    
    //determine date of first occurence of a day
    firstDay = day - offset % 7;
    
    
    //determine how many more days to add based on how many weeks
    moreWeeks = (week - 1) * 7;
    
    //determine date based on above two
    holiday = firstDay + moreWeeks;
    
    return holiday;
  }
}
