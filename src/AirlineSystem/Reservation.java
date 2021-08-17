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

    // getter and setter
    public String getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
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


    public boolean validateDate() {
        return true;
    }
}

enum rStatus {
    PENDING, BOOKED, PAID
}
