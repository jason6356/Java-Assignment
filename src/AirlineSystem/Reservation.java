package AirlineSystem;

public class Reservation {
    private String reservationNo;
    private String reservationTime;
    private rStatus reservationStatus;
    private int noOfSeatBooked;
    private double totalAmount;
    private FlightSchedule flight;

    public Reservation(String reservationNo, String reservationTime, rStatus reservationStatus, int noOfSeatBooked,
            double totalAmount, FlightSchedule flight) {
        this.reservationNo = reservationNo;
        this.reservationTime = reservationTime;
        this.reservationStatus = rStatus.PENDING;
        this.noOfSeatBooked = noOfSeatBooked;
        this.totalAmount = totalAmount;
        this.flight = flight;
    }

    //getter and setter

    public validateDate(){
        
    }
}

enum rStatus{
    PENDING,
    BOOKED,
    PAID
}
