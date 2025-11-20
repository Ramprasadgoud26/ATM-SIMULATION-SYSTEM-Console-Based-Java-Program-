package com.project;

import java.util.*;

class ATMAccount {
    private String number;
    private int pin;
    private double balance;

    public ATMAccount(String number, int pin, double balance) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(int pin) { return this.pin == pin; }
    public double getBalance() { return balance; }

    public void deposit(double amt) {
        if (amt <= 0) throw new IllegalArgumentException("Invalid amount!");
        balance += amt;
    }

    public void withdraw(double amt) {
        if (amt <= 0) throw new IllegalArgumentException("Invalid amount!");
        if (amt > balance) throw new IllegalArgumentException("Insufficient Balance!");
        balance -= amt;
    }
}

class ATM {
    private HashMap<String, ATMAccount> accounts = new HashMap<>();

    public ATM() {
        accounts.put("12345", new ATMAccount("12345", 1111, 5000));
        accounts.put("98765", new ATMAccount("98765", 2222, 10000));
    }

    public ATMAccount login(String acc, int pin) {
        ATMAccount a = accounts.get(acc);
        if (a != null && a.validatePin(pin)) return a;
        return null;
    }
}

public class ATMApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.print("Enter Account Number: ");
        String acc = sc.next();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        ATMAccount user = atm.login(acc, pin);

        if (user == null) {
            System.out.println("Login Failed!");
            return;
        }

        while (true) {
            System.out.println("\n=== ATM MENU ===");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1:
                        System.out.println("Available Balance: " + user.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter Amount: ");
                        double amt = sc.nextDouble();
                        user.withdraw(amt);
                        System.out.println("Withdrawal Successful!");
                        break;
                    case 3:
                        System.out.print("Enter Amount: ");
                        amt = sc.nextDouble();
                        user.deposit(amt);
                        System.out.println("Deposit Successful!");
                        break;
                    case 4:
                        System.out.println("Thank you for using ATM!");
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
