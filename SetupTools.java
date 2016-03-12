package calendar;

import java.util.Calendar;



/**
 * Essential tools for creating a calendar.
 * @author Jonathan Chiu A12113428 jhc028@ucsd.edu
 * @author Kevin Lai A12012166 kelai@ucsd.edu
 */
public class SetupTools {
   
  
  
  /**
   * Gets the current month and year using the Date class.
   * @return array with month and year
   */
  public static int[] getCurrentDate() {
    int[] monthYear = new int[2];
    Calendar current = Calendar.getInstance();

    //assign today's month and year to array
    monthYear[0] = current.get(Calendar.MONTH) + 1;
    monthYear[1] = current.get(Calendar.YEAR);

    return monthYear;
  }
    
    
    
  /**Given a date, determine what day it falls on. Find the sum of the 
   * day, month, year and century offset, modulo 7.
   * 
   * 0 - Sunday
   * 1 - Monday
   * 2 - Tuesday
   * 3 - Wednesday
   * 4 - Thursday
   * 5 - Friday
   * 6 - Saturday
   * 
   * @param month
   * @param day
   * @param year
   * @return the day that the date falls on. Refer to key above.
   */
  public static int findDay(int month, int day, int year) {
      int dayOfWeek = 0; 

      int dayOffset = 0;
      int monthOffset = 0;
      int yearOffset = 0;
      int centuryOffset = 0;

      boolean isLeapYear;
      int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
      int avgMonth = 28; // 4 weeks of 7 days
      int remainingDays = 0;


      //calculate Century offset
      int century = year - (year % 100); //calc century

      if (century % 400 == 0) {
          centuryOffset = 6;
      }

      else if ((century - 100) % 400 == 0) {
          centuryOffset = 4;
      }

      else if ((century - 200) % 400 == 0) {
          centuryOffset = 2;
      }

      else if ((century - 300) % 400 == 0) {
          centuryOffset = 0;
      }


      //calculate year offset
      int lastTwoYearDigits = (year % 1000) % 100;
      isLeapYear = leapYear(year);

      //If it is a leap year, and it is Jan/Feb
      if ((isLeapYear && month == 1) || (isLeapYear && month == 2)) {
          yearOffset = ((lastTwoYearDigits + (lastTwoYearDigits / 4)) % 7) - 1;
      }
      //ordinary years
      else {
          yearOffset = lastTwoYearDigits + (lastTwoYearDigits / 4) % 7;
      }


      //calculate month offset
      if ((month - 1) == 0) {
          monthOffset = 0; //january
      }
      else if ((month - 1) == 1) {
          monthOffset = 3; //february
      }
      else {
          //all other months after february
          for (int i = 3; i <= month; i++) { //calc remainingDays
              remainingDays += daysInMonth[i - 3] - avgMonth;
              remainingDays = remainingDays % 7;
          }

          monthOffset = (daysInMonth[month - 2] + remainingDays) % 7;
      }


      //calculate day offset
      dayOffset = day % 7;

      //calculate what day the date falls on
      dayOfWeek = (centuryOffset + yearOffset + monthOffset + dayOffset) % 7;
      return dayOfWeek;
  }
    
  
  
  /**
   * Determines whether a given year is a leap year. A leap year is a year
   * that is divisible by 4, but not 100, except if it is divisible by 400.
   * @param year
   * @return true if the year is a leap year, false otherwise.
   */
  public static boolean leapYear(int year) {
    boolean leapYear = false;
    boolean divisibleBy4 = (year % 4 == 0);
    boolean divisibleBy100 = (year % 100 == 0);
    boolean divisibleBy400 = (year % 400 == 0);

    //year is a leap year
    if ((divisibleBy4 && !divisibleBy100) || divisibleBy400) {
        leapYear = true;
    }
    //year is not a leap year
    else {
        leapYear = false;
    }

    return leapYear;
  }
    
    
    
  /**
   * Finds the total number of days in a given month.
   * @param month
   * @param year
   */
  public static int findTotalDays(int month, int year) {
    int[] numDays = {31, 28, 31, 30, 31, 30, 31, 31, 30 ,31, 30, 31};
    int totalDays = 0;
    boolean leapYear = false;

    //determine if a year is leap year
    leapYear = leapYear(year);

    //February in leap years have 29 days
    if (leapYear && (month == 2)) {
        totalDays = numDays[month - 1] + 1;
    }

    //all other months
    else {
        totalDays = numDays[month - 1];
    }
    
    return totalDays;
  }
}
