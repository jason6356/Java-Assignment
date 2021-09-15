package AirlineSystem;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

enum rStatus {
    PENDING, BOOKED, PAID, CANCELLED
}

public class Reservation {
    private static int reservationCount = 1;
    private String reservationNo;
    private LocalDateTime reservationTime;
    private rStatus reservationStatus;
    private int noOfSeatBooked;
    private double totalAmount;
    private FlightSchedule flight;
    private static HashMap<Reservation, List<fSeat>> seatMap = new HashMap<Reservation, List<fSeat>>();

    // Parameterized
    public Reservation(int noOfSeatBooked, FlightSchedule flight, List<fSeat> bookedSeats) {
        this.reservationNo = makeReservationID();
        this.reservationTime = LocalDateTime.now(); // get the current time
        this.reservationStatus = rStatus.PENDING; // default value
        this.noOfSeatBooked = noOfSeatBooked;
        this.totalAmount = calculateTotalAmount(bookedSeats);
        this.flight = flight;
        seatMap.put(this, bookedSeats);
        // sum of all the seat book in price
        reservationCount++;
    }

    // Default
    public Reservation() {
        this(0, null, null);
    }

    /**
     * Method to create ReservationID in sequence
     * 
     * @return ReservationID in String
     */
    private static String makeReservationID() {
        if (reservationCount < 10)
            return "R00" + reservationCount;

        else if (reservationCount < 100)
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


    public static HashMap<Reservation, List<fSeat>> getSeatMap() {
        return seatMap;
    }

    /**
     * Method to display Reservation Details
     */
    public String toString() {
        return String.format(
                "\nReservation No: %s \nReservation Time: %s \nReservation Status: %s \nNumber of seat booked: %d \nTotal Amount: %.2f \n"
                        + flight.toString(),
                reservationNo, reservationTime, reservationStatus, noOfSeatBooked, totalAmount);

    }

    /**
     * Method to Display Reservation in Formatted
     * 
     * @return
     */
    public String displayReservation() {
        return String.format(
                "+---------------------------------------------------------------------------------------------------------------------------------------+\n| [%s]                                                                                                                                |\n| Reservation Time: %02d-%02d-%04d %02d:%-20d  | Status: %-50s                    |\n| Seat booked: %-30d           | Total Amount: %-50.2f              |\n| "
                        + flight.displayInfo()
                        + "\n+---------------------------------------------------------------------------------------------------------------------------------------+\n",
                reservationNo, reservationTime.getDayOfMonth(), reservationTime.getMonthValue(),
                reservationTime.getYear(), reservationTime.getHour(), reservationTime.getMinute(), reservationStatus,
                noOfSeatBooked, totalAmount);
    }

    /**
     * Method to Override equals by comparing ReservationNo
     */
    @Override
    public boolean equals(Object reservation) {
        return reservation == ((Reservation) reservation).getReservationNo();
    }

    /**
     * Method to calculate TotalAmount
     * 
     * @param seats
     * @return
     */
    private double calculateTotalAmount(List<fSeat> seats) {
        double totalAmount = 0;
        for (fSeat seat : seats) {
            totalAmount += seat.getSeatPrice();
        }
        return totalAmount;
    }
}
