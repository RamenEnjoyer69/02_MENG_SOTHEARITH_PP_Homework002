import java.util.Scanner;
import java.time.LocalDate;

public class InputValidator {
    private static final Scanner sc = new Scanner(System.in);
    private static int startYear;
    // name, no number, no special chars
//    public static String validateString(String message, String field) {
//        String regex = "^[a-zA-Z\\s]+$"; // Allows only letters and spaces
//        String name;
//        do {
//            System.out.print(message);
//            name = sc.nextLine().trim();
//            if (!name.matches(regex)) {
//                System.out.println("Invalid " + field + "! Only letters and spaces are allowed.");
//            }
//        } while (!name.matches(regex));
//        return name;
//    }
//    public static String validatePhone(String message, String field) {
//        String regex = "^[0-9]+$"; // Allows only numbers
//        String input;
//        do {
//            System.out.print(message);
//            input = sc.nextLine().trim();
//            if (!input.matches(regex)) {
//                System.out.println("Invalid " + field + "! Only numbers are allowed.");
//            }
//        } while (!input.matches(regex));
//        return input;
//    }

    public static String validateInput(String message, String field, String regex) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (!input.matches(regex)) {
                System.out.println("\u001B[31mInvalid " + field + "! Please enter a valid value.\u001B[0m");
            }
        } while (!input.matches(regex));
        return input;
    }

//    public static String validateDateOfBirth(String message) {
//        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$"; // Pattern for dd-mm-yyyy format
//        String dob;
//        do {
//            System.out.print(message);
//            dob = sc.nextLine().trim();
//            if (!dob.matches(regex)) {
//                System.out.println("\u001B[31mInvalid date of birth! Please enter the date in dd-mm-yyyy format.\\u001B[0m");
//            }
//        } while (!dob.matches(regex));
//        return dob;
//    }



    public static String validateDateOfBirth(String message) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$"; // dd-mm-yyyy format
        String dob;
        LocalDate currentDate = LocalDate.now();
        int minYear = currentDate.getYear() - 16; // at least 16 years old

        do {
            System.out.print(message);
            dob = sc.nextLine().trim();

            if (!dob.matches(regex)) {
                System.out.println("\u001B[31mInvalid date format! Please enter in dd-mm-yyyy format.\u001B[0m");
                continue;
            }

            String[] parts = dob.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Check if year is valid
            if (year > minYear && year > 150) {
                System.out.println("\u001B[31mYou must be at least 16 years old and not older than 150 years old.\u001B[0m");
                continue;
            }

            // Validate days in months
            if (!isValidDay(day, month, year)) {
                System.out.println("\u001B[31mInvalid day for the given month. Please enter a valid date.\u001B[0m");
                continue;
            }

            return dob; // If all checks pass, return the valid date

        } while (true);
    }

    // checks valid days in each month
    private static boolean isValidDay(int day, int month, int year) {
        if (month == 2) { // February
            return day <= (isLeapYear(year) ? 29 : 28);
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) { // April, June, September, November
            return day <= 30;
        }
        return day <= 31; // Other months
    }

    // Function to check if a year is a leap year
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
//choice
    public static int getValidAccountChoice(String message) {
        int choice;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice == 1 || choice == 2 || choice == 3) {
                    sc.nextLine();
                    return choice;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("\u001B[31mInvalid input! Please enter 1 or 2.\u001B[0m");
        }
    }

    // Helper function to get a valid double amount
    public static double getValidAmount(String message) {
        double amount;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount > 0) {
                    sc.nextLine();
                    return amount;
                } else {
                    System.out.println("\u001B[31mAmount must be greater than zero!\u001B[0m");
                }
            } else {
                sc.nextLine();
                System.out.println("\u001B[31mInvalid input! Please enter a valid number.\u001B[0m");
            }
        }
    }

}
