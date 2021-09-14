package AirlineSystem;

import java.util.List;

enum rqStatus {
    APPROVED, REJECTED
}

public class Request {
    private static int requestCount = 1;
    private String requestID;
    private String requestDescription;
    private String reason;
    private rqStatus requestStatus;
    private Reservation oldReservation; // if reschedule then we will have oldReservation and new reservation
    private Reservation newReservation; // cancel, oldReservation, new Reservation == null

    //Default invoke with this();
    Request() {
        this.requestID = makeRequestID();
    }

    //Remember to increase the requestCount once a request has been done
    Request(String requestDescription, String reason, Reservation oldReservation, Reservation newReservation) {
        this.requestID = makeRequestID();
        this.requestDescription = requestDescription;
        this.reason = reason;
        this.oldReservation = oldReservation;
        this.newReservation = newReservation;
    }

    /**
     * Method to create RequestID in sequence
     * 
     * @return RequestID in String
     */
    private static String makeRequestID() {
        if (requestCount < 10)
            return "RQ00" + requestCount;

        else if (requestCount < 100)
            return "RQ0" + requestCount;

        return "RQ" + requestCount;
    }

    // getter
    public String getRequestID() {
        return requestID;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public String getReason() {
        return reason;
    }

    public rqStatus getRequestStatus() {
        return requestStatus;
    }

    public Reservation getOldReservation() {
        return oldReservation;
    }

    public Reservation getNewReservation() {
        return newReservation;
    }

    // setter
    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRequestStatus(rqStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setOldReservation(Reservation oldReservation) {
        this.oldReservation = oldReservation;
    }

    public void setNewReservation(Reservation newReservation) {
        this.newReservation = newReservation;
    }

    /**
     * Method to display Object in String
     */
    public String toString() {
        return String.format(
                "Request ID: %s      Request Description: %s      Reason of Request: %s \nOld Reservation: "
                        + oldReservation.toString() + "\n\nNew Reservation: " + newReservation.toString(),
                requestID, requestDescription, reason);
    }

    /**
     * Method to display Request Details In Formatted Way
     * @return formatted request String
     */

    public String displayRequest() {
        if (requestDescription == "Cancel Ticket Request") {
            return String.format(
                    "Request ID: %s      Request Description: %s      Reason of Request: %s   \n\nOld Reservation: \n+---------------------------------------------------------------------------------------------------------------------------------------+"
                            + oldReservation.displayReservation()
                            + "\n+---------------------------------------------------------------------------------------------------------------------------------------+",
                    requestID, requestDescription, reason);
        } else
            return String.format(
                    "Request ID: %s      Request Description: %s      Reason of Request: %s \n\nOld Reservation: \n+---------------------------------------------------------------------------------------------------------------------------------------+"
                            + oldReservation.displayReservation()
                            + "\n+---------------------------------------------------------------------------------------------------------------------------------------+\n\nNew Reservation: \n+---------------------------------------------------------------------------------------------------------------------------------------+"
                            + newReservation.displayReservation()
                            + "\n+---------------------------------------------------------------------------------------------------------------------------------------+",
                    requestID, requestDescription, reason);
    }

    /**
     * Method that update the request once the staff has approve a request
     * @param request
     */

    public void updateRequest(Request request) {
        List<Reservation> reservationList = Main.getReservations();

        // if status is approved, update the reservation
        if (request.getRequestStatus() == rqStatus.APPROVED) {
            if (request.getRequestDescription() == "Cancel Ticket Request") {

                for (Reservation reservation : reservationList) {
                    if (request.getOldReservation().equals(reservation)){
                        //Make all the seats booked by the cancelled reservation into available again
                        Reservation.getSeatMap().get(reservation).forEach((seat) -> seat.makeSeatEmpty());
                        //Reset the reservation
                        reservation = null;
                    }
                }

            } else if (request.getRequestDescription() == "Reschedule Ticket Request") {

                for (Reservation reservation : reservationList) {
                    if (request.getOldReservation().equals(reservation))
                        reservation = request.newReservation;
                }

            }
        }
    }

}