package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User newUser;
        UserAccount newUserAccount;
        Transaction userTransaction = new Transaction();
        WorkWithDataBase addDataIntoDataBase = new WorkWithDataBase();
        Scanner scan;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No sqlite driver");
            return;
        }

        System.out.println("Create a new User:");
        boolean addUser = true;

        do {
            scan = new Scanner(System.in);
            System.out.println("Enter your NAME than enter your EMAIL:");
            newUser = new User(scan.nextLine(), scan.nextLine());
            while (addDataIntoDataBase.checkUserForaMatch(newUser.getName(), newUser.getAddress())) {
                System.out.println("Enter another name:");
                newUser.setName(scan.nextLine());
                System.out.println("Enter another address:");
                newUser.setAddress(scan.nextLine());
            }
            addDataIntoDataBase.addUserIntoDb(newUser);


            boolean addAccount = true;

            while (addAccount) {
                System.out.println("Do you want to add account? yes/no");
                scan = new Scanner(System.in);
                if (scan.nextLine().equalsIgnoreCase("yes")) {
                    addAccount = true;

                    System.out.println("Enter currency type: 1-EURO, 2-DOLLAR, 3-RUSSIAN_RUBLE,  4-BELARUSIAN_RUBLE;");

                    newUserAccount = new UserAccount(newUser, Currency.currencySelection(scan.nextInt()));
                    addDataIntoDataBase.checkAccountBeforeAdding();
                    addDataIntoDataBase.addAccountIntoDb(newUser, newUserAccount);

                    int choose = 3;

                    do {

                        System.out.println("Would you like to change your balance?");

                        System.out.println("If you want to 'add' enter 1." + "\n" + "If you want to 'take off' enter 2."
                                + "\n" + "If you don't want do anything enter 3.");

                        choose = scan.nextInt();
                        if (choose == 1) {
                            System.out.println("Enter your amount:");
                            userTransaction.userIncrementTransaction(newUserAccount, scan.nextInt());  //добавляем на счет
                            addDataIntoDataBase.checkTransactionIDForaMatch();
                            addDataIntoDataBase.addTransactionIntoDb(newUserAccount);  // записываемв БД
                        } else if (choose == 2) {
                            System.out.println("Enter your amount:");
                            userTransaction.userDecrementTransaction(newUserAccount, scan.nextInt());  //снимаем со счета
                            addDataIntoDataBase.checkTransactionIDForaMatch();
                            addDataIntoDataBase.addTransactionIntoDb(newUserAccount);  // записываемв БД
                        }

                    } while (choose != 3);

                } else {
                    addAccount = false;
                }
            }
            System.out.println("Do you want to add another user? yes/no");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
                addUser = true;
            } else {
                addUser = false;
            }

        } while (addUser);
        scan.close();
    }
}
