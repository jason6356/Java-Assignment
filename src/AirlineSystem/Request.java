package AirlineSystem;

import java.util.List;
import java.util.Scanner;

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
    private RegisteredAccount requestedBy;

    // Default invoke with this();
    Request() {
        this.requestID = makeRequestID();
        Request.requestCount++;
    }

    // Remember to increase the requestCount once a request has been done
    Request(String requestDescription, String reason, Reservation oldReservation, Reservation newReservation, RegisteredAccount requestedBy) {
        this.requestID = makeRequestID();
        this.requestDescription = requestDescription;
        this.reason = reason;
        this.oldReservation = oldReservation;
        this.newReservation = newReservation;
        this.requestedBy = requestedBy;
        Request.requestCount++;
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

    public void setRequestBy(RegisteredAccount requestedBy) {
        this.requestedBy = requestedBy;
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
     * 
     * @return formatted request String
     */

    public String displayRequest() {
        if (requestDescription == "Cancel Ticket Request") {
            return String.format(
                    "\n---------------REQUEST-------------------------------------------------------------------------------------------------------------------------\nRequest ID: %s     Description: %s     Reason of Request: %s     Status: %s \n\nReservation: \n"
                            + oldReservation.displayReservation()
                            + "-----------------------------------------------------------------------------------------------------------------------------------------------",
                    requestID, requestDescription, reason, requestStatus);
        } else
            return String.format(
                    "\n---------------REQUEST-------------------------------------------------------------------------------------------------------------------------\nRequest ID: %s     Description: %s     Reason of Request: %s     Status: %s \n\nReservation: \n"
                            + oldReservation.displayReservation() + "\nNew Reservation: \n"
                            + newReservation.displayReservation()
                            + "-----------------------------------------------------------------------------------------------------------------------------------------------",
                    requestID, requestDescription, reason, requestStatus);
    }

    /**
     * Method that update the request once the staff has approve a request
     * 
     * @param request
     */

    public void updateRequest(Request request) {
        List<Reservation> customerReservationList = requestedBy.getReservations();
        boolean found = false;

        System.out.println("Before Update");
        //Display all the contents
        customerReservationList.forEach((e) -> System.out.println(e.displayReservation()));

        // if status is approved, update the reservation
        if (request.getRequestStatus() == rqStatus.APPROVED) {
            if (request.getRequestDescription() == "Cancel Ticket Request") {


                for (Reservation reservation : customerReservationList) {
                    
                    if (request.getOldReservation().equals(reservation)) {
                        // Make all the seats booked by the cancelled reservation into available again
                        Reservation.getSeatMap().get(reservation).forEach((seat) -> seat.makeSeatEmpty());
                        // Remove the reservation
                        reservation.setReservationStatus(rStatus.CANCELLED);

                        System.out.println("Found the item to cancel!");

                    }
                }
            }
             else if (request.getRequestDescription() == "Reschedule Ticket Request") {

           
                for (Reservation reservation : customerReservationList) {
                    
                    if (request.getOldReservation().equals(reservation)){
                        found = true;
                         // Make all the seats booked by the cancelled reservation into available again
                         Reservation.getSeatMap().get(reservation).forEach((seat) -> seat.makeSeatEmpty());
                         // Remove the reservation
                         reservation.setReservationStatus(rStatus.CANCELLED);
                      
                         System.out.println("Found the item to reschedule!");
                        }
                }

            }
        }

        if(found)
            customerReservationList.add(request.getNewReservation());

        System.out.println("After update");
        for (Reservation customerReservation : customerReservationList) {
            System.out.println(customerReservation.displayReservation());
        }
    }

    public void requestReason(Scanner s) {
        int reasonChoice;
        do {
            System.out.println("\nREASON OF RESCHEDULE: ");
            System.out.println("--------------------- ");
            System.out.println("1. Wrong Date Reserved");
            System.out.println("2. Wrong Flight Time Reserved");
            System.out.println("3. Wrong Location Reserved");
            System.out.println("4. Other Reasons");
            System.out.print("SELECT REASON >");
            reasonChoice = s.nextInt();
            s.nextLine();

            if (reasonChoice == 1) {
                this.reason = "Wrong Date Reserved";
            } else if (reasonChoice == 2) {
                this.reason = "Wrong Flight Time Reserved";
            } else if (reasonChoice == 3) {
                this.reason = "Wrong Location Reserved";
            } else if (reasonChoice == 4) {
                System.out.print("Kindly State the Reason: ");
                String reason = s.nextLine();
                this.reason = reason;
            } else {
                System.out.println("Invalid Selection, please try again.");
            }
        } while (reasonChoice != 1 && reasonChoice != 2 && reasonChoice != 3 && reasonChoice != 4);
    }

}