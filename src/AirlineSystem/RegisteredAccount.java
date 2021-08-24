package AirlineSystem;

import java.util.List;
import java.util.Scanner;



public class RegisteredAccount extends Account {

    private static int nthAcc = 0;
    private String accID;


    public RegisteredAccount() {
    }

    public RegisteredAccount(String password, String firstName, String lastName, Address address,
            char gender, int age, String email, String phoneNum) {
        super(password, firstName, lastName, address, gender, age, email, phoneNum);
        this.accID = makeAccID();
        //Increment Once a staff has been created
        nthAcc++;
    }

    //getter
    public String getAccID() {
        return accID;
    }
    public static int getNthAcc() {
        return nthAcc;
    }
    
    //setter


    //methods
    public void updateProfile(){
        Scanner scan = new Scanner (System.in);
        char another;
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
            System.out.print("Enter your First Name > ");   //INPUT NEW FIRST NAME
            String newFirstName = scan.nextLine();
            while(!super.validateName(newFirstName))          //VALIDATE FIRST NAME
            {
                System.out.println("Your Name Should Only Contain Characters");
                System.out.print("Enter your First Name > ");
                newFirstName = scan.nextLine();
            }
            super.setFirstName(newFirstName);           //UPDATE FIRST NAME
            System.out.println("\n\nFirst Name updated successfully !");
           
        break;
        case 2:
        System.out.print("Enter your Last Name > ");            //INPUT NEW LAST NAME    
        String newLastName = scan.nextLine();           
        while(!super.validateName(newLastName))                    //VALIDATE NEW LAST NAME
        {
            System.out.println("Your Name Should Only Contain Characters");
            System.out.print("Enter your Last Name > ");
            newLastName = scan.nextLine();
        }
        super.setFirstName(newLastName);                    //UPDATE LAST NAME
        System.out.println("\n\nLast Name updated successfully !");

        case 3:
            System.out.println("GUIDE---> Input 'abc' if forget password");
            System.out.print("Enter your old password > ");         //ENTER OLD PASSWORD
            String oldPassword = scan.nextLine();
            while((!(super.getPassword().equals(oldPassword))) )          //COMPARE PASSWORD AND INPUT
            {
                if(oldPassword == "abc")                               //if user forget password then return back to mainmenu
                {
                    return;
                }
                System.out.println("Wrong Password");
                System.out.print("Enter your old password > ");         //ENTER OLD PASSWORD
                oldPassword = scan.nextLine();
            }
                System.out.println("\t*NOTE*\nYour password should be fulfilled the requirement below :\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number");
                System.out.println("Enter your NEW Password >");        //ENTER NEW PASSWORD
                String newPassword = scan.nextLine();
               
                if(!super.validatePassword(newPassword))                //VALIDATE NEW PASSWORD FORMAT
                {
                    System.out.println("Invalid Password");
                }
                else
                {
                    super.setPassword(newPassword);                     //UPDATE PASSWORD
                    System.out.println("\n\nPassword Updated Successfully !");
                }
        break;
        case 4:
                System.out.print("Enter your Gender (M/F) > ");                 //ENTER GENDER
                char newGender = scan.next().charAt(0);
                while(!super.validateGender(newGender))                   //VALIDATE GENDER EITHER M OR F
                {
                    System.out.println("Male or Female only");
                    System.out.print("Enter your Gender (M/F) > ");
                    newGender = scan.next().charAt(0);
                }
                super.setGender(Character.toUpperCase(newGender));              //UPDATE GENDER WITH CAPITAL
                System.out.println("\n\nGender updated successfully !"); 
        break;
        case 5:
                System.out.print("Enter your Age > ");
                super.setAge(scan.nextInt());
                System.out.println("\n\nAge updated successfully !"); 
        break;
        case 6:
                System.out.println("Enter your Email > ");                  //ENTER EMAIL
                String newEmail = scan.nextLine();
                while(!super.validateEmailFormat(newEmail))                    //VALIDATE EMAIL FORMAT, CONTAIN "@"
                {
                    System.out.println("Invalid Email");
                    System.out.println("Enter your Email > ");                 
                    newEmail = scan.nextLine();
  
                }
                    super.setEmail(newEmail);                                   //UPDATE EMAIL
                    System.out.println("\n\nEmail updated Successfully !");
        break;
        case 7:
                System.out.print("Enter your Phone Number > ");         //ENTER NUMBER
                String newPhoneNum = scan.nextLine();
                while(!super.validatePhoneNum(newPhoneNum))           //VALIDATE ONLY NUMBER WITH +COUNTRY CODE
                {
                    System.out.println("Phone Number Should Start With '+' followed by country code and phone number");
                    System.out.println("Eg : +60123456789");
                    System.out.print("Enter your Phone Number > ");
                    newPhoneNum = scan.nextLine();
                }
                super.setPhoneNum(newPhoneNum);
                System.out.println("\n\nPhone Number updated successfully !"); 
        break;
        case 8:
            return;
        
        default:
        System.out.println("Invalid Input.");
        break;
    }
    System.out.print("Another Update? (Y/N) > ");
    another = scan.next().charAt(0);
    while(!super.validateOption(another)){
        System.out.println("Invalid Input.");
        System.out.print("Another Update? (Y/N) > ");
        another = scan.next().charAt(0);
    }
} while (Character.toUpperCase(another) == 'Y');
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

    System.out.print("\nConfirm to reschedule? (Y/N) > ");
    char next = scanner.next().charAt(0);
    while(!super.validateOption(next)){
        System.out.println("Invalid Input.");
        System.out.print("Confirm to reschedule? (Y/N) >");
        next = scanner.next().charAt(0);
    }

    if(next == 'Y'){
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

    System.out.print("\nConfirm to cancel? (Y/N) > ");
    char next = scanner.next().charAt(0);
    while(!super.validateOption(next)){
        System.out.println("Invalid Input.");
        System.out.print("Confirm to cancel? (Y/N) > ");
        next = scanner.next().charAt(0);
    }

    if(next == 'Y'){
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

private static String makeAccID(){
    if(nthAcc < 10)
        return "A00" + nthAcc;
    else if(nthAcc < 100)
        return "A0" + nthAcc;

    return "A" + nthAcc;
}
}
