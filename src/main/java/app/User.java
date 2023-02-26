package app;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class User {
    private String name;
    private String address;
    private static int id;
    private final int userId;

    public User(String name) {
        this.name = name;
        this.userId = ++id;
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
        this.userId = ++id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(address, user.address) && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, userId);
    }

    @Override
    public String toString() {
        return "User:" + "\n" +
                "userId= " + userId + '\n' +
                "name= " + name + '\n' +
                "address= " + address;

    }
}
