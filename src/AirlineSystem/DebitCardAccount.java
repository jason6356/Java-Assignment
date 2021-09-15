package AirlineSystem;
import java.util.Scanner;
public class DebitCardAccount implements PaymentMethod{

    private String bank;
    private int cardNo;
    private int cvsNo;
    private String validDate;
    private String holderName; 
    private double balance;
    private int tac; 

    public DebitCardAccount (){}
    public DebitCardAccount(String bank, int cardNo, int cvsNo, String validDate, String holderName,
    double balance, int tac){
        this.bank = bank;
        this.cardNo = cardNo;
        this.cvsNo = cvsNo;
        this.validDate = validDate;
        this.holderName = holderName;
        this.balance = balance;
        this.tac = tac;
    }

    ////////////////////////////////////////getter///////////////////////////////////
    public String getBank() {
        return bank;
    }
    public int getCardNo() {
        return cardNo;
    }
    public int getCvsNo() {
        return cvsNo;
    }
    public String getValidDate() {
        return validDate;
    }
    public String getHolderName() {
        return holderName;
    }
    public double getBalance() {
        return balance;
    }
    public int getTac() {
        return tac;
    }

    ///////////////////////////////////setter/////////////////////////////////////////
    //NO SETTER


    ////////////////////////////////methods/////////////////////////////////////////
    @Override
    public void pay(double amount) {
        if(balance<amount)
        System.out.println("Payment failed");
    else 
        balance -=amount;
        
    }

    public Boolean validateDC(){
        Boolean error = false; 
        Scanner dc = new Scanner(System.in);
        int inputCardNumber;
        int inputCVS;
        String inputValidDate;
        System.out.println("\n\tCard Payment");
        System.out.println("================");
        do{ //	either 1 wrong will loop here at the end
        System.out.println("*NOTE* \nKindly Enter 0 to exit");
        System.out.println("Card Number > ");                 //input card 
        inputCardNumber = dc.nextInt();
        if ( inputCardNumber == 0)                     // if user input 0 will exit
        {
            return true; 
        }
        System.out.println("CVS > ");              // input CVS
        inputCVS = dc.nextInt();
        if ( inputCVS == 0 )             
        {
            return true; 
        }

        System.out.println("Valid Date (MM/YY) > ");
        inputValidDate = dc.nextLine();
        if ( inputValidDate.equals("0"))
        {
            return true; 
        }                 
        if((inputCardNumber!=cardNo)|| (inputCVS != cvsNo) || (inputValidDate.equals(validDate))){
            System.out.println("Incorrect Card Details!");
            error = true;
        }
        }while(error == true);

        return error; // this error is false, means no error
    }
    
}
