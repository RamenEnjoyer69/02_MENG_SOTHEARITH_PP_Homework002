class CheckingAccount implements Account {
    private double balance;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CheckingAccount(String name, String dateOfBirth, String gender, String phoneNumber) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("\u001B[32mDeposited $" + amount + " successfully.\u001B[0m");
            System.out.println("\n==========================================================");
            System.out.println("Checking Account");
            System.out.println("Received: $" + "\u001B[32m" +  amount + "\u001B[0m");
            System.out.println("Total Balance: $" + "\u001B[32m" + balance + "\u001B[0m");
            System.out.println("==========================================================");

        } else {
            System.out.println("\u001B[31mInvalid deposit amount.\u001B[0m");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("\u001B[32mWithdrawn $" + amount + " successfully.\u001B[0m");
            System.out.println("\n==========================================================");
            System.out.println("Checking Account");
            System.out.println("Withdrawn: \u001B[31m-$" +  amount + "\u001B[0m");
            System.out.println("Total Balance: $" + "\u001B[32m" + balance + "\u001B[0m");
            System.out.println("==========================================================");
            return true;
        } else {
            System.out.println("\u001B[31mInsufficient balance or invalid amount.\u001B[0m");
            return false;
        }
    }

    @Override
    public void transfer(double amount, Account targetAccount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("\u001B[32mTransfer successful!\u001B[0m");
        }
//        else {
//            System.out.println("\u001B[31mTransfer failed due to insufficient balance.\u001B[0m");
//        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> Checking Account <<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Account Type: Checking Account");
        displayAccountInfo(name, dateOfBirth, gender, phoneNumber, balance);

    }

    static void displayAccountInfo(String name, String dateOfBirth, String gender, String phoneNumber, double balance) {
        System.out.println("Account ID: " + name);
        System.out.println("User Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("=================================================================");
    }
}