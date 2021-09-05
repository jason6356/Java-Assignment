package AirlineSystem;

enum sStatus{
    SEATED,NOTSEATED
}
/**
 * NOTE : the way of invoking this class is same as invoking the Seat Class,
 * Set the total amount of seats first!
 */
class fSeat extends Seat {

    private double seatPrice;
    private sStatus seatStatus;
    private final double BUSSINESS_SEAT_PRICE = 1500.00;
    private final double ECONOMY_SEAT_PRICE = 750.00;

    //no-args constructor
    public fSeat(String seatNo){
        //Default Seat
        super(seatNo);
        //Assigning Price with its class type
        this.seatPrice = this.seatClass == sClass.BUSSINESS ? BUSSINESS_SEAT_PRICE : ECONOMY_SEAT_PRICE;
        this.seatStatus = sStatus.NOTSEATED;
    }

    //getter and setter
    public double getSeatPrice() {
        return seatPrice;
    }
    public sStatus getSStatus(){
        return seatStatus;
    }
    public sStatus getSeatStatus() {
        return seatStatus;
    }
    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    //ToString
    /**
     * ToString Method
     * @return SeatNo, SeatClass, SeatPrice, SeatStatus in String Format
     */
    @Override
    public String toString() {
        return super.toString() + String.format("SeatPrice = %.2f \nSeatStatus = %s \n", seatPrice,seatStatus.toString());
    }

    //Methods
    /**
     * This method makes the seat become seated     
     * 
     * */
    public void makeSeatSeated(){
        this.seatStatus = sStatus.SEATED;
    }

    /**
     * This method makes the seat become empty and available to other people
     */
    public void makeSeatEmpty(){
        this.seatStatus = sStatus.NOTSEATED;
    }

}