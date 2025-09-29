import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final AccountType Acc_Type;
    private final int Acc_no;
    private int pin;
    private double balance;
    private final List<String> Transaction;

    private double withdrawnToday = 0;
    private LocalDate lastWithdrawalDate = LocalDate.now();

    public Account(AccountType Acc_Type, int accountNumber, int pin, double balance) {
        this.Acc_Type = Acc_Type;
        this.Acc_no = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.Transaction = new ArrayList<>();
    }

    public AccountType getAcc_Type() {
        return Acc_Type;
    }

    public int getAcc_no() {
        return Acc_no;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransaction() {
        return Transaction;
    }

    public void setPin(int pin) {
        if(pin<1000 || pin>9999){
            System.out.println("Invalid Pin! It Should have 4 digits");
            return;
        }
        this.pin = pin;
        System.out.println("Pin Set Successfully");
    }

    public void addTransaction(String transaction) {
        if(Transaction.size() >= 5)
            Transaction.removeFirst();
        Transaction.add(transaction+" at "+ LocalDateTime.now()+" Cur.Bal : "+getBalance());
    }

    public int getPin() {
        return pin;
    }

    public void deposit(double amount){
        this.balance += amount;
        ATM.addCash(amount);
        addTransaction("Deposit : Rs."+amount);
    }

    public boolean limitCheck(double amount){
        double limit = (Acc_Type == AccountType.ZERO_BALANCE) ? 10000 : 20000;

        if(Acc_Type == AccountType.SAVINGS){
            if(balance - amount < 1000){
                System.out.println("You cannot withdraw this amount as this will get the balance below Minimum Balance");
                return false;
            }
        }
        if(amount > limit){
            System.out.println("Maximum Amount per Time Limit Reached for "+Acc_Type);
            return false;
        }
        return true;
    }

    public boolean canWithdraw(double amount){
        if(!lastWithdrawalDate.equals(LocalDate.now())){
            withdrawnToday = 0;
            lastWithdrawalDate = LocalDate.now();
        }
        if (amount > balance) {
            System.out.println("Insufficient Balance in Account");
            return false;
        }
        if(Acc_Type == AccountType.SAVINGS){
            if(withdrawnToday + amount > 100000){
                System.out.println("Daily Limit Reached");
                return false;
            }
        }
        else if(Acc_Type == AccountType.ZERO_BALANCE){
            if(withdrawnToday + amount > 50000){
                System.out.println("Daily Limit Reached");
                return false;
            }
        }
        return true;
    }

    public void withdraw(double amount){
        if(canWithdraw(amount)) {
            this.balance = this.balance - amount;
            this.withdrawnToday = this.withdrawnToday + amount;
            addTransaction("Withdrawl : Rs." + amount);
            System.out.println("Withdraw Successful");
        }
    }
}
