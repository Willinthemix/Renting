package functions;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class NumberFunctions {


	public static double DecimalRoundUp(double  doubleInput) {
		double finalDouble = 0;
		String doubleStr   = "";
	     
		DecimalFormat df = new DecimalFormat("#.##");
		doubleStr = df.format(doubleInput);	
		df.setRoundingMode(RoundingMode.CEILING);
		doubleStr = df.format(doubleInput);	
		finalDouble = Double.parseDouble(doubleStr); 

		return finalDouble;
	}




}
