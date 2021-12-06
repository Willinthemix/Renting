package test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.ChainsawRental;
import data.JackhammerRental;
import data.LadderRental;
import functions.DateFunctions;

import static org.hamcrest.CoreMatchers.containsString;

class DemoTest {
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	void Test1_JAKR_20150903() {

		try {
			JackhammerRental rentJackhammmerT1 = new JackhammerRental("JAKR", "Ridgid");
			
			rentJackhammmerT1.CheckOut(5, DateFunctions.StringDate("09/3/2015"), 101);

			
		} catch (Exception e) {
			
			e.printStackTrace();
			String execptionMsg = e.getMessage();
			
			Assert.assertThat(execptionMsg, containsString("Discount is invalid."));

		}		
		
	}	
	
	@Test
	void Test2_LADW_20200702() {

		
		try {
			LadderRental rentLadderT2 			= new LadderRental("LADW", "Werner");
			rentLadderT2.CheckOut(3, DateFunctions.StringDate("07/2/2020"), 10);

		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool code: LADW"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool type: Ladder"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool brand: Werner"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Rental days: 3"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Check out date: 07/02/20"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Due date: 07/05/20"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Daily rental charge: $1.99"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Charge days: 2"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Pre-charge: 3.98"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount %: 10.00%"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount Amt: $0.40"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Final charge: $3.58"));
	}	

	@Test
	void Test3_CHNS_20150702() {
		try {
			ChainsawRental rentChainsawT3		= new ChainsawRental("CHNS", "Stihl");
			rentChainsawT3.CheckOut(5, DateFunctions.StringDate("07/2/2015"), 25);

		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool code: CHNS"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool type: Chainsaw"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool brand: Stihl"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Rental days: 5"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Check out date: 07/02/15"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Due date: 07/07/15"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Daily rental charge: $1.49"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Charge days: 3"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Pre-charge: 4.47"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount %: 25.00%"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount Amt: $1.12"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Final charge: $3.35"));
		
		
	}	
	
	@Test
	void Test4_JAKD_20150903() {
		try {
			JackhammerRental rentJackhammerT4 	= new JackhammerRental("JAKD", "DeWalt");
			rentJackhammerT4.CheckOut(6, DateFunctions.StringDate("09/3/2015"), 0);

		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool code: JAKD"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool type: Jackhammer"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool brand: DeWalt"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Rental days: 6"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Check out date: 09/03/15"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Due date: 09/09/15"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Daily rental charge: $2.99"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Charge days: 3"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Pre-charge: 8.97"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount %: 0.00%"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount Amt: $0.00"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Final charge: $8.97"));
		
	}	
	
	@Test
	void Test5_JAKR_20150702() {
		try {
			JackhammerRental rentJackhammerT5 	= new JackhammerRental("JAKR", "Ridgid");
			rentJackhammerT5.CheckOut(9, DateFunctions.StringDate("07/2/2015"), 0);

		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool code: JAKR"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool type: Jackhammer"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool brand: Ridgid"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Rental days: 9"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Check out date: 07/02/15"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Due date: 07/11/15"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Daily rental charge: $2.99"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Charge days: 6"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Pre-charge: 17.94"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount %: 0.00%"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount Amt: $0.00"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Final charge: $17.94"));
		
	}	
			
	@Test
	void Test6_JAKR_20200702() {
		try {
			JackhammerRental rentJackhammerT6 	= new JackhammerRental("JAKR", "Ridgid");
			rentJackhammerT6.CheckOut(4, DateFunctions.StringDate("07/2/2020"), 50);

		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool code: JAKR"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool type: Jackhammer"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Tool brand: Ridgid"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Rental days: 4"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Check out date: 07/02/20"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Due date: 07/06/20"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Daily rental charge: $2.99"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Charge days: 1"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Pre-charge: 2.99"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount %: 50.00%"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Discount Amt: $1.50"));
		Assert.assertThat(outputStreamCaptor.toString(), containsString("Final charge: $1.49"));
		
	}	
	
	
	
	
	@AfterEach
	public void tearDown() {
	    System.setOut(standardOut);
	}	
	
    


}
