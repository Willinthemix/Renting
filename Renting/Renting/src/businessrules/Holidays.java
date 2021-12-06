package businessrules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

import functions.DateFunctions;
import functions.StringFunctions;

public class Holidays {


	
	private final static String labDatePrefix = "09/01/";
	private final static String indDatePrefix = "07/04/";
	
	public static Map<String, Date> getYearHolidays(int year) {
		Map<String, Date> honoredHolidays = new HashMap<String, Date>();
		Date honeredIndDay;
		Date honeredLaborDay;
		
		honeredIndDay 	= configureIndDate(year);
		honeredLaborDay = configureLabDate(year);
		
		
		honoredHolidays.put("IndependenceDay", honeredIndDay);
		honoredHolidays.put("LaborDay", honeredLaborDay);
		return honoredHolidays;
	}
	
	
	public static Date configureIndDate (int year) {
		Date finalDate = null;
		
		// Adjust to the July 4th 
		String stringDate = indDatePrefix + String.valueOf(year);
		Date   indDate	  = DateFunctions.StringDate(stringDate);

		String dayOfWeek = DateFunctions.GetDayofWeek(indDate);
		
		// If 4th is on the weekend adjust to a biz day
		switch (dayOfWeek) {
			case "Saturday": finalDate = DateFunctions.DateAdjust(indDate, -1); break;
			case "Sunday"  : finalDate = DateFunctions.DateAdjust(indDate, 1); break;
			default: finalDate = indDate; 
		}

		return finalDate;
	}

	public static Date configureLabDate (int year) {
		Date finalDate = null;
		
		// Adjust to the Month of September 
		String labDate = labDatePrefix + String.valueOf(year);
		
		// Find First Monday in Month and perform necessary conversions
		finalDate = DateFunctions.LocalDateDate(FindFirstMonday(DateFunctions.StringDate(labDate))); 
		return finalDate;
	}

	public static LocalDate FindFirstMonday(Date dateInput) {
		Date finalDate = null;

		String dateString = StringFunctions.DateToStringYYYY(dateInput);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	    LocalDate dateTime = LocalDate.parse(dateString, formatter);

	
		LocalDate firstMonday = dateTime.with(firstInMonth(DayOfWeek.MONDAY)); //2015-11-02 (Monday)
		return firstMonday;
	}
}



