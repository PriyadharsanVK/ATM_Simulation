import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private AccountType Acc_Type;
    private int Acc_no;
    private int pin;
    private double balance;
    private List<String> Transaction;

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
            Transaction.remove(0);
        Transaction.add(transaction+" at "+ LocalDateTime.now()+" Cur.Bal : "+getBalance());
    }

    public int getPin() {
        return pin;
    }

    public void deposit(double amount){
        this.balance += amount;
        addTransaction("Deposit : Rs."+amount);
    }

    public boolean limitCheck(double amount){
        double limit = (Acc_Type == AccountType.ZERO_BALANCE) ? 10000 : 20000;

        if(amount > limit){
            System.out.println("Maximum Amount per Time Limit Reached for "+Acc_Type);
            return false;
        }
        return true;
    }
    public void withdraw(double amount){
        if (amount > balance) {
            System.out.println("Insufficient Balance in Account");
            return;
        }

        this.balance = this.balance - amount;
        addTransaction("Withdrawl : Rs."+amount);
        System.out.println("Withdraw Successful");
    }
}
