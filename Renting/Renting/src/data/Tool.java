package data;


/**
 * Base Template class that all tools should extend.
 *
 * @author hawkinsw
 * @created 2021/11/28
 * 
 */



public interface Tool {
	
	public boolean chargeIfWeekday();
	public boolean chargeIfWeekend();
	public boolean chargeIfHoliday();
	
	public double getDailyCharge();
	public void setDailyCharge(double charge);
	
	public String getCode();
	public void setCode(String codeInput);
		
	public String getBrand();
	public void setBrand(String brandInput);
	
	public String getType();
	public void setType(String typeInput);
	
	public void setchargeWeekday (boolean chargeWeekday);
	public void setchargeWeekend (boolean chargeWeekend);
	public void setchargeHoliday (boolean chargeHoliday);
	
}
