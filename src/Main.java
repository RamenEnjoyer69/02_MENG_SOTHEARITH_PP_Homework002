import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static CheckingAccount checkingAccount;
    private static SavingAccount savingAccount;

    public static void main(String[] args) {
        while (true) {
            int option = displayMenu();
            handleMenuOption(option);
        }
    }

    // Main menu
    public static int displayMenu() {
        System.out.println("\n============== Online Banking System =============");
        System.out.println("1- Create Account");
        System.out.println("2- Deposit Money");
        System.out.println("3- Withdraw Money");
        System.out.println("4- Transfer Money");
        System.out.println("5- Display Account Information");
        System.out.println("6- Delete Account");
        System.out.println("7- Exit");
        System.out.println("---------------------------------------------------------");
        System.out.print("Choose an option (1-7): ");

        while (!sc.hasNextInt()) {
            System.out.println("\u001B[31mInvalid input. Please enter a number.\u001B[0m");
            sc.next(); // Clear invalid input
        }
        int option = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        return option;
    }

    // Handle main menu options
    public static void handleMenuOption(int option) {
        switch (option) {
            case 1 -> createAccount();
            case 2 -> depositMoney();
            case 3 -> withdrawMoney();
            case 4 -> transferMoney();
            case 5 -> displayAccountInfo();
            case 6 -> deleteAccount();
            case 7 -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
            default -> System.out.println("\u001B[31mInvalid option. Please enter a number between 1 and 7.\u001B[0m");
        }
    }

    // Create account
    public static void createAccount() {
        displayAccountCreationMenu();

        int option = getValidOption();
        if (option == 3) {
            return; // Exit if the user chooses "Back"
        }

        // Check if account already exists
        if ((option == 1 && checkingAccount != null) || (option == 2 && savingAccount != null)) {
            System.out.println("\u001B[31mAccount already exists. Only one account of each type is allowed.\u001B[0m");
            return;
        }

        String name = InputValidator.validateInput("Enter User Name: ", "name", "^[a-zA-Z\\s]+$");
        String dob = InputValidator.validateDateOfBirth("Enter date of birth (dd-mm-yyyy): ");
        String gender = InputValidator.validateInput("Enter gender: ", "gender", "^[a-zA-Z\\s]+$");
        String phone = InputValidator.validateInput("Enter phone number: ", "phone ", "^[0-9]+$");

        if (option == 1) {
            checkingAccount = new CheckingAccount(name, dob, gender, phone);
            System.out.println("\u001B[32mYour Checking Account has been created successfully!\u001B[0m");
        } else if (option == 2) {
            savingAccount = new SavingAccount(name, dob, gender, phone);
            System.out.println("\u001B[32mYour Saving Account has been created successfully!\u001B[0m");
        }
    }

    // Display account creation menu
    public static void displayAccountCreationMenu() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> Creating Account <<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("1. Checking Account");
        System.out.println("2. Saving Account");
        System.out.println("3. Back");
        System.out.println("=========================================================================");
        System.out.print("Choose an option (1-3): ");
    }

    // Get a valid integer option
    public static int getValidOption() {
        while (!sc.hasNextInt()) {
            System.out.println("\u001B[31mInvalid option. Please enter a number.\u001B[0m");
            sc.next(); // Clear invalid input
        }
        return sc.nextInt();
    }

    // Deposit money
    public static void depositMoney() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> Deposit Money <<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        int type = InputValidator.getValidAccountChoice("\n1: Checking\n2: Saving\n3: Exit\nChoose an option: ");

        if (type == 3) {
            System.out.println("Returning to the main menu...");
            return;
        }

        double amount = InputValidator.getValidAmount("Enter Deposit Amount: ");

        if (type == 1 && checkingAccount != null) {
            checkingAccount.deposit(amount);
        } else if (type == 2 && savingAccount != null) {
            savingAccount.deposit(amount);
        } else {
            System.out.println("\u001B[31mAccount not found!\u001B[0m");
        }
    }

    // Withdraw money
    public static void withdrawMoney() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> Withdraw Money <<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        int type = InputValidator.getValidAccountChoice("\n1: Checking\n2: Saving\n3: Exit\nChoose an option: ");

        if (type == 3) {
            System.out.println("Returning to the main menu...");
            return;
        }

        double amount = InputValidator.getValidAmount("Enter Withdrawal Amount: ");

        if (type == 1 && checkingAccount != null) {
            checkingAccount.withdraw(amount);
        } else if (type == 2 && savingAccount != null) {
            savingAccount.withdraw(amount);
        } else {
            System.out.println("\u001B[31mAccount not found!\u001B[0m");
        }
    }

    // Transfer money
    public static void transferMoney() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>> Transfer Money <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        int choice = InputValidator.getValidAccountChoice("\n1: Checking Account → Saving Account\n2: Saving Account → Checking Account\n3: Exit\nChoose an option: ");

        if (choice == 3) {
            System.out.println("Returning to the main menu...");
            return;
        }

        if (checkingAccount == null || savingAccount == null) {
            System.out.println("\u001B[31mBoth accounts must exist to perform a transfer!\u001B[0m");
            return;
        }

        double amount = InputValidator.getValidAmount("Enter Transfer Amount: ");

        if (choice == 1) {
            checkingAccount.transfer(amount, savingAccount);
        } else if (choice == 2) {
            savingAccount.transfer(amount, checkingAccount);
        } else {
            System.out.println("\u001B[31mInvalid choice! Please select 1, 2, or 3.\u001B[0m");
        }
    }



    // Display account information
    public static void displayAccountInfo() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>> Display Account Info <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (checkingAccount != null) {
            checkingAccount.displayAccountInfo();
        } else {
            System.out.println("\u001B[31mNo Checking Account Found!\u001B[0m");
        }

        if (savingAccount != null) {
            savingAccount.displayAccountInfo();
        } else {
            System.out.println("\u001B[31mNo Saving Account Found!\u001B[0m");
        }
    }


    public static void deleteAccount() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>> Delete Account <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        int type = InputValidator.getValidAccountChoice("\n1: Checking\n2: Saving\n3: Exit\nChoose an option: ");

        if (type == 3) {
            System.out.println("Returning to the main menu...");
            return;
        }

        // Ensure both accounts exist, otherwise prevent deletion
        if ((checkingAccount == null && savingAccount != null) ||
                (savingAccount == null && checkingAccount != null)) {
            System.out.println("\u001B[31mDeletion not allowed. At least one account must remain.\u001B[0m");
            return;
        }

        if (type == 1 && checkingAccount != null) {
            if (checkingAccount.getBalance() > 0 && savingAccount != null) {
                // Transfer balance to saving account
                savingAccount.deposit(checkingAccount.getBalance());
                System.out.println("Balance transferred to Saving account.");
            }
            checkingAccount = null;
            System.out.println("Checking account deleted.");
        } else if (type == 2 && savingAccount != null) {
            if (savingAccount.getBalance() > 0 && checkingAccount != null) {
                // Transfer balance to checking account
                checkingAccount.deposit(savingAccount.getBalance());
                System.out.println("Balance transferred to Checking account.");
            }
            savingAccount = null;
            System.out.println("Saving account deleted.");
        } else {
            System.out.println("\u001B[31mAccount not found or invalid option.\u001B[0m");
        }
    }

}