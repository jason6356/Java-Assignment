package AirlineSystem;
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
        System.out.println("Payment failed!");
    else 
        balance -=amount;
        System.out.println("Payment Successful!");
    }
}
