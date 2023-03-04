package app;

public class UserAccount {
    private User user;
    private static int accountID;
    private final int userAccountId;
    private int userBalance;
    private static int userTransactionID;
    private static int transactionID;  //счетчик транзакций
    private int userAmount;

    private Currency userCurrency;

    public UserAccount(User user, Currency userCurrency) {
        this.user = user;
        this.userAccountId = ++accountID;
        this.userBalance = 0;
        this.userCurrency = userCurrency;
        this.userAmount = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserTransactionID() {
        return userTransactionID;
    }

    public void setUserTransactionID(int userTransactionID) {
        this.userTransactionID = userTransactionID;
    }

    public int getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(int userAmount) {
        this.userAmount = userAmount;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public int getUserBalance() {
        return userBalance;
    }

    public Currency getUserCurrency() {
        return userCurrency;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    public static void setAccountID(int accountID) {
        UserAccount.accountID = accountID;
    }

    public static int getAccountID() {
        return accountID;
    }

    public static int getTransactionID() {
        return transactionID;
    }

    public static void setTransactionID(int transactionID) {
        UserAccount.transactionID = transactionID;
    }


    @Override
    public String toString() {
        return "UserAccount: " + "\n" +
                "userAccountId=" + userAccountId + "\n" +
                "userBalance=" + userBalance + "\n" +
                "userCurrency=" + userCurrency + "\n" +
                "userAmount= " + userAmount + "\n" +
                user;
    }
}
