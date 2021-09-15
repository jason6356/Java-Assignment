package AirlineSystem;

import java.util.Scanner;

public class FPX implements PaymentMethod{

    private String bank;
    private String userName;
    private String securityPhrase;
    private String password;
    private double balance; 
    private int tac;

    public FPX(){}
    public FPX(String bank, String userName, String securityPhrase, String password, double balance, int tac){
        this.bank = bank;
        this.userName = userName;
        this.securityPhrase = securityPhrase;
        this.password = password;
        this.balance = balance;
        this.tac = tac;
    }

    ///////////////////////////////////////getter///////////////////////////////////////
    public String getBank() {
        return bank;
    }
    public String getUserName() {
        return userName;
    }
    public String getSecurityPhrase() {
        return securityPhrase;
    }
    public String getPassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }
    public int getTac() {
        return tac;
    }

    //////////////////////////////////////setter////////////////////////////////////////////
    // NO SETTER 


    /////////////////////////////////////methods////////////////////////////////
    @Override
    public void pay(double amount) {
        if(balance<amount)
        {
            System.out.println("Payment failed");
        }

    else 
    {
        balance -=amount;
        System.out.println("Payment Successful!");
    }
    }
    
}
