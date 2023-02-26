package app;

public class UserAccount {
    private User user;
    private static int accountID;
    private final int userAccountId;
    private int userBalance;
    private static int userTransactionID;
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

    //    public void userIncrementTransaction(int amount) {
//        if (amount > 100_000_000) {
//            System.out.println("You can't add so much!");
//        } else if ((getUserBalance() + amount) > 2_000_000_000) {
//            System.out.println("We can't add your money, cause your balance can't be more then 2_000_000_000! ");
//        } else {
//            setUserBalance(getUserBalance() + amount);
//             new Transaction( amount);
//        }
//    }
//
//    public void userDecrementTransaction(int amount) {
//        if (amount > 100_000_000) {
//            System.out.println("You can't withdraw so much!");
//        } else if (getUserBalance() - amount < 0) {
//            System.out.println("You can't withdraw that much, cause in your balance don't enough money! ");
//        } else {
//            setUserBalance(getUserBalance() - amount);
//            new Transaction( amount);
//        }
//    }
//
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
