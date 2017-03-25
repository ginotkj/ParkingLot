package com.parking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.parking.command.Command;

/**
 * @author Gino for Gojek
 */

public class App {
	private static Scanner in;

	public static void main(String[] args) {
		String[] _command;

		if (args.length > 0) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(args[0]));
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				
				System.out.println(sb.toString());

				String[] listCommand = sb.toString().split(System.lineSeparator());
				for (int i = 0; i < listCommand.length; i++) {
					_command = listCommand[i].split(" ");
					Command.getTableParking().execute(_command);
				}

			} catch (FileNotFoundException e) {
				System.out.println("file not found !");
			} catch (IOException e) {
				System.out.println("IO Exception !");
			}

		} else {
			while (true) {
				in = new Scanner(System.in);
				String commandLine = in.nextLine();
				_command = commandLine.split(" ");
				Command.getTableParking().execute(_command);
			}
		}

	}
}
