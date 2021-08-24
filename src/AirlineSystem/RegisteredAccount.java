package AirlineSystem;

import java.util.Scanner;



public class RegisteredAccount extends Account {

    private static int regAccCount = 1;
    private int accID;


    public RegisteredAccount() {
    }

    public RegisteredAccount(int accID, String password, String firstName, String lastName, Address address,
            char gender, int age, String email, String phoneNum) {
        super(password, firstName, lastName, address, gender, age, email, phoneNum);
        this.accID = accID;

        regAccCount ++;
    }

    /////////////////////////////////// getter////////////////////////////////////
    public int getAccID() {
        return accID;
    }
    public static int getRegAccCount() {
        return regAccCount;
    }
    
    ///////////////////////////////// setter///////////////////////////////////////
   

    public void setAccID(int accID) {
        this.accID = accID;
    }

    //////////////////////////////// method///////////////////////////////////////////
    public void updateProfile(){
        Scanner scan = new Scanner (System.in);
        String again;
    do{
        System.out.println("\n\n\t\tUpdate Profile\n============================================"); //header
        System.out.print("1. First Name  [ " + super.getFirstName() + " ]\n" + 
                           "2. Last Name   [ " + super.getLastName() + " ]\n" + 
                           "3. Password \n"+ 
                           "4. Gender      [ " + super.getGender() + " ]\n" + 
                           "5. Age         [ " + super.getAge() + " ]\n" + 
                           "6. Email       [ " + super.getEmail() + " ]\n" + 
                           "7. Phone No.   [ " + super.getPhoneNum() + " ]\n"  +
                           "8. Exit \n\n" +
                           "Please Enter The Number that you wished to change > " );
        int updateOption = scan.nextInt();


        switch (updateOption){
        case 1:
            System.out.print("Enter your First Name > ");
            super.setFirstName(scan.nextLine());
            System.out.println("\n\nFirst Name updated successfully !");
        break;
        case 2:
            System.out.print("Enter your Last Name > ");
            super.setLastName(scan.nextLine());
            System.out.println("\n\nLast Name updated successfully !");                   
        case 3:
            System.out.print("Enter your old password > ");
            String oldPassword = scan.nextLine();
            if(super.getPassword().equals(oldPassword) == false)
            {
                System.out.println("Wrong Password");
            }
            else
            {
                System.out.println("\t*NOTE*\nYour password should be fulfilled the requirement below :\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number");
                System.out.println("Enter your NEW Password >");
                String newPassword = scan.nextLine();
                if(super.Checking(newPassword) == false)
                {
                    System.out.println("Invalid Password");
                }
                else
                {
                    super.setPassword(newPassword);
                    System.out.println("\n\nPassword Updated Successfully !");
                }
            }
        break;
        case 4:
        
        break;
        case 5:
        
        break;
        case 6:
        
        break;
        case 7:
        
        break;
        case 8:
            return;
        
        default:
        System.out.println("Invalid Input.");
        break;
    }

    System.out.print("Another Update? (Y/N) > ");
    again = scan.next();
} while (again == "Y");
    scan.close();
   }

   public void rescheduleTicket(Reservation reservation, FlightSchedule[] schedule) {
    Scanner scanner = new Scanner (System.in);
    Request request = new Request();
    Reservation newReservation = new Reservation();

    System.out.println("\nRESERVATION DETAILS\n"+reservation.toString());

    System.out.println("\nAVAILABLE FLIGHT SCHEDULE");

    for(int i=0;i<schedule.length;i++){
        System.out.println(" Schedule " + (i+1) + schedule[i].toString() + "\n---------------------------------------------\n");
    }   
    System.out.print("Enter new schedule choice: ");
    int choice=scanner.nextInt();
    newReservation.setFlightSchedule(schedule[choice-1]);

    System.out.print("\nReason of reschedule: ");
    request.setReason(scanner.nextLine());

    System.out.println("\nConfirm to reschedule? (Y/N) > ");
    String next = scanner.next();

    if(next == "Y"){
        //get request list from the data storage
        List<Request> requestList = Main.getRequests(); //data storage
        //customer request
        Request customerRequest = new Request(reservation.getReservationNo(), "Reschedule Ticket Request", newReservation);
        //add the request to the request list
        requestList.add(customerRequest);

        request.setRequestID(reservation.getReservationNo());
        request.setRequestDescription("Reschedule Ticket Request");
        request.setReservation(newReservation);
        System.out.println("Requested for Rescheduling Ticket.");
        //can put in staff module
        if (request.getRequestStatus() == rqStatus.APPROVED){
            reservation.setFlightSchedule(schedule[choice-1]);
            System.out.println("Request Approved, Ticket Rescheduled");
        }
        else
            System.out.println("Request Rejected.");
    }
       

        scanner.close();
}

public void cancelTicket(Reservation reservation) {
    Scanner scanner = new Scanner (System.in);
    
    System.out.println("\nRESERVATION DETAILS\n"+reservation.toString());

    System.out.print("\nReason of cancellation: ");
    String reason = scanner.nextLine();

    System.out.println("\nConfirm to cancel? (Y/N) > ");
    String next = scanner.next();

    if(next == "Y"){
        Request request = new Request();

        request.setRequestID(reservation.getReservationNo());
        request.setRequestDescription("Cancel Ticket Request");
        request.setReason(reason);
        request.setReservation(reservation);

        System.out.println("Requested for Cancelling Ticket.");
        if (request.getRequestStatus() == rqStatus.APPROVED){
            reservation = null;
            System.out.println("Request Approved, Ticket Cancelled.");
        }
        else
            System.out.println("Request Rejected.");
    }

    scanner.close();
}


}
