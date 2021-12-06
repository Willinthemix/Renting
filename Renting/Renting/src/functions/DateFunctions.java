package functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateFunctions {


	public static Date StringDate(String dateString) {
		Date finalDate = null;


	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        finalDate  = dateFormat.parse(dateString);
	    }

	    catch(ParseException e){
	        e.printStackTrace();

	    }

	    return finalDate;
	}

	public static Date LocalDateDate(LocalDate dateLocal) {
		Date finalDate = null;
		
		
		try{
			finalDate = Date.from(dateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		return finalDate;
	}
	
	public static String GetDayofWeek(Date dateInput) {

		String dayName = "";
		
        Calendar c = Calendar.getInstance();
        c.setTime(dateInput);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
        switch(dayOfWeek) {
	        case 1: dayName =  "Sunday"	  ; break;
	        case 2: dayName =  "Monday"	  ; break;
	        case 3: dayName =  "Tuesday"  ; break;
	        case 4: dayName =  "Wednesday"; break;
	        case 5: dayName =  "Thursday" ; break;
	        case 6: dayName =  "Friday"   ; break;
	        case 7: dayName =  "Saturday" ; break;
	        
	        default: dayName =  "" ; break;
        }
        
		return dayName;
	}

	public static String GetDayType(Date dateInput) {

		String dayType = "";
		
        Calendar c = Calendar.getInstance();
        c.setTime(dateInput);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
        switch(dayOfWeek) {
	        case 1: 
	        case 7: 
	        	dayType =  "Weekend"  ; 
    		break;
	        case 2: 
	        case 3: 
	        case 4: 
	        case 5: 
	        case 6: 
				dayType = "Weekday";
			break;	        
        }
        
		return dayType;
	}
	
	public static boolean IsWeekDay(Date dateInput) {

		boolean validWeekDay = (GetDayType(dateInput) ==  "Weekday") ? true: false;
        
		return validWeekDay;
	}
	
	public static boolean IsWeekEndDay(Date dateInput) {
		
		boolean validWeekEndDay = (GetDayType(dateInput) ==  "Weekend") ? true: false;
		
		return validWeekEndDay;
	}
	
	
	public static Date DateAdjust (Date dateInput, int adjustment) {
		Date finalDate = null;
			
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInput);		
        cal.add(Calendar.DAY_OF_MONTH, adjustment);
        
        finalDate = cal.getTime();
        return finalDate;
	}

	public static int GetYear (Date dateInput) {
		int finalYear = 0;
			
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInput);		
                
        finalYear = cal.get(Calendar.YEAR);
        return finalYear;
	}
	

}
