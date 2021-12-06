package businessrules;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import data.ToolRental;
import functions.DateFunctions;
import functions.StringFunctions;

public class ValidateRental {


	public static String    toolCode 	  = "";
	public static String    toolType 	  = "";
	public static String    toolBrand	  = "";
	public static int       rentalDays   = 0;
	public static Date      rentalDate   = null;
	public static Double    discount     = null;
	
	
	public static void VaildateRentalObj(ToolRental obj) throws Exception {
		toolCode 	 = obj.getCode();
		toolType 	 = obj.getEntity();
		toolBrand	 = obj.getBrand();
		rentalDays   = obj.getCheckoutDays();
		rentalDate   = obj.getCheckoutDate();
		discount     = obj.getDiscount();
		
		VaildateRental();
		
	}
	
	public static void VaildateRental( ) throws Exception {
		String errMsg = "";
		
		boolean errdiscount =  (discount   < 0 || discount > 100) ? true : false;
		
		
		// Validate and add to Error Message for String Validations
		// Assignments below uses ternary to evaluate and Create Error Message Method 
		// to create the overall error Message separated by Line Feed
		errMsg = (toolCode   == "") ? CreateErrMsg(errMsg, toolType, "Code"  		 , toolCode  ) : "";
		errMsg = (toolType   == "") ? CreateErrMsg(errMsg, toolType, "Type"  		 , toolType  ) : "";
		errMsg = (toolBrand  == "") ? CreateErrMsg(errMsg, toolType, "Brand" 		 , toolBrand ) : "";

		
		// Validate and add to Error Message for Other Validations
		errMsg = (rentalDays < 0) 		?  CreateErrMsg(errMsg, toolType, "Rental Days" , String.valueOf(rentalDays)) : ""; 
		errMsg = (rentalDate == null)	?  CreateErrMsg(errMsg, toolType, "Rental Date" , "Null") : ""; 
		errMsg = (errdiscount ) 		?  CreateErrMsg(errMsg, toolType, "Discount"    , StringFunctions.PercentString(discount )) : "";  
		
		
		if (errMsg != "")
			throw new Exception("Validation Error: \n" + errMsg);
		
	}
	
	public static String CreateErrMsg(String currentErrMsg, String entityString, String propString, String valueString) {
	
		// Create a template string 
		String errMsg = "Value of &0 for &1 &2 is invalid.";
		String finalMsg = "";
		
		// Add Substitute Values to ArrayList Object
        ArrayList<String> errValues = new ArrayList<String>() {
            {
            	add(valueString);
                add(entityString);
                add(propString);
            }
        };		
		
		// Finally create Error Msg Appropriately
		errMsg = StringFunctions.Substitute(errMsg, errValues);
		finalMsg = (currentErrMsg == "") ? errMsg : currentErrMsg + "\n" + errMsg;
		
		
		return finalMsg;
	}

	public static boolean IsValidChargeDay(ToolRental toolRental, Map<String, Date> honoredHolidays, Date currentDate) {

		boolean validWorkdate = true;
		
		// Exclude date base on Rules 
		ChargeDayBlock:{
			
			// Holiday Check
			if ((!toolRental.chargeIfHoliday()) && 
					(currentDate.equals(honoredHolidays.get("LaborDay")) || currentDate.equals(honoredHolidays.get("IndependenceDay")))) {
			
				validWorkdate =  false ;
				
				break ChargeDayBlock;
			}
			
			// Weekday Check
			if ((!toolRental.chargeIfWeekday()) &&  DateFunctions.IsWeekDay(currentDate) ) {
	
				validWorkdate =  false ;
				
				break ChargeDayBlock;
			
			}

			if ((!toolRental.chargeIfWeekend()) &&  DateFunctions.IsWeekEndDay(currentDate) ) {
				
				validWorkdate =  false ;
				
				break ChargeDayBlock;
				
			}
		}

		return validWorkdate;
	}
	

	
	
}



