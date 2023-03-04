package app;

public class Transaction {

    public void userIncrementTransaction(UserAccount account, int amount) {
        if (amount > 100_000_000) {
            System.out.println("You can't add so much!");
        } else if (account.getUserBalance() + amount > 2_000_000_000) {
            System.out.println("We can't add your money, cause your balance can't be more then 2_000_000_000! ");
        } else {
            account.setUserBalance(account.getUserBalance() + amount);
            account.setUserAmount(amount);
            account.setUserTransactionID(account.getUserTransactionID() + 1);
        }
    }

    public void userDecrementTransaction(UserAccount account, int amount) {
        if (amount > 100_000_000) {
            System.out.println("You can't withdraw so much!");
        } else if (account.getUserBalance() - amount < 0) {
            System.out.println("You can't withdraw too much, cause in your balance don't enough money! ");
        } else {
            account.setUserBalance(account.getUserBalance() - amount);
            account.setUserAmount(-amount);
            account.setUserTransactionID(account.getUserTransactionID() + 1);
        }
    }

}
