package AirlineSystem;
import java.time.LocalDateTime;

enum rStatus {
    PENDING, BOOKED, PAID
}

public class Reservation {
    private static int reservationCount = 0;
    private String reservationNo;
    private LocalDateTime reservationTime;
    private rStatus reservationStatus;
    private int noOfSeatBooked;
    private double totalAmount;
    private FlightSchedule flight;
   //TODO: Do Specific Booked Seat!



    //Parameterized
    public Reservation(int noOfSeatBooked,double totalAmount, FlightSchedule flight) {
        this.reservationNo = makeReservationID();
        this.reservationTime = LocalDateTime.now(); // get the current time
        this.reservationStatus = rStatus.PENDING; //default value
        this.noOfSeatBooked = noOfSeatBooked;
        this.totalAmount = totalAmount;
        this.flight = flight;
        
        // sum of all the seat book in price
        reservationCount++;
    }

    //Default
    public Reservation(){
        this(0,0.0,null);
    }

    /**
    Method to create ReservationID in sequence
    @return ReservationID in String
     */
    private static String makeReservationID(){
        if(reservationCount<10)
            return "R00" +reservationCount;
        
        else if(reservationCount < 100)
            return "R0" + reservationCount;

        return "R" + reservationCount; 
    }

    // getter and setter
    public String getReservationNo() {
        return reservationNo;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public rStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(rStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public int getNoOfSeatBooked() {
        return noOfSeatBooked;
    }

    public void setNoOfSeatBooked(int noOfSeatBooked) {
        this.noOfSeatBooked = noOfSeatBooked;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public FlightSchedule getFlightSchedule() {
        return flight;
    }

    public void setFlightSchedule(FlightSchedule flight) {
        this.flight = flight;
    }
    
    //TODO: Use String.format better
    public String toString() {
        return "\nReservation No: " + reservationNo + "\nReservation Time: " + reservationTime
                + "\nReservation Status: " + reservationStatus + "\nNumber of seat booked: " + noOfSeatBooked
                + "\nTotal Amount: " + totalAmount + flight.toString();
    }
}
