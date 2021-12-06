package data;

import java.util.Date;
import java.util.Map;

import businessrules.Holidays;
import businessrules.ValidateRental;
import functions.DateFunctions;
import functions.NumberFunctions;
import functions.StringFunctions;


/**
 * ToolSpecs Template class that all individual should extend.
 *
 * @author hawkinsw
 * @created 2021/11/28
 * 
 */

public class ToolRental implements Tool {

	private String brand	= "";
	private String code		= "";
	private String type		= "";
	private String entity   = "";
	
	private Date checkoutDate = null;
	private int  checkoutDays = 0;

	// Set defaults for Items that won't change
	// Values will always be the same for this tools
	// Values should come from a DB; but hard code for now
	private double 	dailyCharge		= 0;
	private double  discount        = 0;
	
	private boolean chargeWeekday 	= false;
	private boolean chargeWeekend 	= false;
	private boolean chargeHoliday 	= false;

	// Create Calculated Properties
	private Date 	dueDate 	= null;
	private double 	discountAmt = 0;
	private double 	preTotal 	= 0;
	private double 	finalCharge = 0;
	private int 	chargeDays  = 0;
	
	private String errorMsg = "";
	

	
	@Override
	public boolean chargeIfWeekday() {
		
		return this.chargeWeekday;
	}

	@Override
	public boolean chargeIfWeekend() {
		
		return this.chargeWeekend;
	}

	@Override
	public boolean chargeIfHoliday() {
		
		return this.chargeHoliday;
	}

	@Override
	public double getDailyCharge() {
		return dailyCharge;
	}

	public String getDailyChargeReport() {
		return StringFunctions.MoneyString(dailyCharge);
	}

	@Override
	public String getCode() {
		
		return this.code;
	}

	@Override
	public String getType() {
		
		return this.type;
	}

	@Override
	public String getBrand() {
		
		return this.brand;
	}
 

	@Override
	public void setDailyCharge(double charge) {
		this.dailyCharge = charge;
	}

	
	@Override
	public void setBrand(String brandInput) {
		this.brand = brandInput;
	}

	@Override
	public void setCode(String codeInput) {
		
		this.code = codeInput;
		
	}

	@Override
	public void setType(String typeInput) {
		
		this.type = typeInput;
	}


	@Override
	public void setchargeWeekday(boolean chargeWeekday) {
		
		this.chargeWeekday = chargeWeekday;
	}


	@Override
	public void setchargeWeekend(boolean chargeWeekend) {
		
		this.chargeWeekend = chargeWeekend;
	}


	@Override
	public void setchargeHoliday(boolean chargeHoliday) {
		
		
		this.chargeHoliday = chargeHoliday;
	}



	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public String getCheckoutDateReport() {
		return StringFunctions.DateToStringYY(checkoutDate);
	}
	


	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}



	public int getCheckoutDays() {
		return checkoutDays;
	}



	public void setCheckoutDays(int checkoutDays) {
		this.checkoutDays = checkoutDays;
	}
	

	public double getDiscount() {
		return discount;
	}

	public String getDiscountReport() {
		return StringFunctions.PercentString(discount);
	}
	


	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setSpecs(String brand, String code, int checkoutDays, Date checkoutDate, double discount, 
			String entity, double dailyCharge, boolean chargeWeekday, boolean chargeWeekend, boolean chargeHoliday) {

		setBrand(brand);
		setCode(code);
		setCheckoutDays(checkoutDays);
		setCheckoutDate(checkoutDate);
		setDiscount(discount);
		setEntity(entity);
		setDailyCharge(dailyCharge);
		setchargeWeekday(chargeWeekday);
		setchargeWeekend(chargeWeekend);
		setchargeHoliday(chargeHoliday);

	}
	
	
	// Process and calculate what's needed
	public void ProcessCheckout() {

		try {
			ValidateRental.VaildateRentalObj(this);
			
			calChargeDays();
		
			calDueDate();
			
			calPreTotal();

			calDiscountAmt();

			calTotal();
			
			// Print the invoice
			printInvoice();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public void calChargeDays()  {

		
		Date workDate 			= new Date();
		Date checkoutDateLocal 	= this.checkoutDate;
		int  rentalDays 		= this.checkoutDays;
		int  rentalYear 		= DateFunctions.GetYear(checkoutDateLocal);
		
    	Map<String, Date> honoredHolidays = Holidays.getYearHolidays(rentalYear);
		
		// Iterate the Number of days 
		for(int i = 0; i < rentalDays; i ++) {

			// Generate new Workout Date
			workDate = DateFunctions.DateAdjust( checkoutDateLocal, i);
			
			
			// Increase Charge days when necessary
			if (ValidateRental.IsValidChargeDay (this, honoredHolidays, workDate)) {
				this.chargeDays ++;

			}
			
		}
		
		
				
	}
	
	public void calPreTotal() throws Exception {
		
		this.preTotal = this.chargeDays * this.dailyCharge;
		
	}

	
	public void calTotal()  {

		this.finalCharge =  this.preTotal - this.discountAmt;
	
	}
	
	public void calDiscountAmt() throws Exception {
		
		this.discountAmt =  ( ((double)this.discount / (double)100) ) * this.preTotal ; 
		
		this.discountAmt = NumberFunctions.DecimalRoundUp(this.discountAmt) ; 


	}
	
	public void calDueDate() throws Exception {
		
		this.dueDate = DateFunctions.DateAdjust(this.checkoutDate, this.checkoutDays);
	}



	public Date getDueDate() {
		return dueDate;
	}

	public String getDueDateReport() {
		return StringFunctions.DateToStringYY(dueDate);
	}
	

	public double getDiscountAmt() {
		return discountAmt;
	}

	public String getDiscountAmtReport() {
		return StringFunctions.MoneyString(discountAmt);
	}
	

	public double getPreTotal() {
		return preTotal;
	}

	public String getPreTotalReport() {
		return StringFunctions.MoneyString(preTotal);
	}


	public double getFinalCharge() {
		return finalCharge;
	}

	public String getFinalChargeReport() {
		return StringFunctions.MoneyString(finalCharge);
	}
	

	public void createInvoice(ToolRental obj) throws Exception {
		// Validate Rental Specs
		ValidateRental.VaildateRentalObj(obj);		
		
		
		if (errorMsg != "")
			throw new Exception("Print Invoice Error: \n Please resolve the following before resubmitting request" 
								+ errorMsg); 
		else
			printInvoice();
		
	}
	
	public void printInvoice() {

		System.out.println("Tool code: " 				+ getCode());
		System.out.println("Tool type: " 				+ getEntity()); 
		System.out.println("Tool brand: " 				+ getBrand());
		System.out.println("Rental days: " 				+ getCheckoutDays());
		System.out.println("Check out date: " 			+ getCheckoutDateReport());
		System.out.println("Due date: " 				+ getDueDateReport());
		System.out.println("Daily rental charge: " 		+ getDailyChargeReport());
		System.out.println("Charge days: " 				+ this.chargeDays);
		System.out.println("Pre-charge: " 				+ this.preTotal);
		System.out.println("Discount %: " 				+ getDiscountReport());
		System.out.println("Discount Amt: " 			+ getDiscountAmtReport());
		System.out.println("Final charge: "				+ getFinalChargeReport());	
		
		
		
	}



	public String getEntity() {
		return entity;
	}



	public void setEntity(String entity) {
		this.entity = entity;
	}

}
