public class ATM {
    private static double atmBalance = 50000;

    public static double getAtmBalance() {
        return atmBalance;
    }

    public static void addCash(double amount) {
        atmBalance += amount;
        System.out.println("ATM Refilled. Current ATM Balance: Rs." + atmBalance);
    }

    public static boolean dispenseCash(double amount) {
        if (amount > atmBalance) {
            System.out.println("ATM does not have enough cash!");
            return false;
        }
        atmBalance -= amount;
        return true;
    }
}
