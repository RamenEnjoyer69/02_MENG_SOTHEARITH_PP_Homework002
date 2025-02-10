class SavingAccount implements Account {
    private String name;
    private double balance;
    private String dateOfBirth;
    private String phoneNumber;
    private String gender;

    public SavingAccount(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public SavingAccount(String name, String dob, String gender, String phone) {
        this.name = name;
        this.dateOfBirth = dob;
        this.gender = gender;
        this.phoneNumber = phone;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            double interest = 0;

            // Apply 5% interest if deposit is 200 or more
            if (amount >= 200) {
                interest = amount * 0.05;
                System.out.println("\u001B[33mCongratulations! You've earned $" + interest + " as a 5% interest bonus.\u001B[0m");
            }

            balance += amount + interest;

            System.out.println("\u001B[32mDeposited $" + amount + " successfully.\u001B[0m");
            System.out.println("\n==========================================================");
            System.out.println("Saving Account");
            System.out.println("Received: $" + "\u001B[32m" + amount + "\u001B[0m");
            if (interest > 0) {
                System.out.println("Interest Bonus: $" + "\u001B[33m" + interest + "\u001B[0m");
            }
            System.out.println("Total Balance: $" + "\u001B[32m" + balance + "\u001B[0m");
            System.out.println("==========================================================");
        } else {
            System.out.println("\u001B[31mInvalid deposit amount.\u001B[0m");
        }
    }


    @Override
    public boolean withdraw(double amount) {
        if (amount > 0) {
            double maxWithdrawLimit = balance * 0.8; // 80% of the total balance

            if (amount > maxWithdrawLimit) {
                System.out.println("\u001B[31mWithdrawal denied! You cannot withdraw 80% or more of your total balance.\u001B[0m");
                return false;
            }

            if (amount <= balance) {
                balance -= amount;
                System.out.println("\u001B[32mWithdrawn $" + amount + " successfully.\u001B[0m");
                System.out.println("\n==========================================================");
                System.out.println("Saving Account");
                System.out.println("Withdrawn: \u001B[31m-$" + amount + "\u001B[0m");
                System.out.println("Total Balance: $" + "\u001B[32m" + balance + "\u001B[0m");
                System.out.println("==========================================================");
                return true;
            }
        }

        System.out.println("\u001B[31mInsufficient balance or invalid amount.\u001B[0m");
        return false;
    }


    @Override
    public void transfer(double amount, Account targetAccount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("\u001B[32mTransfer successful!\u001B[0m");
        } else {
            System.out.println("\u001B[31mTransfer failed due to insufficient balance.\u001B[0m");
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> Saving Account <<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Account Type: Saving Account");
        CheckingAccount.displayAccountInfo(name, dateOfBirth, gender, phoneNumber, balance);
    }
}