import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM_Main {
    static Scanner sc = new Scanner(System.in);
    private static final List<Account> accounts = new ArrayList<>();
    private static final String ADMIN_PASSWORD = "admin123";

    public static void main(String[] args) {
        accounts.add(new Account(AccountType.ZERO_BALANCE, 1001, 1221, 1000));
        accounts.add(new Account(AccountType.SAVINGS, 1002, 1231, 1000));
        accounts.add(new Account(AccountType.ZERO_BALANCE, 1003, 1241, 1000));
        int ch;
        System.out.println("*****Welcome to Cosmo Bank ATM Simulator*****");
        do{
            System.out.println("1.Admin\n2.Normal User\n3.Exit");
            System.out.print("Enter Your Choice : ");
            ch = sc.nextInt();
            int cho;
            if(ch == 1){
                adminMenu();
            } else if (ch == 2) {
                do{
                    System.out.println("1.Login\n2.Register New Account\n3.Exit");
                    System.out.print("Enter Your Choice : ");
                    cho = sc.nextInt();
                    switch(cho){
                        case 1:
                            Account account = login();
                            if(account != null)
                                showUserMenu(account);
                            break;
                        case 2:
                            registerAccount();
                            break;
                        case 3:
                            System.out.println("Thank You!! Visit Again...");
                            break;
                        default:
                            System.out.println("Invalid Choice!! Please Try Again!");
                    }
                }while(cho != 3);
            }
        }while(ch != 3);
    }
    private static void registerAccount(){
        System.out.println("New Account Registration");
        System.out.println("************************");
        int attempt = 0;
        while(attempt < 3){
            System.out.println("Select Account Type: 1. Savings  2. Zero Balance");
            int typeChoice = sc.nextInt();
            AccountType accTy = (typeChoice == 2) ? AccountType.ZERO_BALANCE : AccountType.SAVINGS;

            System.out.print("Enter Account Number : ");
            int newAcc = sc.nextInt();
            boolean exists = false;
            for(Account acc:accounts){
                if(acc.getAcc_no() == newAcc){
                    System.out.println("Account Already Exists...");
                    exists = true;
                    attempt++;
                    break;
                }
            }

            if(exists)
                continue;

            System.out.print("Enter Pin Number : ");
            int pin = sc.nextInt();
            if(pin < 1000 || pin > 9999){
                System.out.println("Invalid PIN Number! It should be 4 digit number");
                attempt++;
                continue;
            }

            double amt = 0.0;
            if(accTy == AccountType.SAVINGS){
                System.out.print("Enter Initial Amount : ");
                amt = sc.nextDouble();
                if(amt < 1000) {
                    System.out.println("Initial Amount Should be Rs.1000");
                    attempt++;
                    continue;
                }
            }

            Account newAccount = new Account(accTy, newAcc, pin, amt);
            accounts.add(newAccount);
            System.out.println("Account Registered Successfully");
            break;

        }
    }

    private static @Nullable Account login(){
        int attempt = 0;
        while(attempt < 3){
            System.out.print("Enter Account NUmber : ");
            int acc = sc.nextInt();

            System.out.print("Enter Pin Number : ");
            int pin = sc.nextInt();

            for(Account account:accounts){
                if(account.getAcc_no() == acc && account.getPin() == pin){
                    System.out.println("Login Successfully");
                    return account;
                }
            }
            System.out.println("Invalid Credentials");
            attempt++;
        }
        System.out.println("Too many Invalid Attempts");
        return null;
    }

    private static void changePin(@NotNull Account account){
        System.out.print("Enter the Current Pin : ");
        int pin = sc.nextInt();
        if(pin != account.getPin()){
            System.out.println("Invalid Pin");
            return;
        }
        int attempt = 0;
        while(attempt < 3){
            System.out.print("Enter the New Pin :");
            int newPin = sc.nextInt();
            System.out.print("Confirm the New Pin :");
            int Cnf_newPin = sc.nextInt();
            if(newPin == Cnf_newPin && newPin != pin) {
                account.setPin(newPin);
                return;
            }
            else if(newPin == pin)
                System.out.println("New Pin Shouldn't be the same as the Old Pin");
            else
                System.out.println("Pins don't Match");
            attempt++;
        }
    }
    private static void showUserMenu(Account account){
        int ch;
        do{
            System.out.println("*****Menu*****");
            System.out.println("1. View Balance\n2. Deposit Cash\n3. Withdraw Cash\n4. Mini Statement\n5. Change PIN\n6. Exit");
            System.out.print("Enter your Choice : ");
            ch = sc.nextInt();
            switch(ch){
                case 1:
                    System.out.println("Current Balance : "+account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the Amount to Deposit : ");
                    double amt = sc.nextDouble();
                    account.deposit(amt);
                    System.out.println("Current Balance : "+account.getBalance());
                    break;
                case 3:
                    System.out.print("Enter the Withdraw Amount : ");
                    double amt1 = sc.nextDouble();
                    if(account.limitCheck(amt1)) {
                        if (ATM.dispenseCash(amt1)) {
                            account.withdraw(amt1);
                            System.out.println("Current Balance : " + account.getBalance());
                        } else
                            System.out.println("Withdrawal failed due to insufficient ATM cash.");
                    }
                    break;
                case 4:
                    System.out.println("***Mini Statement");
                    List<String> trans = account.getTransaction();
                    for(String s : trans)
                        System.out.println(s);
                    break;
                case 5:
                    changePin(account);
                    break;
                case 6:
                    System.out.println("Thank you for your Patronage!! We are Logging you out!");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }while(ch != 6);
    }

    private static void adminMenu() {
        System.out.print("Enter Admin Password: ");
        String pwd = sc.next();
        if (!pwd.equals(ADMIN_PASSWORD)) {
            System.out.println("Invalid Admin Password!");
            return;
        }

        int ch;
        do {
            System.out.println("***** Admin Menu *****");
            System.out.println("1. Refill ATM\n2. View ATM Balance\n3. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter amount to add: ");
                    double amt = sc.nextDouble();
                    ATM.addCash(amt);
                    break;
                case 2:
                    System.out.println("ATM Current Balance: Rs." + ATM.getAtmBalance());
                    break;
                case 3:
                    System.out.println("Exiting Admin Menu...");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (ch != 3);
    }
}