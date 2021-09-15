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
        System.out.println("Payment failed");
    else 
        balance -=amount;
        
    }

    public boolean validateFPX(){
        Boolean error = false; 
        Scanner fpx = new Scanner(System.in);
        String inputBank;
        String inputUserName;
        String inputPassword;
        System.out.println("\n\tFPX");
        System.out.println("================");
        do{ //	✓ bank name, ✓user name , X password or terbalik - loop here
        do{ //  X bank name                  - loop here
        System.out.println("*NOTE* \nKindly Enter 0 to exit");
        System.out.println("eg: maybank");
        System.out.println("Input preferred bank name > ");                 //input bank name
        inputBank = fpx.nextLine();
        if ( inputBank.equals("0"))                     // if user input 0 will exit
        {
            return true; 
        }
        if (!inputBank.equalsIgnoreCase(bank))                // if bank name not exsist 
        {                                                      // print error message 
            System.out.println("Incorrect Bank Name!");
            error = true;
        }
        } while(error == true);                             //loop to the beginning

        System.out.println("UserName > ");              // input user name
        inputUserName = fpx.nextLine();
        if ( inputUserName.equals("0"))                 //as long as not 0, will proceed to input password
        {
            return true; 
        }
        System.out.println("Password > ");
        inputPassword = fpx.nextLine();
        if ( inputUserName.equals("0"))
        {
            return true; 
        }                                               // if either username or password wrong, will loop back to the top
        if(!inputPassword.equals(password) || !inputPassword.equals(password)){
            System.out.println("Incorrect User Name or Password!");
            error = true;
        }
        }while(error == true);

        System.out.println("TAC (check your phone) > ");        // input tac
        int inputTac = fpx.nextInt();
        if(inputTac != tac)
        {
            System.out.println("Incorrect TAC\nPayment Terminated\nPlease Try Again!");
            return true;
        }
     
        return error; // this error is false, means no error
    }
    
}
