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
                    + account.getUserAccountId() + "', '" + user.getUserId() + "', '" + account.getUserCurrency() + "')");

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
                    + "' WHERE accountId= " + account.getUserAccountId());

            statement.executeUpdate("INSERT INTO Transactions (transactionId, accountId, amount) VALUES ('"
                    + account.getUserTransactionID() + "', '" + account.getUserAccountId() + "', '" + account.getUserAmount() + "')");

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

    public boolean checkAccountForaMatch(String userName,String userAddress) {
        Connection connection = null;
        Statement statement = null;
        boolean isExist = false;
        try {
            connection =
                    DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydb.db");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, address FROM Users");
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(userName)
                        && rs.getString("address").equalsIgnoreCase(userAddress)) {
                    isExist = true;
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
}

