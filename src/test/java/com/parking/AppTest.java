package com.parking;

import org.junit.Before;

import com.parking.command.Command;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	String paramNoLot[];
	String paramWithLotInt[];
	String paramWithLotNotInt[];
	
	String paramsTesParkingNoParams[];
	String paramsTesParkingParamLessThan2[];
	String paramsTesParkingParamMoreThan2[];
	String paramsTesParkingParamEqual2[];
	
	String paramsTesStatus[];
	
	String paramsTesGetRegistNumberWithNoColor[];
	String paramsTesGetRegistNumberWithColorAvail[];
	String paramsTesGetRegistNumberWithColorNoAvail[];
	
	String paramsTesGetSlotNumberWithNoColor[];
	String paramsTesGetSlotNumberWithColorAvail[];
	String paramsTesGetSlotNumberWithColorNoAvail[];
	
	String paramsTesGetSlotNumberWithNoRegNo[];
	String paramsTesGetSlotNumberWithRegNoAvail[];
	String paramsTesGetSlotNumberWithRegNoNotAvail[];
	
	String paramsTesLeaveNoData[];
	String paramsTesLeaveNoDataAvail[];
	String paramsTesLeaveDataAvail[];

	String expectedFalse = "Expected False but result is True";
	String expectedTrue= "Expected True but result is False";

	public void setUp(){
		paramNoLot = new String[] { "create_parking_lot"};
		paramWithLotInt = new String[] { "create_parking_lot", "1" };
		paramWithLotNotInt = new String[] { "create_parking_lot", "f" };
		
		paramsTesParkingNoParams = new String[] { "park" };
		paramsTesParkingParamLessThan2  = new String[] { "park","KA-01-HH-2701"};
		paramsTesParkingParamMoreThan2 = new String[] { "park","KA-01-HH-2701","KA-01-HH-3141","Black"};
		paramsTesParkingParamEqual2 = new String[] { "park","KA-01-HH-2701","Black"};
		
		paramsTesStatus = new String[] { "status" };
		
		paramsTesGetRegistNumberWithNoColor = new String[] { "registration_numbers_for_cars_with_colour" };
		paramsTesGetRegistNumberWithColorAvail = new String[] { "registration_numbers_for_cars_with_colour","Black"};
		paramsTesGetRegistNumberWithColorNoAvail = new String[] { "registration_numbers_for_cars_with_colour", "Red" };
		
		paramsTesGetSlotNumberWithNoColor = new String[] { "slot_numbers_for_cars_with_colour" };
		paramsTesGetSlotNumberWithColorAvail = new String[] { "slot_numbers_for_cars_with_colour","Black"};
		paramsTesGetSlotNumberWithColorNoAvail = new String[] { "slot_numbers_for_cars_with_colour", "Red" };
		
		paramsTesGetSlotNumberWithNoRegNo = new String[] { "slot_number_for_registration_number" };
		paramsTesGetSlotNumberWithRegNoAvail = new String[] { "slot_number_for_registration_number","KA-01-HH-2701"};
		paramsTesGetSlotNumberWithRegNoNotAvail = new String[] { "slot_number_for_registration_number", "KA-03-WW-4000" };
		
		paramsTesLeaveNoData = new String[] { "leave" };
		paramsTesLeaveDataAvail= new String[] { "leave","1" };
		paramsTesLeaveNoDataAvail= new String[] { "leave","3" };
		
	}
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	

	/**
	 * Test Method create Parking lot 
	 * 		return false 
	 * 			if params with no lot
	 * 			if params with lot but not int
	 * 		return True 
	 * 			if params with lot
	 */
	public void test_createParkingLot() {
		assertFalse(expectedFalse,Command.getTableParking().execute(paramNoLot));
		assertFalse(expectedFalse,Command.getTableParking().execute(paramWithLotNotInt));
		
		assertTrue(expectedTrue,Command.getTableParking().execute(paramWithLotInt));
	}
	
	
	/**
	 * Test Method Parking
	 * 		return false
	 * 			if params with no params
	 * 			if params with param less than 2
	 * 			if params with param more than 2
	 * 			if parking full
	 * 		return true
	 * 			if params with param  equal 2
	 */
	public void test_parking(){
		
		assertFalse(expectedFalse, Command.getTableParking().execute(paramsTesParkingNoParams));
		assertFalse(expectedFalse, Command.getTableParking().execute(paramsTesParkingParamLessThan2));
		assertFalse(expectedFalse, Command.getTableParking().execute(paramsTesParkingParamMoreThan2));
		
		assertTrue(expectedTrue,Command.getTableParking().execute(paramWithLotInt));
		assertTrue(expectedTrue, Command.getTableParking().execute(paramsTesParkingParamEqual2));
		
		assertFalse(expectedFalse, Command.getTableParking().execute(paramsTesParkingParamEqual2));
	}
	

	/**
	 * Test Method Status
	 * 		return false
	 * 			if no Data available
	 * 		return true
	 * 			if data available
	*/
	public void test_status(){
		assertTrue(expectedTrue,Command.getTableParking().execute(paramWithLotInt));
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesStatus));
		
		assertTrue(expectedTrue, Command.getTableParking().execute(paramsTesParkingParamEqual2));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesStatus));
	} 
	
	/**
	 * Test Method getRegistrationNumberByColor
	 * 		return false
	 * 			if no data available
	 * 			if no param color
	 * 		return true
	 * 			if data available
	 */
	public void test_getRegistrationNumberByColour(){
		assertTrue(expectedTrue,Command.getTableParking().execute(paramWithLotInt));
		assertTrue(expectedTrue, Command.getTableParking().execute(paramsTesParkingParamEqual2));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesStatus));
		
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesGetRegistNumberWithNoColor));
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesGetRegistNumberWithColorNoAvail));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesGetRegistNumberWithColorAvail));
	}
	
	/**
	 * Test Method getSlotNumberByColor
	 * 		return false
	 * 			if no data available
	 * 			if no param color
	 * 		return true
	 * 			if data available
	 */
	public void test_getSlotNumberByColor(){
		assertTrue(expectedTrue,Command.getTableParking().execute(paramWithLotInt));
		assertTrue(expectedTrue, Command.getTableParking().execute(paramsTesParkingParamEqual2));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesStatus));
		
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesGetSlotNumberWithNoColor));
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesGetSlotNumberWithColorNoAvail));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesGetSlotNumberWithColorAvail));
	}
	
	/**
	 * Test Method getSlotNumberByReqNumber
	 * 		return false
	 * 			if no data available
	 * 			if no param Req Number
	 * 		return true
	 * 			if data available
	 */
	public void test_getSlotNumberByRegNumber(){
		assertTrue(expectedTrue,Command.getTableParking().execute(paramWithLotInt));
		assertTrue(expectedTrue, Command.getTableParking().execute(paramsTesParkingParamEqual2));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesStatus));

		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesGetSlotNumberWithNoRegNo));
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesGetSlotNumberWithRegNoNotAvail));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesGetSlotNumberWithRegNoAvail));
	}
	
	/**
	 * Test Method leave
	 * 		return false
	 * 			if no data available
	 * 			if no param slot no
	 * 		return true
	 * 			if data slot no available and there is record
	 */
	public void test_leave(){
		assertTrue(expectedTrue,Command.getTableParking().execute(paramWithLotInt));
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesLeaveNoData));
		
		assertTrue(expectedTrue, Command.getTableParking().execute(paramsTesParkingParamEqual2));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesStatus));
		
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesLeaveNoData));
		assertFalse(expectedFalse,Command.getTableParking().execute(paramsTesLeaveNoDataAvail));
		assertTrue(expectedTrue,Command.getTableParking().execute(paramsTesLeaveDataAvail));
	}

}
