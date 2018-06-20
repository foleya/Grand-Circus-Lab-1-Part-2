import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class DateDifference {
	
	public static void main(String[] args) {
		System.out.printf("Welcome to the date difference checker. Enter two "
						   + "dates to see how far apart they are.%n%n");
		calcDateDifference();
	}
	
	/**
	 * This method contains the primary logic of the program. It asks the user to
	 * enter two dates, uses java.time.Period to get the quantity of time between
	 * the two dates. That quantity is then reported to the user in a formatted string.
	 */
	private static void calcDateDifference() {
		/* Ask user to input two dates. */
		String dateInputOne = getUserInput();
		String dateInputTwo = getUserInput();
		
		/* Get LocalDates by parsing user's input */
		LocalDate dateOne = parseDateFromString(dateInputOne);
		LocalDate dateTwo = parseDateFromString(dateInputTwo);
		
		/* Calculate the period between the two LocalDates */
		Period diff = Period.between(dateOne, dateTwo);
		
		/* Print out that period as years, months, and days */
		System.out.printf("Those two dates are %d year(s), "
				          + "%d month(s) and %d day(s) apart.",
				          Math.abs(diff.getYears()), 
				          Math.abs(diff.getMonths()), 
				          Math.abs(diff.getDays())
		);		
	}
	
	/**
	 * This method uses DateTimeFormatter to parse a
	 * LocalDate out of a string (using the format "MM/dd/yyyy").
	 */
	private static LocalDate parseDateFromString(String userInput) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	    LocalDate date = LocalDate.parse(userInput, dateFormat);
	    return date;
	}
	
	/**
	 * This method prompts the user to enter a date (using the format
	 * MM/DD/YYYY), maintaining a while-loop until the user's input
	 * matches the correct date format.
	 */
	private static String getUserInput() {
		/* Variables for reading, storing, and validating user input */
		Scanner scnr = new Scanner(System.in);
		String input;
		boolean inputIsValid = false;
		
		do {
			System.out.println("Enter a date using the MM/DD/YYYY format (e.g. 01/01/2018):");
			input = scnr.nextLine();
			try { 
				/* Attempt to parse date from user's input */
				parseDateFromString(input);
				inputIsValid = true;
			} catch (DateTimeParseException dtpe) {
				/* Prompt user to try again if date could not be parsed from input */
				System.out.printf("%n-- '%s' is not a valid date format. "
								  + "Please try again. --%n%n", input);
			}
		} while (!inputIsValid);
	
		return input;
	}

}