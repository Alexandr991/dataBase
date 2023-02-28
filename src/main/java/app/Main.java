package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User newUser;
        UserAccount newUserAccount;
        Transaction userTransaction = new Transaction();
        WorkWithDataBase workWithDataIntoDataBase = new WorkWithDataBase();
        Scanner scan;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No sqlite driver");
            return;
        }
        int decision = 1;
        do {
            try {
                scan = new Scanner(System.in);
                System.out.println("Would you find user o create user?");
                System.out.println("Enter number '1' to find user, \n enter number '2' to create user, \n enter number '3' to quit. ");
                decision = scan.nextInt();
                if (decision == 1) {
                    //тест метода найти пользователя в БД
                    System.out.println("Enter your NAME than enter your EMAIL:");
                    String name = scan.next();
                    String address = scan.next();
                    workWithDataIntoDataBase.findUserAccountsInDataBase(name, address);


                } else if (decision == 2) {
                    System.out.println("Create a new User:");

                    // создаём пользователя и добавляем в БД
                    scan = new Scanner(System.in);
                    System.out.println("Enter your NAME than enter your EMAIL:");
                    newUser = new User(scan.nextLine(), scan.nextLine());
                    while (workWithDataIntoDataBase.checkUserForaMatch(newUser.getName(), newUser.getAddress())) {
                        System.out.println("Enter another name:");
                        newUser.setName(scan.nextLine());
                        System.out.println("Enter another address:");
                        newUser.setAddress(scan.nextLine());
                    }
                    workWithDataIntoDataBase.addUserIntoDb(newUser);


                    // создаём аккаунт для текущего пользователя
                    boolean addAccount = true;

                    while (addAccount) {
                        System.out.println("Do you want to add account? yes/no");
                        scan = new Scanner(System.in);
                        if (scan.nextLine().equalsIgnoreCase("yes")) {
                            addAccount = true;

                            System.out.println("Enter currency type: 1-EURO, 2-DOLLAR, 3-RUSSIAN_RUBLE,  4-BELARUSIAN_RUBLE;");

                            newUserAccount = new UserAccount(newUser, Currency.currencySelection(scan.nextInt()));
                            workWithDataIntoDataBase.checkAccountBeforeAdding();
                            workWithDataIntoDataBase.addAccountIntoDb(newUser, newUserAccount);

                            // выбор действия с балансом
                            int choose = 3;
                            do {
                                try {
                                    System.out.println("Would you like to change your balance?");

                                    System.out.println("If you want to 'add' enter 1." + "\n" + "If you want to 'take off' enter 2."
                                            + "\n" + "If you don't want do anything enter 3.");
                                    choose = scan.nextInt();
                                    if (choose == 1) {
                                        System.out.println("Enter your amount:");
                                        userTransaction.userIncrementTransaction(newUserAccount, scan.nextInt());  //добавляем на счет
                                        workWithDataIntoDataBase.checkTransactionIDForaMatch();
                                        workWithDataIntoDataBase.addTransactionIntoDb(newUserAccount);  // записываемв БД
                                    } else if (choose == 2) {
                                        System.out.println("Enter your amount:");
                                        userTransaction.userDecrementTransaction(newUserAccount, scan.nextInt());  //снимаем со счета
                                        workWithDataIntoDataBase.checkTransactionIDForaMatch();
                                        workWithDataIntoDataBase.addTransactionIntoDb(newUserAccount);  // записываемв БД
                                    } else if (choose == 3) {
                                        continue;
                                    } else {
                                        throw new InputMismatchException();
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Incorrect input!");
                                }
                            }
                            while (choose != 3);
                        } else {
                            addAccount = false;
                        }
                    }
                } else if (decision == 3) {
                    continue;
                } else {
                    throw new InputMismatchException();
                }
                scan.close();
            } catch (InputMismatchException e) {
                System.out.println("You entered incorrect number!");
            }

        }
        while (decision != 3);


    }

}
