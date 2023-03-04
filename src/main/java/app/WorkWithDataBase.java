package app;

import java.sql.*;

public class WorkWithDataBase {
    Connection connection = null;
    Statement statement = null;

    public void addAccountIntoDb(User user, UserAccount account) {
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO Accounts ( accountId,userId,currency) VALUES ('"
                    + UserAccount.getAccountID() + "', '" + user.getUserId() + "', '" + account.getUserCurrency() + "')");

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Can't close the connection");
            }
        }

    }

    public void addTransactionIntoDb(UserAccount account) {
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();

            statement.executeUpdate("UPDATE Accounts SET balance = '" + account.getUserBalance()
                    + "' WHERE accountId= " + UserAccount.getAccountID());

            statement.executeUpdate("INSERT INTO Transactions (transactionId, accountId, amount) VALUES ('"
                    + UserAccount.getTransactionID() + "', '" + UserAccount.getAccountID() + "', '" + account.getUserAmount() + "')");

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Can't close the connection");
            }
        }

    }

    public void addUserIntoDb(User user) {
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO Users ( userId,name,address) VALUES ('"
                    + user.getUserId() + "', '" + user.getName() + "', '" + user.getAddress() + "')");

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Can't close the connection");
            }
        }

    }


    //метод для проверки на дубликат пользователя

    public boolean checkUserForaMatch(String userName, String userAddress) {

        boolean isExist = false;
        int userIDinDataBase = 0;
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, address FROM Users");
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(userName)
                        && rs.getString("address").equalsIgnoreCase(userAddress)) {
                    isExist = true;
                } else {
                    ++userIDinDataBase;
                    User.setUserId(userIDinDataBase + 1);
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Can't close the connection");
            }
        }

        return isExist;
    }

    public void checkTransactionIDForaMatch() {

        int userTransactionIDinDataBase = 0;
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT transactionId FROM Transactions");
            while (rs.next()) {
                ++userTransactionIDinDataBase;

            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Can't close the connection");
            }
        }

        UserAccount.setTransactionID(userTransactionIDinDataBase + 1);
    }

    public void checkAccountBeforeAdding() {

        int userAccountIDinDataBase = 0;
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT accountId FROM Accounts");
            while (rs.next()) {

                ++userAccountIDinDataBase;

            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Can't close the connection");
            }
        }
        UserAccount.setAccountID(userAccountIDinDataBase + 1);
    }

    //данный метод предназначен для определения существует ли пользователь с таким именем и адресом и сколько у него аккаунтов.
    public void findUserAccountsInDataBase(String userName, String userAddress) {

        int countAccountID = 0;
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, address, userID FROM Users");
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(userName)
                        && rs.getString("address").equalsIgnoreCase(userAddress)) {
                    System.out.println("This user is exist in data base.");

                    ResultSet rs2 = statement.executeQuery("SELECT accountId, currency, balance FROM Accounts Where userID= '"
                            + rs.getString("userId") + "'");
                    while (rs2.next()) {
                        countAccountID++;
                        System.out.println("Number of account: " + countAccountID + " AccountId: " + rs2.getInt("accountId") + " Currency: " + rs2.getString("currency") + " User balance: " + rs2.getString("balance"));

                    }
                    System.out.println("User has " + countAccountID + " accounts.");
                }
            }
            if (countAccountID == 0) {
                System.out.println("This user is not exist.");
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Can't close the connection");
            }
        }

    }


}

