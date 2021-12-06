package functions;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import data.ChainsawRental;
import data.JackhammerRental;
import data.LadderRental;

public class StringFunctions {


	public static String Substitute(String stringTemplate, ArrayList<String> valueList) {
		String finalString = stringTemplate;
		int i = 0;
		
		// Loop to exhaust all Substitutions; Using Needle and haystack
		for (String SubValue : valueList) {
			
			String needle = "&" + String.valueOf(i);

			finalString = finalString.replaceAll(needle, escapeMetaCharacters(SubValue));
			i++;
		}
			
			
		
		return finalString;
	}

	public static String escapeMetaCharacters(String inputString){
	    final String[] metaCharacters = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&","%"};

	    for (int i = 0 ; i < metaCharacters.length ; i++){
	        if(inputString.contains(metaCharacters[i])){
	            inputString = inputString.replace(metaCharacters[i],"\\"+metaCharacters[i]);
	        }
	    }
	    return inputString;
	}	
	
	public static String DateToStringYY(Date dateInput) {

		return DateToString(dateInput, true);
	}
	
	public static String DateToStringYYYY(Date dateInput) {
		return DateToString(dateInput, false);
	}

	public static String DateToString(Date dateInput, boolean twoDigitYear) {
		String finalDate   ="";
		String datePattern = (twoDigitYear) ? "MM/dd/yy": "MM/dd/yyyy";
		
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date according to the chosen pattern
		DateFormat dateFormat = new SimpleDateFormat(datePattern);		
		
		finalDate = dateFormat.format(dateInput) ;
		return finalDate;
	}
	
	
	public static String PercentString(double dubInput) {
		// use DecimalFormat
		return DoubleString (dubInput, new DecimalFormat("##0.00")) + "%" ;
	}
	
	public static String MoneyString(double dubInput) {
		
		return "$" +  DoubleString (dubInput, new DecimalFormat("##0.00")) ;
	}
	
	
	public static String DoubleString(double dubInput, DecimalFormat decimalFormat) {

		String finalString = "";
		
		finalString = decimalFormat.format(dubInput);
		
		return finalString ;

		
	}


	
	public static boolean ValidateListString(String needle, ArrayList<String> haystack) {
		
		// Convert for Easy Processing
		Set<String> set =  new HashSet<String>(haystack);

		return set.contains(needle);
	}
	
	
}
