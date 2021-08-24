package AirlineSystem;

import java.util.List;
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

        regAccCount++;
    }

    //getter
    public int getAccID() {
        return accID;
    }
    public static int getRegAccCount() {
        return regAccCount;
    }
    
    //setter
   

    public void setAccID(int accID) {
        this.accID = accID;
    }

    //methods
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
            // only alphabet
        break;
        case 2:
            System.out.print("Enter your Last Name > ");
            super.setLastName(scan.nextLine());
            System.out.println("\n\nLast Name updated successfully !");                   
        case 3:
            System.out.print("Enter your old password > ");
            String oldPassword = scan.nextLine();
            if(!(super.getPassword().equals(oldPassword)))
            {
                System.out.println("Wrong Password");
            }
            else
            {
                System.out.println("\t*NOTE*\nYour password should be fulfilled the requirement below :\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number");
                System.out.println("Enter your NEW Password >");
                String newPassword = scan.nextLine();
                //Checking chg name
                if(!super.Checking(newPassword))
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
                System.out.print("Enter your Gender > ");
                super.setGender(scan.next().charAt(0));
                System.out.println("\n\nGender updated successfully !"); 
                //validate M or F only
        break;
        case 5:
                System.out.print("Enter your Age > ");
                super.setAge(scan.nextInt());
                System.out.println("\n\nAge updated successfully !"); 
        break;
        case 6:
                System.out.println("Enter your Email > ");
                String newEmail = scan.nextLine();
                if(!super.validateEmailFormat(newEmail))
                {
                    System.out.println("Invalid Email");//@
                }
                else
                {
                    super.setEmail(newEmail);
                    System.out.println("\n\nEmail updated Successfully !");
                }
        break;
        case 7:
                System.out.print("Enter your Phone Number > ");
                super.setPhoneNum(scan.nextLine());
                System.out.println("\n\nPhone Number updated successfully !"); 
                // only digit and - +
        break;
        case 8:
            return;
        
        default:
        System.out.println("Invalid Input.");
        break;
    }

    System.out.print("Another Update? (Y/N) > ");
    again = scan.next();
    //B <- invalid input
} while (again == "Y");
    scan.close();
   }

   public void rescheduleTicket(Reservation reservation) {
    Scanner scanner = new Scanner (System.in);
    List<FlightSchedule> flightScheduleList = Main.getFlightSchedules();
    Reservation newReservation = new Reservation(int noOfSeatBooked, double totalAmount, FlightSchedule flight);

    System.out.println("\nRESERVATION DETAILS\n"+ reservation.toString());

    System.out.println("\nAVAILABLE FLIGHT SCHEDULE");

        for (FlightSchedule schedule : flightScheduleList ) {
            System.out.println(flightScheduleList.toString());
        }

    System.out.println(flightScheduleList.toString());

    System.out.print("Enter new schedule choice: ");
    int choice=scanner.nextInt();
    newReservation.setFlightSchedule(flightScheduleList.get(choice - 1));

    //DO A MENU (LIST OF REASONS)
    System.out.print("\nReason of reschedule: ");
    String reason = scanner.nextLine();

    System.out.println("\nConfirm to reschedule? (Y/N) > ");
    String next = scanner.next();

    if(next == "Y"){
        //get request list from the data storage
        List<Request> requestList = Main.getRequests(); //data storage

        //customer request
        Request customerRequest = new Request("Reschedule Ticket Request", reason, reservation, newReservation);

        //add the request to the request list
        requestList.add(customerRequest);

  
        System.out.println("Requested for Rescheduling Ticket.");
        //can put in staff module
        if (customerRequest.getRequestStatus() == rqStatus.APPROVED){
            //reservation = new reservation
        }
    
    }

        scanner.close();
}

public void cancelTicket(Reservation reservation) {
    Scanner scanner = new Scanner (System.in);
    
    System.out.println("\nRESERVATION DETAILS\n"+reservation.toString());

//DO MENU
    System.out.println("\nReason of Cancel: ");
    System.out.println("\n1.  ");
    System.out.println("\n2.  ");
    System.out.println("\n3. Other");
    System.out.print("\nEnter Choice of Reason: ");
    String reason = scanner.nextLine();

    System.out.println("\nConfirm to cancel? (Y/N) > ");
    String next = scanner.next();

    if(next == "Y"){
        Request request = new Request("Cancel Ticket Request", reason, reservation, null);

        System.out.println("Requested for Cancelling Ticket.");
        
        if (request.getRequestStatus() == rqStatus.APPROVED){
            reservation = null;
        }
    }

    scanner.close();
}


public void welcome(){ // can remove if dw, main line 149
    System.out.println("\n\n\n\n\n\n\n\n\nWelcome To SaiLou Airline !");
}

}
