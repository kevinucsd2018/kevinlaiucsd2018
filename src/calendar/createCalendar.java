package calendar;
import java.util.Calendar;
import java.util.Date;

/**
 * Essential tools for creating a calendar.
 * @author Jonathan
 * @version 1.0
 * @date 2/28/2016
 */
public class createCalendar {
    
    /**
     * Gets the current month and year using the Date class.
     * @return array with month and year
     */
    public int[] getCurrentDate() {
        int[] monthYear = new int[2];
        Calendar current = Calendar.getInstance();
        
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
    public int findDay(int month, int day, int year) {
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
     * Determines whether the given year is a leap year.
     * @param year
     * @return true if the year is a leap year, false otherwise.
     */
    public boolean leapYear(int year) {
        boolean leapYear = false;
        boolean divisibleBy4 = (year % 4 == 0);
        boolean divisibleBy100 = (year % 100 == 0);
        boolean divisibleBy400 = (year % 400 == 0);
                
        
        if ((divisibleBy4 && !divisibleBy100) || divisibleBy400) {
            leapYear = true;
        }
        else {
            leapYear = false;
        }
      
        return leapYear;
    }
    
    public static void main(String[] args) {
        int whatDay = 0;
        int month = Integer.parseInt(args[0]);
        int day = Integer.parseInt(args[1]);
        int year = Integer.parseInt(args[2]);
        
        createCalendar cal = new createCalendar();
        
        whatDay = cal.findDay(month, day, year);
        System.out.println(whatDay);
    
    }
    
}
