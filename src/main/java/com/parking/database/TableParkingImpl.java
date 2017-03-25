package com.parking.database;

import com.parking.model.Parking;

public class TableParkingImpl implements TableParking {
	private final String createParkingLot = "create_parking_lot";
	private final String park = "park";
	private final String status = "status";
	private final String registrationNumbersFoRCarsWithColour = "registration_numbers_for_cars_with_colour";
	private final String slotNumbersForCarWithColour = "slot_numbers_for_cars_with_colour";
	private final String slotNumberForRegistrationNumber = "slot_number_for_registration_number";
	private final String leave = "leave";

	private final String errorMessageNeedIntegerParams = "This Command need Integer Params. !";
	private final String errorIlegalCommand = "Ilegal comand !";

	private final String notFound = "Not Found";
	private final String format = "%-10s %-20s %-15s\n";

	private Parking[] parkingTable;
	private int countParking = 0;

	/**
	 * Constructor
	 */
	public TableParkingImpl() {
	}

	/**
	 * @param lot
	 * @return true
	 */
	private boolean createParkingLot(int lot) {
		countParking = 0;
		parkingTable = new Parking[lot];
		System.out.println("Created a parking lot with " + lot + " slots");
		return true;
	}

	/**
	 * Method for get Registration Number base on Color.
	 * 
	 * @param colour
	 * @return true if there is data available false if there is no data
	 *         available
	 */
	private boolean getRegistrationNumberByColour(String colour) {
		if (parkingTable == null)
			return false;
		
		String result = "";
		
		for (int i = 0; i < parkingTable.length; i++) {
			if (parkingTable != null && parkingTable[i].getColor().equals(colour)) {
				result = result + parkingTable[i].getRegistrationNo() + ", ";
			}
		}

		return checkReturn(result);
	}

	/**
	 * Method for get Slot Number base on Flag.
	 * 
	 * @param flag
	 *            regno color
	 * @param if
	 *            Flag regNo then param is registration number if Flag color
	 *            then param is color
	 * @return true if there is data available false if there is no data
	 *         available
	 */
	private boolean getSlotNumber(String param, String flag) {
		if (parkingTable == null)
			return false;
		
		String result = "";
		
		for (int i = 0; i < parkingTable.length; i++) {
			String data = (parkingTable != null)
					? (flag.equals("regno")) ? parkingTable[i].getRegistrationNo() : parkingTable[i].getColor() : "";

			if (data.equals(param))
				result = result + parkingTable[i].getSlotNo() + ", ";

		}
		return checkReturn(result);
	}

	/**
	 * Method for print all of list parking
	 * 
	 * @return true if there is data available false if there is no data
	 *         available
	 */
	private boolean status() {
		if (countParking == 0) {
			// System.out.format("No Data Available !" + countParking);
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
	 * 
	 * @param slotNo
	 * @return true if success set null false if fail set null
	 */
	public boolean leave(int slotNo) {
		if (parkingTable == null || parkingTable.length < slotNo - 1 || parkingTable[slotNo - 1] == null)
			return false;

		parkingTable[slotNo - 1] = null;
		countParking--;
		System.out.println("Slot number " + slotNo + " is free");
		return true;
	}

	/**
	 * Method for add Parking
	 * 
	 * @param newParking
	 * @return true if success to add false if fail to add
	 */
	private boolean Parking(Parking newParking) {
		if (parkingTable == null)
			return false;
		
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
	 * @param result
	 * @return true if result != "" false if result == ""
	 */
	private boolean checkReturn(String result) {
		if (result != "") {
			System.out.println(result.substring(0, result.length() - 2));
			return true;
		} else {
			System.out.println(notFound);
			return false;
		}
	}

	@Override
	public boolean execute(String[] command) {
		boolean _res = false;

		String _command = this.getCommand(command);
		String params[] = this.getParam(command);
		switch (_command) {
		case createParkingLot:
			if (!checkingParams(params, 1))
				return _res;

			int lot = 0;
			try {
				lot = Integer.parseInt(params[0]);
			} catch (NumberFormatException e) {
				// System.out.println(errorMessageNeedIntegerParams);
				return _res;
			}
			_res = createParkingLot(lot);
			break;
		case park:
			if (!checkingParams(params, 2))
				return _res;

			Parking newParking = new Parking();
			newParking.setRegistrationNo(params[0]);
			newParking.setColor(params[1]);

			_res = Parking(newParking);
			break;
		case status:
			_res = status();
			break;
		case registrationNumbersFoRCarsWithColour:
			if (!checkingParams(params, 1))
				return _res;

			_res = getRegistrationNumberByColour(params[0]);
			break;
		case slotNumbersForCarWithColour:
			if (!checkingParams(params, 1))
				return _res;

			_res = getSlotNumber(params[0], "colour");
			break;
		case slotNumberForRegistrationNumber:
			if (!checkingParams(params, 1))
				return _res;

			_res = getSlotNumber(params[0], "regno");
			break;
		case leave:
			if (!checkingParams(params, 1))
				return _res;

			try {
				int slotNo = Integer.parseInt(params[0]);
				_res = leave(slotNo);
			} catch (NumberFormatException e) {
				// System.out.println(errorMessageNeedIntegerParams);
				return _res;
			}
			break;
		default:
			// System.out.println(errorIlegalCommand);
			return _res;
		}
		System.out.println();
		return _res;
	}

	private String[] getParam(String[] command) {
		String[] params = new String[command.length - 1];
		for (int i = 1; i < command.length; i++) {
			params[i - 1] = command[i];
		}
		return params;
	}

	private String getCommand(String[] command) {
		return command[0].toLowerCase();
	}

	private boolean checkingParams(String params[], int needParams) {
		if (params.length != needParams) {
			// System.out.println("This command need " + needParams + " params.
			// !");
			return false;
		}
		return true;
	}

}
