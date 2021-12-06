package data;

import java.util.ArrayList;
import java.util.Date;

import functions.StringFunctions;

/**
 * Base class that all tools should extend.
 *
 * @author hawkinsw
 * @return 
 * @created 2021/11/28
 */

public class ChainsawRental extends ToolRental{

	private String  brand	= "";
	private String 	code	= "";

	// Set defaults for Items that won't change
	// Values will always be the same for this tools
	// Values should come from a DB; but hard code for now
    ArrayList<String> codeList 	= new ArrayList<String>() {{ add("CHNS"); }};
    ArrayList<String> brandList = new ArrayList<String>() {{ add("Stihl"); }};
	
	
	private final double 	dailyCharge 	= 1.49;

	private final boolean 	chargeWeekday 	= true;
	private final boolean 	chargeWeekend	= false;
	private final boolean 	chargeHoliday	= true;
	
	private final String 	entity		= "Chainsaw";
	

	public ChainsawRental(String code, String brand ) throws Exception {
	
		
		this.brand = brand;
		this.code  = code;
		
		ValidateCore();
		
			
	}
	
	public void CheckOut(int checkoutDays, Date checkoutDate,  double discount) throws Exception {
		
		
		setSpecs(this.brand, this.code, checkoutDays, checkoutDate, discount, this.entity, 
				this.dailyCharge, this.chargeWeekday, this.chargeWeekend, this.chargeHoliday);
		
		ProcessCheckout();
		
	}

	public void ValidateCore() throws Exception {
		
		
		// Sanity Check Code and brand
		if(!StringFunctions.ValidateListString(brand, brandList)) {
			
			throw new Exception("Brand " + brand + "is invalid.");
		}

		if(!StringFunctions.ValidateListString(code, codeList)) {
			throw new Exception("Code " + code + "is invalid."); 
		}

	}
	
	
	
	
	
}
