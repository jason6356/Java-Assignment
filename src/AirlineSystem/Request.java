package AirlineSystem;

import java.util.List;

enum rqStatus {
    APPROVED, REJECTED
}

public class Request {
    private static int requestCount = 0;
    private String requestID;
    private String requestDescription;
    private String reason;
    private rqStatus requestStatus;
    private Reservation oldReservation; // if reschedule then we will have oldReservation and new reservation
    private Reservation newReservation; // cancel, oldReservation, new Reservation == null

    Request() {

    }

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
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

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

    // method
    public String toString() {
        return String.format(
                "Request ID: %s      Request Description: %s      Reason of Request: %s \nOld Reservation: "
                        + oldReservation.toString() + "\n\nNew Reservation: " + newReservation.toString(),
                requestID, requestDescription, reason);
    }

    public String displayRequest() {
        if (requestDescription == "Cancel Ticket Request") {
            return String
                    .format("Request ID: %s      Request Description: %s      Reason of Request: %s   Status: %s\n\nOld Reservation: "
                            + oldReservation.displayReservation(), requestID, requestDescription, reason,requestStatus);
        } else
            return String.format(
                    "Request ID: %s      Request Description: %s      Reason of Request: %s \n\nOld Reservation: "
                            + oldReservation.displayReservation() + "\n\nNew Reservation: " + newReservation.displayReservation(),
                    requestID, requestDescription, reason);
    }

    public void updateRequest(Request request) {
        List<Reservation> reservationList = Main.getReservations();

        // if status is approved, update the reservation
        if (request.getRequestStatus() == rqStatus.APPROVED) {
            if (request.getRequestDescription() == "Cancel Ticket Request") {

                for (Reservation reservation : reservationList) {
                    if (request.getOldReservation().equals(reservation))
                        reservation = null;
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