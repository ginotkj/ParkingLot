package com.parking.command;

import com.parking.database.TableParking;
import com.parking.database.TableParkingImpl;

public class Command {

	private static TableParking tableParking;

	public static TableParking getTableParking() {
		if (tableParking == null) {
			tableParking = new TableParkingImpl();
		}
		return tableParking;
	}
}
