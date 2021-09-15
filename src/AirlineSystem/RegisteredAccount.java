package AirlineSystem;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class RegisteredAccount extends Account {

    private static int nthAcc = 0;
    private String accID;
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private List<Request> requests = new ArrayList<Request>();

    public RegisteredAccount() {
        this("","","",null,'\0',0,"","");
    }

    public RegisteredAccount(String password, String firstName, String lastName, Address address,
            char gender, int age, String email, String phoneNum) {
        super(password, firstName, lastName, address, gender, age, email, phoneNum);
        this.accID = makeAccID();
        //Increment Once a staff has been created
        nthAcc++;
    }

    private static String makeAccID(){
        if(nthAcc < 10)
            return "A00" + nthAcc;
        else if(nthAcc < 100)
            return "A0" + nthAcc;
    
        return "A" + nthAcc;
    }

    //getter
    public String getAccID() {
        return accID;
    }
    public static int getNthAcc() {
        return nthAcc;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Request> getRequests() {
        return requests;
    }
    
    //setter


//methods
public void makeReservation(Scanner sc){

    //Input, Country from another country ->
    List<FlightSchedule> fsList = Main.getFlightSchedules();
    List<FlightSchedule> queryFsList = new ArrayList<FlightSchedule>();
    FlightSchedule fs = null;
    char continueBook = 'N';
    
    do{
        System.out.println("Reservation Form");
        System.out.print("Enter the country u would like to go - ");
        String destination = sc.next();
        System.out.print("Enter the country u are currently at - ");
        String location = sc.next();
        boolean found = false;

        System.out.println("Available Flight Schedules");
        System.out.println("Depart Country : " + location);
        System.out.println("Arrive Country : " + destination);
        System.out.printf("%-2s|%10s|%5s|%-37s|%-37s|%-16s|%-24s|","NO","DepartDate","Time","Location","Destination","Arrival Time","Direction");
        Main.printLine(138);
        //Print the flightschedules the user wants
        int i = 1;
        for (FlightSchedule flightSchedule : fsList) {
            if(flightSchedule.getLocation().getLocation().equals(location) && flightSchedule.getDestination().getLocation().equals(destination)){
                System.out.printf("%-2d|",i);
                System.out.println(flightSchedule);
                queryFsList.add(flightSchedule);
                found = true;
                i++;
            }
        }
        
        if(found){
            System.out.println("Found!");
            System.out.print("Enter the No to book the flight - ");
            int flightNo = sc.nextInt();

            while(flightNo < 0 || flightNo > i){
                System.out.println("Invalid Input");
                System.out.print("Enter the No to book the flight - ");
                flightNo = sc.nextInt();
            }

            fs = queryFsList.get(flightNo-1);

            System.out.print("Would u like to book it ? (Y/N) - ");
            char choice = sc.next().charAt(0);
            //validate choice
            while(Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N'){
                System.out.println("Invalid Input");
                System.out.print("Would u like to book it ? (Y/N) - ");
                choice = sc.next().charAt(0);
            }

            if(Character.toUpperCase(choice) == 'Y'){
                
                //Clear Screen
                Main.clearConsole();
                //Display Flight Seats
                fs.displaySeats();

                //Book Seat
                List<fSeat> bookedSeat = fs.bookSeat(sc);

                System.out.println("Successfully booked the seat");
                Reservation reservation = new Reservation(bookedSeat.size(),fs,bookedSeat);
                List<Reservation> rList = Main.getReservations();
                rList.add(reservation);
                
                //This Instance add the reservation to the object
                reservations.add(reservation);
            }
        }
        else
            System.out.println("No Such country found!");

        System.out.print("Continue to book another reservation ? ");
        continueBook = sc.next().charAt(0);
        while(Character.toUpperCase(continueBook)!='Y' && Character.toUpperCase(continueBook)!='N'){
            System.out.println("Invalid Input!");
            System.out.print("Continue to book another reservation ? ");
            continueBook = sc.next().charAt(0);
        }
        
        
    }while(Character.toUpperCase(continueBook) == 'Y');
}

public void confirmTicket(Scanner s){

    /**
     * Confirm Ticket
     * 1. Get all reservations that are still pending
     * 2. Let user choose which reservations to confirm
     * 3. Confirm them whether they want to purchase the ticket
     * 4. if yes then call the payment module 
     * 5. else loop ask whether they still want to continue to confirm ticket?
     * 
     */
    Main.clearConsole();
    System.out.println("Your reservations that are not confirmed");
    Main.printLine(148);
    List<Reservation> queryList = new ArrayList<Reservation>();
    int i = 1;
    for (Reservation reservation : reservations) {
        if(reservation.getReservationStatus() == rStatus.PENDING){
            queryList.add(reservation);
            System.out.printf("%2d", i++);
            System.out.println(reservation);
        }
    }

    System.out.print("Enter the No to confirm reservation - ");
    int choice = s.nextInt();
    while(choice < 0 || choice > i){
        System.out.println("Invalid Input");
        System.out.print("Enter the No to confirm reservation - ");
        choice = s.nextInt();
    }

    System.out.print("Are you sure to confirm? (Y/N) - ");
    char confirm = s.next().charAt(0);

    while(Character.toUpperCase(confirm) != 'Y' && Character.toUpperCase(confirm) != 'N'){
        System.out.println("Invalid Input");
        System.out.print("Are you sure to confirm? (Y/N) - ");
        confirm = s.next().charAt(0);
    }

    if(confirm == 'Y'){
        queryList.get(choice - 1).setReservationStatus(rStatus.BOOKED);

        System.out.print("Do u want to pay for this reservation ? (Y/N) - ");
        char payChoice = s.next().charAt(0);
        while(Character.toUpperCase(payChoice) != 'Y' && Character.toUpperCase(payChoice) != 'N'){
            System.out.println("Invalid Input");
            System.out.print("Do u want to pay for this reservation ? (Y/N) - ");
            payChoice = s.next().charAt(0);
        }

        if(payChoice == 'Y'){
            payment(s, queryList.get(choice - 1).getTotalAmount());
            System.out.println("Calling junwei method");
        }
        else{
            System.out.println("For your reminder, the reservation that is not confirmed yet after 14 days will be removed!");
            s.nextLine();
            s.nextLine();
        }
    }
}

public void updateProfile(Scanner scan){
        
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
    
   }

//Reschedule Ticket
public void rescheduleTicket(Reservation reservation, Scanner scanner) {
    
    List<FlightSchedule> flightScheduleList = Main.getFlightSchedules();
    List<FlightSchedule> availableSchedules = new ArrayList<FlightSchedule>(); 

    //customer request
    Request customerRequest = new Request();
    customerRequest.setRequestDescription("Reschedule Ticket Request");
    customerRequest.setOldReservation(reservation);

    Main.clearConsole();
    System.out.println("\nCURRENT RESERVATION DETAILS:\n============================\n"+ reservation.displayReservation());

    //display available flight schedule
    System.out.println("\n\nAVAILABLE FLIGHT SCHEDULE:");
    System.out.println("==========================");

    int choice = 0;
    int n;
    do {
        n = 1;
        for (FlightSchedule flightSchedule : flightScheduleList) {
            if(flightSchedule != reservation.getFlightSchedule()){
                System.out.print(n + ". ");
                System.out.println(flightSchedule.toString());
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                availableSchedules.add(flightSchedule);
                n++;
                }
            }
        
        System.out.printf("\nEnter new schedule choice [1...%d]: ",n-1);
        choice = scanner.nextInt();

        if(choice < 1 || choice > n-1 ){
            System.out.println("\nInvalid Choice, Please Enter Again!");
            System.out.println("\n\nAVAILABLE FLIGHT SCHEDULE:");
            System.out.println("==========================");
        }

    } while(choice < 1 || choice > n-1);

  
    FlightSchedule targetSchedule = availableSchedules.get(choice-1);

    //Display Seats
    targetSchedule.displaySeats();

    //Get seats
    List<fSeat> bookedSeats = targetSchedule.bookSeat(scanner);

    //Set the new reservation
    Reservation newReservation = new Reservation(bookedSeats.size(),targetSchedule,bookedSeats);
    
    //DO A MENU (LIST OF REASONS)
    customerRequest.requestReason(scanner);

    System.out.print("\nConfirm to reschedule? (Y/N) > ");
    char next = scanner.next().charAt(0);
    
    while(!super.validateOption(next)){
        System.out.println("Invalid Input.");
        System.out.print("Confirm to reschedule? (Y/N) >");
        next = scanner.next().charAt(0);
    }

    if(Character.toUpperCase(next) == 'Y'){
        
        //set new reservation
        customerRequest.setNewReservation(newReservation);

        //add the request to the request list
        requests.add(customerRequest);

        //add request to main request list
        List<Request> mainRequests = Main.getRequests();
        mainRequests.add(customerRequest);

        Main.clearConsole();
       
        System.out.println(customerRequest.displayRequest());
  
        System.out.println("\nRequested for Rescheduling Ticket.");
        System.out.println("ENTER ANY KEY TO CONTINUE >");
        scanner.nextLine();
        scanner.nextLine();
    
    }
}

//Cancel Ticket
public void cancelTicket(Reservation reservation, Scanner scanner) {

    //Customer Request 
    Request request = new Request();
    request.setRequestDescription("Cancel Ticket Request");
    request.setOldReservation(reservation);
    request.setNewReservation(null);

    Main.clearConsole();
    System.out.println("\nRESERVATION DETAILS\n====================\n"+reservation.displayReservation());

    
    //DO A MENU (LIST OF REASONS)
    request.requestReason(scanner);

    System.out.print("\nConfirm to cancel? (Y/N) > ");
    char next = scanner.next().charAt(0);
    while(!super.validateOption(next)){
        System.out.println("Invalid Input.");
        System.out.print("Confirm to cancel? (Y/N) > ");
        next = scanner.next().charAt(0);
    }

    if(Character.toUpperCase(next) == 'Y'){
        
        //add the request to the customer request list
        requests.add(request);

        //add request to main request list
        List<Request> mainRequests = Main.getRequests();
        mainRequests.add(request);

        Main.clearConsole();
       
        System.out.println(request.displayRequest());

        System.out.println("\nRequested for Cancelling Ticket.");
        System.out.println("ENTER ANY KEY TO CONTINUE >");
        scanner.nextLine();
        scanner.nextLine();
    }

}


public void welcome(){ // can remove if dw, main line 149
    System.out.println("\n\n\n\n\n\n\n\n\nWelcome To SaiLou Airline !");
}


//Check Reservation
public void checkRequestStatus(Request reservationRequest, Scanner scanner){
    boolean found = false;

    for (Request request : requests) {

        if (request == reservationRequest){
            found = true;
                        
            System.out.println(request.displayRequest());

            if(request.getRequestStatus()==rqStatus.APPROVED){
    
                if(request.getRequestDescription() == "Reschedule Ticket Request"){

                    System.out.println("\nREQUEST APPROVED.\n");
                    System.out.println("\nOld Reservation have been updated to New Reservation.");
                }
                else if(request.getRequestDescription() == "Cancel Ticket Request"){

                    System.out.println("\nREQUEST APPROVED.\n");
                    System.out.println("\nReservation have been Cancelled.");
                }

            }
            else if(request.getRequestStatus()==rqStatus.REJECTED){
               
                System.out.println("\nREQUEST REJECTED.\n");
            }
            else{

                System.out.println("\nREQUEST PENDING.\n\n");
            }
        }
    }
    if(found == false)
        System.out.println("\nRESERVATION HAVE NO REQUEST.\n");

    System.out.println("ENTER ANY KEY TO CONTINUE >");
        scanner.nextLine();
        scanner.nextLine();

}
     /**
     * Method to choose payment method 
     * 
     */
    public void payment(Scanner s, double amount){
          //simply create bank details
        FPX fpx1 = new FPX("maybank", "vic123", "apple", "abcd1234", 1000.0, 1234);
        DebitCardAccount debit1 = new DebitCardAccount("maybank", 123456789, 111, "09/25", "Wong Jun Wei", 1000.0, 123);
        int option;
        boolean error;

    do{
        System.out.println("Enter 0 to exit");
        System.out.println("Which payment method would you like to choose? ");
        System.out.println("1. FPX\n2.Debit Card");
        option = s.nextInt();

        if (option == 1)
        {
            error = fpx1.validateFPX();
            if ( error == false)
            {
                fpx1.pay(amount);
                System.out.println("Payment Successful!");
                return;
            }
        }
        else if ( option == 2)
        {
            error = debit1.validateDC();
            if(error == false)
            {
                debit1.pay(amount);
                System.out.println("Payment Successful!");
                return;
            }
        }
        else if (option ==0)
        {
            return;
        }
        else 
        {
            System.out.println("Incorrect Input! Please Try Again");
        }
    }while(option!=0);

    System.out.println("Payment Cancelled!");
    }

}
