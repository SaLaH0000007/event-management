package OOP;

import java.util.Arrays;

abstract class Employee {
    private int id;
    private String name;
    private String phoneNumber;
    private char[] email;

    public Employee(int id, String name, String phoneNumber, char[] email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public char[] getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", Phone=" + phoneNumber + ", Email=" + Arrays.toString(email) + "]";
    }
}

