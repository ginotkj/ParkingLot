package com.parking.database;

import com.parking.model.Parking;

public class TableParking {
	private final String notFound = " Not Found";
	private final String format = "%-10s %-20s %-15s\n";
	private Parking[] parkingTable;
	private int countParking = 0;

	/**
	 * Constructor for create parking as much as lot input.
	 * @param 
	 * 		lot
	 */
	public TableParking(int lot) {
		parkingTable = new Parking[lot];
		System.out.println("Created a parking lot with " + lot + " slots");
	}

	/**
	 * Method for get Registration Number base on Color.
	 * 
	 * @param 
	 * 		colour
	 * @return 
	 * 		true if there is data available 
	 * 		false if there is no data available        
	 */
	public boolean getRegistrationNumberByColour(String colour) {
		String result = "";

		for (int i = 0; i < parkingTable.length; i++) {
			if (parkingTable != null && parkingTable[i].getColor().equals(colour)) {
				result = result + parkingTable[i].getRegistrationNo() + ",";
			}
		}

		return checkReturn(result);
	}

	/**
	 * Method for get Slot Number base on Flag.
	 * 
	 * @param 
	 * 		flag
	 *      regno color
	 * @param
	 *      if Flag regNo then param is registration number if Flag color
	 *      then param is color
	 * @return 
	 * 		true if there is data available 
	 * 		false if there is no data available
	 */
	public boolean getSlotNumber(String param, String flag) {
		String result = "";

		for (int i = 0; i < parkingTable.length; i++) {
			String data = (parkingTable != null)
					? (flag.equals("regno")) ? parkingTable[i].getRegistrationNo() : parkingTable[i].getColor() : "";

			if (data.equals(param))
				result = result + parkingTable[i].getSlotNo() + ",";

		}
		return checkReturn(result);
	}

	/**
	 * Method for print all of list parking
	 * @return 
	 * 		true if there is data available 
	 * 		false if there is no data available
	 */
	public boolean status() {
		if (countParking == 0) {
			System.out.format("No Data Available !");
			return false;
		}

		System.out.format(format, "Slot No.", "Registration No", "Colour");
		for (int i = 0; i < parkingTable.length; i++) {
			if (parkingTable[i] != (null)) {
				System.out.format(format, String.valueOf(parkingTable[i].getSlotNo()),
						parkingTable[i].getRegistrationNo(), parkingTable[i].getColor());
			}
		}

		return true;
	}

	/**
	 * Method for set null the parking table base on slot no
	 * @param 
	 * 		slotNo
	 * @return 
	 * 		true if success set null 
	 * 		false if fail set null 
	 */
	public boolean leave(int slotNo) {
		if (parkingTable[slotNo - 1] == null)
			return false;

		parkingTable[slotNo - 1] = null;
		countParking--;
		System.out.println("Slot Number is " + slotNo + " free");
		return true;
	}

	/**
	 * Method for add Parking
	 * @param 
	 * 		newParking
	 * @return 
	 * 		true if success to add
	 * 		false if fail to add
	 */
	public boolean add(Parking newParking) {
		for (int i = 0; i < parkingTable.length; i++) {
			if (parkingTable[i] == (null)) {
				int SlotNo = i + 1;
				newParking.setSlotNo(SlotNo);
				parkingTable[i] = newParking;
				countParking++;
				System.out.println("Allocated slot number: " + SlotNo);
				return true;
			}
		}
		System.out.println("Sorry, parking lot is full");
		return false;
	}

	/**
	 * @param 
	 * 		result
	 * @return
	 * 		true if result != ""
	 * 		false if result == ""
	 */
	private boolean checkReturn(String result) {
		if (result != "") {
			System.out.println(result.substring(0, result.length() - 1));
			return true;
		} else {
			System.out.println(countParking + notFound);
			return false;
		}
	}
}
