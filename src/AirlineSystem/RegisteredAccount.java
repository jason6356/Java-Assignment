package AirlineSystem;

import java.util.Scanner;

public class RegisteredAccount extends Account {

    private static int regAccCount = 1;
    private int accID;
    private int creditCardNumber;

    public RegisteredAccount() {
    }

    public RegisteredAccount(int accID, String password, String firstName, String lastName, Address address,
            char gender, int age, String email, String phoneNum, int creditCardNumber) {
        super(password, firstName, lastName, address, gender, age, email, phoneNum);
        this.accID = accID;

        this.creditCardNumber = creditCardNumber;
    }

    /////////////////////////////////// getter////////////////////////////////////
    public int getAccID() {
        return accID;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    ///////////////////////////////// setter///////////////////////////////////////
    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }



    public void setAccID(int accID) {
        this.accID = accID;
    }

    //////////////////////////////// method///////////////////////////////////////////
    public void updateProfile(){
        Scanner scan = new Scanner (System.in);
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

        break;
        case 2:
                                  
        case 3:
        
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
        System.out.println("Invalid Input. Please Try Again. ");
        break;
    }
   }

   public void rescheduleTicket(Reservation reservation, FlightSchedule[] schedule) {
    Scanner scanner = new Scanner (System.in);
    Request request = new Request();
    Reservation newReservation = new Reservation(reservation.getReservationNo(), reservation.getReservationTime(), reservation.getReservationStatus(), reservation.getNoOfSeatBooked(),
    reservation.getTotalAmount(), reservation.getFlightSchedule());

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
        request.setRequestID(reservation.getReservationNo());
        request.setRequestDescription("reschedule ticket request");
        request.setReservation(newReservation);
        if (request.getRequestStatus() == rqStatus.APPROVED){
            reservation.setFlightSchedule(schedule[choice-1]);
            System.out.println("Request Approved.");
        }
        else
            System.out.println("Request Rejected.");
    }
       

        scanner.close();
}
}
