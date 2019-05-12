package demo11;

public class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public void depositAmount(int amount){
        balance+= amount;
    }

    public void withdrawAmount(int amount){
        balance-=amount;
    }

    //Transfer from 1 account to other
    public static void transfer(Account sourceAccount , Account destinationAccount, int amount){
        sourceAccount.withdrawAmount(amount);
        destinationAccount.depositAmount(amount);
    }
}
