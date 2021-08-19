package AirlineSystem;

enum rqStatus {
    APPROVED, REJECTED;
}

public class Request {
    private String requestID;
    private String requestDescription;
    private String reason;
    private rqStatus requestStatus;
    private Reservation reservation;

    Request() {

    }

    Request(String requestID, String requestDescription, String reason, rqStatus requestStatus,
            Reservation reservation) {
        this.requestID = requestID;
        this.requestDescription = requestDescription;
        this.reason = reason;
        this.requestStatus = requestStatus;
        this.reservation = reservation;
    }

/////////////////////////////////////////// getter///////////////////////////////////////
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

public Reservation getReservation() {
    return reservation;
}


/////////////////////////////////// Setter/////////////////////////////////////////////
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

public void setReservation(Reservation reservation) {
    this.reservation = reservation;
}

}