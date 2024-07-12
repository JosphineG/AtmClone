package AtmClone;

import java.util.Scanner;

public class Atm {
    public  static  void main (String []args){
        Scanner sc = new Scanner(System.in);

        Bank  theBank = new Bank ("Kenya Commercial Bank");

        User aUser = theBank.addUser("Josphine", "Gatwiri", "4321");

        Account newAccount = new Account("Checking",aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curlUser;
        while (true){
            curlUser = Atm.mainMenuPrompt(theBank, sc);

            Atm.printUserMenu(curlUser, sc);
        }
    }

//

    public  static  User mainMenuPrompt(Bank theBank, Scanner sc){
        String userUuid;
        String  pin;
        User authUser;
        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userUuid = sc.nextLine();
            System.out.print("Enter  pin: ");
            pin = sc.nextLine();

            authUser = theBank.userLogin(userUuid,pin);
            if (authUser == null){
                System.out.println(("Incorrect user ID/pin." + "Please try again."));
            }

        } while (authUser == null);
        return authUser;
     }
   public static void printUserMenu(User theUser, Scanner sc) {
        theUser.printAccountsSummary();

        int choice;

        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getfirstName());
            System.out.println("  1) Transaction History");
            System.out.println("  2) Withdraw");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5) {
                System.out.println("Invalid choice! please choose 1-5");
            }

        } while (choice < 1 || choice > 5);
        switch (choice){
            case 1:
                Atm.showTransHistory(theUser, sc);
                break;
            case  2:
                Atm.withdrawMoney(theUser, sc);
                break;
            case 3:
                Atm.depositFunds(theUser, sc);
                break;
            case 4:
                Atm.transferFunds(theUser, sc);
                break;
            case 5:
                sc.nextLine();
                break;
        }
        if (choice != 5){
            Atm.printUserMenu(theUser, sc);
        }



    }


    private static void showTransHistory(User theUser, Scanner sc) {
        int theAcct;

        do {
            System.out.printf("Enter the number (1-%d) of the account \n" +
                    "whose transactions you want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt()-1;
            if(theAcct < 0 || theAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }

        }while (theAcct < 0 ||theAcct >= theUser.numAccounts());
        theUser.printAcctTransHistory(theAcct);
    }

    public static  void transferFunds(User theUser, Scanner sc){

        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + " to transfer from: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if(fromAcct< 0 ||fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }

            }while (fromAcct< 0 ||fromAcct>= theUser.numAccounts());

            acctBal = theUser.getAcctBalance(fromAcct);

        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + " to transfer to: ", theUser.numAccounts() );
            toAcct = sc.nextInt()-1;
            if(toAcct< 0 ||toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }

        }while (toAcct< 0 ||toAcct>= theUser.numAccounts());

        do {
            System.out.printf("Enter the amount to transfer(max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater than zero");
            }
        } while (amount < 0 || amount > acctBal);

        theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
        theUser.addAcctTransaction(toAcct,amount, String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));
    }
    public  static  void withdrawMoney(User theUser, Scanner sc){
        int   fromAcct;
        double amount;
        double acctBal;
        String  memo;

        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + " to withdraw from: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if(fromAcct< 0 ||fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }

        }while (fromAcct< 0 ||fromAcct>= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(fromAcct);
        do {
            System.out.printf("Enter the amount to withdraw(max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater than zero");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than\n" + "balance of $%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        sc.nextLine();
         System.out.print("Enter a memo:");
         memo = sc.nextLine();

         theUser.addAcctTransaction(fromAcct, -1*amount,memo);

    }

    public  static void depositFunds(User theUser, Scanner sc){
        int   toAcct;
        double amount;
        double acctBal;
        String  memo;

        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + " to deposit in: ", theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if(toAcct< 0 ||toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }

        }while (toAcct< 0 ||toAcct>= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(toAcct);
        do {
            System.out.printf("Enter the amount to deposit(max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater than zero");
            }
        } while (amount <= 0 );

        sc.nextLine();
        System.out.print("Enter a memo:");
        memo = sc.nextLine();

        theUser.addAcctTransaction(toAcct, amount,memo);

    }
//    private static void printUserMenu(User theUser, Scanner sc) {
//    }


}
