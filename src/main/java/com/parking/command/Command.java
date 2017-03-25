package com.parking.command;

import com.parking.database.TableParking;
import com.parking.model.Parking;

public class Command {
	private final String createParkingLot 						= "create_parking_lot";
	private final String park 									= "park";
	private final String status 								= "status";
	private final String registrationNumbersFoRCarsWithColour 	= "registration_numbers_for_cars_with_colour";
	private final String slotNumbersForCarWithColour 			= "slot_numbers_for_cars_with_colour";
	private final String slotNumberForRegistrationNumber 		= "slot_number_for_registration_number";
	private final String leave 									= "leave";
	
	private TableParking _tableParking;
	
	
	public void execute(String[] command) {
		String _command = this.getCommand(command);
		String _params[] = this.getParam(command);
		switch (_command) {
		case createParkingLot:
			createParking(_params);
			break;
		case park:
			park(_params);
			break;
		case status:
			status();
			break;
		case registrationNumbersFoRCarsWithColour:
			getRegistrationNumberByColour(_params);
			break;
		case slotNumbersForCarWithColour:
			getSlotNumberByColour(_params);
			break;
		case slotNumberForRegistrationNumber:
			getSlotNumberByRegistrationNumber(_params);
			break;
		case leave:
			leave(_params);
			break;
		default:
			System.out.println("Ilegal comand !");
		}
	}
	
	public void leave(String params[]){
		if (!checkingParams(params, 1))
			return;
		try {
			int slotNo = Integer.parseInt(params[0]);
			_tableParking.leave(slotNo);
		} catch (NumberFormatException e) {
			System.out.println("This Command need Integer Params. !");
			return;
		}
	}
	
	public void getSlotNumberByRegistrationNumber(String params[]) {
		if (!checkingParams(params, 1))
			return;
		_tableParking.getSlotNumber(params[0],"regno");
	}

	public void getSlotNumberByColour(String params[]) {
		if (!checkingParams(params, 1))
			return;
		_tableParking.getSlotNumber(params[0],"colour");
	}

	public void getRegistrationNumberByColour(String params[]) {
		if (!checkingParams(params, 1))
			return;
		_tableParking.getRegistrationNumberByColour(params[0]);
	}

	public void status() {
		_tableParking.status();
	}

	private void park(String params[]) {
		if (!checkingParams(params, 2))
			return;

		Parking newParking = new Parking();
		newParking.setRegistrationNo(params[0]);
		newParking.setColor(params[1]);

		_tableParking.add(newParking);

	}

	private void createParking(String params[]) {
		if (!checkingParams(params, 1))
			return;

		int lot = 0;
		try {
			lot = Integer.parseInt(params[0]);
		} catch (NumberFormatException e) {
			System.out.println("This Command need Integer Params. !");
			return;
		}

		_tableParking = new TableParking(lot);
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
			System.out.println("This command need " + needParams + " params. !");
			return false;
		}
		return true;
	}
	
}
