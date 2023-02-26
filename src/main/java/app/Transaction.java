package app;

public class Transaction {
//    private static int transactionID;
//    private final int userTransactionID;
//    private int amount;
//
//
//    public Transaction() {
//
//        this.userTransactionID = ++transactionID;
//        this.amount = amount;
//    }
//
//    public int getUserTransactionID() {
//        return userTransactionID;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
    //                               данные методы перенесены в USER ACCOUNT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


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


//    @Override
//    public String toString() {
//        return "Transaction: " + "\n" +
//                "transactionID=" + userTransactionID + "\n" +
//                "amount=" + amount;
//    }
}
