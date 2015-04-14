package com.excilys.malbert.client.CLI;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.excilys.malbert.persistence.model.Company;
import com.excilys.malbert.persistence.model.Computer;
import com.excilys.malbert.service.Service;

/**
 * Command Line Interface class for the client
 * 
 * @author excilys
 */
public class Cli {

    private static Service service;
    private static Scanner scanner;

    /**
     * Prints the menu
     */
    private static void printMenu() {
	System.out.println("1. List of computers");
	System.out.println("2. List of companies");
	System.out.println("3. Show details of a computer");
	System.out.println("4. Create a computer");
	System.out.println("5. Update a computer");
	System.out.println("6. Delete a computer");
	System.out.println("7. Quit program");
    }

    /**
     * Change the format's date (YYYY-MM-DD) to a Timestamp
     * 
     * @return The date in a Timestamp object
     */
    private static LocalDateTime getDate() {
	try {
	    String[] introduction = scanner.nextLine().split("-");
	    return LocalDateTime.of(Integer.parseInt(introduction[0]),
		    Integer.parseInt(introduction[1]),
		    Integer.parseInt(introduction[2]), 0, 0);
	} catch (NumberFormatException | ArrayIndexOutOfBoundsException
		| DateTimeException e) {
	    return null;
	}
    }

    /**
     * @return A computer
     */
    private static Computer createComputer() {
	String name;
	LocalDateTime introduced, discontinued;
	long id = -1;
	System.out.println("Name of the computer :");
	scanner.nextLine(); // Just for debug, otherwise name is ""
	name = scanner.nextLine();
	System.out.println("Date of introduction (YYYY-MM-DD):");
	introduced = getDate();
	System.out.println("Date of discontinuation (YYYY-MM-DD):");
	discontinued = getDate();
	System.out
		.println("Id of the manufacturer (for no manufacturer, enter a 0 or minus id):");
	if (scanner.hasNextLong()) {
	    id = scanner.nextLong();
	} else {
	    scanner.next();
	}
	return new Computer(name, introduced, discontinued, new Company(id,
		null));
    }

    /**
     * Menu of the CLI
     * 
     * @param choice
     *            Menu choice
     */
    private static void menu(int choice) {
	List<Company> listCompanies;
	List<Computer> listComputers;
	Computer pc;
	long id = -1;

	switch (choice) {
	case 1:
	    listComputers = service.getAllComputers();
	    Paginator<Computer> paginatorPc = new Paginator<Computer>(
		    listComputers);
	    paginatorPc.show();
	    break;
	case 2:
	    listCompanies = service.getAllCompanies();
	    Paginator<Company> paginatorComp = new Paginator<Company>(
		    listCompanies);
	    paginatorComp.show();
	    break;
	case 3:
	    System.out.println("Id of the computer :");
	    id = scanner.nextLong();
	    System.out.println(service.getComputer(id).toString());
	    break;
	case 4:
	    service.createComputer(createComputer());
	    break;
	case 5:
	    System.out.println("Id of the computer to update :");
	    if (scanner.hasNextLong()) {
		id = scanner.nextLong();
	    } else {
		scanner.next();
	    }
	    pc = service.getComputer(id);
	    service.updateComputer(pc, createComputer());
	    break;
	case 6:
	    System.out.println("Id of the computer :");
	    id = scanner.nextLong();
	    pc = service.getComputer(id);
	    service.deleteComputer(pc);
	    break;
	case 7:
	    System.out.println("Good bye");
	    break;
	default:
	    System.err.println("Please enter a number between 1 and 7");
	    break;
	}
    }

    /**
     * @param args
     *            Unused
     */
    public static void main(String[] args) {
	service = new Service();
	scanner = new Scanner(System.in);
	int entry;

	do {
	    printMenu();
	    if (scanner.hasNextInt()) {
		entry = scanner.nextInt();
		menu(entry);
	    } else {
		scanner.next();
		entry = 0;
		System.err.println("Please enter a number between 1 and 7");
	    }
	} while (entry != 7);
    }
}
