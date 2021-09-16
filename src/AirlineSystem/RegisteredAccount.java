package AirlineSystem;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class RegisteredAccount extends Account {

    private static int nthAcc = 1;
    private String userID;
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private List<Request> requests = new ArrayList<Request>();

    public RegisteredAccount() {
        this("","","",null,'\0',0,"","");
    }

    public RegisteredAccount(String password, String firstName, String lastName, Address address,
            char gender, int age, String email, String phoneNum) {
        super(password, firstName, lastName, address, gender, age, email, phoneNum);
        this.userID = makeUserID();
        //Increment Once a staff has been created
        nthAcc++;
    }

    private static String makeUserID(){
        if(nthAcc < 10)
            return "A00" + nthAcc;
        else if(nthAcc < 100)
            return "A0" + nthAcc;
    
        return "A" + nthAcc;
    }

    //getter
    public String getUserID() {
        return userID;
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
public void makeReservation(Scanner s){

    //Input, Country from another country ->
    List<FlightSchedule> fsList = Main.getFlightSchedules();
    List<FlightSchedule> queryFsList = new ArrayList<FlightSchedule>();
    FlightSchedule fs = null;
    char continueBook = 'N';

    System.out.print("One Way Flight or Two Way Flight (1 - One, 2 - Two) : ");
    int wayChoice = s.nextInt();
    

    do{
        System.out.println("Reservation Form");
        System.out.print("Enter the country u would like to go - ");
        String destination = s.next();
        System.out.print("Enter the country u are currently at - ");
        String location = s.next();
        boolean found = false;

        System.out.println("Available Flight Schedules");
        System.out.println("Depart Country : " + location);
        System.out.println("Arrive Country : " + destination);
        Main.printLine(138);
        System.out.printf("%-2s|%10s|%5s|%-37s|%-37s|%-16s|%-24s|\n","NO","DepartDate","Time","Location","Destination","Arrival Time","Direction");
        Main.printLine(138);
        //Print the flightschedules the user wants
        int i = 1;
        for (FlightSchedule flightSchedule : fsList) {
            if(flightSchedule.getLocation().getLocation().equals(location) && flightSchedule.getDestination().getLocation().equals(destination)){
                System.out.printf("%-2d|",i);
                System.out.println(flightSchedule.displayInfo());
                queryFsList.add(flightSchedule);
                found = true;
                i++;
            }
        }
        Main.printLine(138);
        
        if(found){
            System.out.println("Found!");
            System.out.print("Enter the No to book the flight - ");
            int flightNo = s.nextInt();

            while(flightNo < 0 || flightNo > i){
                System.out.println("Invalid Input");
                System.out.print("Enter the No to book the flight - ");
                flightNo = s.nextInt();
            }

            fs = queryFsList.get(flightNo-1);

            System.out.print("Would u like to book it ? (Y/N) - ");
            char choice = s.next().charAt(0);
            //validate choice
            while(Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N'){
                System.out.println("Invalid Input");
                System.out.print("Would u like to book it ? (Y/N) - ");
                choice = s.next().charAt(0);
            }

            if(Character.toUpperCase(choice) == 'Y'){
                
                //Clear Screen
                Main.clearConsole();
                //Display Flight Seats
                fs.displaySeats();

                //Book Seat
                List<fSeat> bookedSeat = fs.bookSeat(s);

                System.out.println("Successfully booked the seat");
                Reservation reservation = new Reservation(bookedSeat.size(),fs,bookedSeat);
                List<Reservation> rList = Main.getReservations();
                rList.add(reservation);
                
                //This Instance add the reservation to the object
                reservations.add(reservation);

                if(wayChoice == 2)
                    twoWayReserve(s,reservation);
            }
        }
        else
            System.out.println("No Such country found!");

        System.out.print("Continue to book another reservation ? (Y/N)");
        continueBook = s.next().charAt(0);

        while(Character.toUpperCase(continueBook)!='Y' && Character.toUpperCase(continueBook)!='N'){
            System.out.println("Invalid Input!");
            System.out.print("Continue to book another reservation ? ");
            continueBook = s.next().charAt(0);
        }



    }while(Character.toUpperCase(continueBook) == 'Y');
    
    s.nextLine();

}

//Split into 2 methods, 1 way reservation , 2 way reservations

public void twoWayReserve(Scanner s,Reservation from){

    List<FlightSchedule> fsList = Main.getFlightSchedules();
    List<FlightSchedule> queryFsList = new ArrayList<FlightSchedule>();
    FlightSchedule fs = null;
    boolean found = false;
    String newLocation = from.getFlightSchedule().getDestination().getLocation();
    String newDestination = from.getFlightSchedule().getLocation().getLocation();

    System.out.println("Available Flight Schedules");
    System.out.println("Depart Country");
    System.out.println("Depart Country : " + newLocation);
    System.out.println("Arrive Country : " + newDestination);
    Main.printLine(138);
    System.out.printf("%-2s|%10s|%5s|%-37s|%-37s|%-16s|%-24s|\n","NO","DepartDate","Time","Location","Destination","Arrival Time","Direction");
    Main.printLine(138);
    //Print the flightschedules the user wants
    int i = 1;
    for (FlightSchedule flightSchedule : fsList) {
        if(flightSchedule.getLocation().getLocation().equals(newLocation) && flightSchedule.getDestination().getLocation().equals(newDestination)){
            System.out.printf("%-2d|",i);
            System.out.println(flightSchedule.displayInfo());
            queryFsList.add(flightSchedule);
            found = true;
            i++;
        }
    }
    Main.printLine(138);
    if(found){
        System.out.println("Found!");
        System.out.print("Enter the No to book the flight - ");
        int flightNo = s.nextInt();

        while(flightNo < 0 || flightNo > i){
            System.out.println("Invalid Input");
            System.out.print("Enter the No to book the flight - ");
            flightNo = s.nextInt();
        }

        fs = queryFsList.get(flightNo-1);

        System.out.print("Would u like to book it ? (Y/N) - ");
        char choice = s.next().charAt(0);
        //validate choice
        while(Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N'){
            System.out.println("Invalid Input");
            System.out.print("Would u like to book it ? (Y/N) - ");
            choice = s.next().charAt(0);
        }

        if(Character.toUpperCase(choice) == 'Y'){
            
            //Clear Screen
            Main.clearConsole();
            //Display Flight Seats
            fs.displaySeats();

            //Book Seat
            List<fSeat> bookedSeat = fs.bookSeat(s);

            System.out.println("Successfully booked the seat");
            Reservation reservation = new Reservation(bookedSeat.size(),fs,bookedSeat);
            List<Reservation> rList = Main.getReservations();
            rList.add(reservation);
            
            //This Instance add the reservation to the object
            reservations.add(reservation);
        }
    }
    else
        System.out.println("No FlightSchedules found for Return Trip!");

}

public void displayReservation(){
    Scanner ntg = new Scanner(System.in);

    if(reservations.size() == 0)
        System.out.println("No Reservations");
    else{
        System.out.println("Your Reservations\n\n\n\n");
        reservations.forEach((e) -> System.out.println(e.displayReservation(e)));
    }
    ntg.nextLine();
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
    boolean found = false;
    for (Reservation reservation : reservations) {
        if(reservation.getReservationStatus() == rStatus.PENDING){
            found = true;
            queryList.add(reservation);
            System.out.printf("%2d", i++);
            System.out.println(reservation.displayReservation(reservation));
        }
    }
    if(found){
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
                if(payment(s, queryList.get(choice - 1).getTotalAmount())){
                    queryList.get(choice - 1).setReservationStatus(rStatus.PAID);
                }
                else{
                    queryList.get(choice - 1).setReservationStatus(rStatus.PENDING);
                }
            }
            else{
                queryList.get(choice - 1).setReservationStatus(rStatus.PENDING);
                System.out.println("For your reminder, the reservation that is not confirmed yet after 14 days will be removed!");
            }
        }
    }
    else
        System.out.println("No Reservations to Confirmed");

    s.nextLine();
    s.nextLine();
}

public void updateProfile(Scanner s){
        
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
        int updateOption = s.nextInt();
        s.nextLine();
        switch (updateOption){
        case 1:
            System.out.print("Enter your First Name > ");   //INPUT NEW FIRST NAME
            String newFirstName = s.nextLine();
            while(!super.validateName(newFirstName))          //VALIDATE FIRST NAME
            {
                System.out.println("Your Name Should Only Contain Characters");
                System.out.print("Enter your First Name > ");
                newFirstName = s.nextLine();
            }
            super.setFirstName(newFirstName);           //UPDATE FIRST NAME
            System.out.println("\n\nFirst Name updated successfully !");
           
        break;
        case 2:
        System.out.print("Enter your Last Name > ");            //INPUT NEW LAST NAME    
        String newLastName = s.nextLine();           
        while(!super.validateName(newLastName))                    //VALIDATE NEW LAST NAME
        {
            System.out.println("Your Name Should Only Contain Characters");
            System.out.print("Enter your Last Name > ");
            newLastName = s.nextLine();
        }
        super.setLastName(newLastName);                    //UPDATE LAST NAME
        System.out.println("\n\nLast Name updated successfully !");
        break;
        case 3:
            System.out.println("GUIDE---> Input 'forget' if forget password");
            System.out.print("Enter your old password > ");         //ENTER OLD PASSWORD
            String oldPassword = s.nextLine();
            while((!(super.getPassword().equals(oldPassword))) )          //COMPARE PASSWORD AND INPUT
            {
                if(oldPassword.equals("forget"))                               //if user forget password then return back to mainmenu
                {
                    return;
                }
                System.out.println("Wrong Password");
                System.out.print("Enter your old password > ");         //ENTER OLD PASSWORD
                oldPassword = s.nextLine();
            }
                System.out.println("\t*NOTE*\nYour password should be fulfilled the requirement below :\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number");
                System.out.print("Enter your NEW Password >");        //ENTER NEW PASSWORD
                String newPassword = s.nextLine();
               
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
                char newGender = s.next().charAt(0);
                while(!super.validateGender(newGender))                   //VALIDATE GENDER EITHER M OR F
                {
                    System.out.println("Male or Female only");
                    System.out.print("Enter your Gender (M/F) > ");
                    newGender = s.next().charAt(0);
                }
                super.setGender(Character.toUpperCase(newGender));              //UPDATE GENDER WITH CAPITAL
                System.out.println("\n\nGender updated successfully !"); 
        break;
        case 5:
                System.out.print("Enter your Age > ");
                super.setAge(s.nextInt());
                System.out.println("\n\nAge updated successfully !"); 
        break;
        case 6:
                System.out.println("Enter your Email > ");                  //ENTER EMAIL
                String newEmail = s.nextLine();
                while(!super.validateEmailFormat(newEmail))                    //VALIDATE EMAIL FORMAT, CONTAIN "@"
                {
                    System.out.println("Invalid Email");
                    System.out.println("Enter your Email > ");                 
                    newEmail = s.nextLine();
  
                }
                    super.setEmail(newEmail);                                   //UPDATE EMAIL
                    System.out.println("\n\nEmail updated Successfully !");
        break;
        case 7:
                System.out.print("Enter your Phone Number > ");         //ENTER NUMBER
                String newPhoneNum = s.nextLine();
                while(!super.validatePhoneNum(newPhoneNum))           //VALIDATE ONLY NUMBER WITH +COUNTRY CODE
                {
                    System.out.println("Phone Number Should Start With '+' followed by country code and phone number");
                    System.out.println("Eg : +60123456789");
                    System.out.print("Enter your Phone Number > ");
                    newPhoneNum = s.nextLine();
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
    another = s.next().charAt(0);
    while(!Main.validateOption(another)){
        System.out.println("Invalid Input.");
        System.out.print("Another Update? (Y/N) > ");
        another = s.next().charAt(0);
    }
} while (Character.toUpperCase(another) == 'Y');
    
   }

//Reschedule Ticket
public void rescheduleTicket(Reservation reservation, Scanner s, RegisteredAccount user) {
    
    List<FlightSchedule> flightScheduleList = Main.getFlightSchedules();
    List<FlightSchedule> availableSchedules = new ArrayList<FlightSchedule>(); 

    //customer request
    Request customerRequest = new Request();
    customerRequest.setRequestDescription("Reschedule Ticket Request");
    customerRequest.setOldReservation(reservation);
    customerRequest.setRequestBy(user);

    Main.clearConsole();
    System.out.println("\nCURRENT RESERVATION DETAILS:\n============================\n"+ reservation.displayReservation(reservation));

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
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                availableSchedules.add(flightSchedule);
                n++;
                }
            }
        
        System.out.printf("\nEnter new schedule choice [1...%d]: ",n-1);
        choice = s.nextInt();

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
    List<fSeat> bookedSeats = targetSchedule.bookSeat(s);

    //Set the new reservation
    Reservation newReservation = new Reservation(bookedSeats.size(),targetSchedule,bookedSeats);
    
    //DO A MENU (LIST OF REASONS)
    customerRequest.requestReason(s);

    System.out.print("\nConfirm to reschedule? (Y/N) > ");
    char next = s.next().charAt(0);
    
    while(!Main.validateOption(next)){
        System.out.println("Invalid Input.");
        System.out.print("Confirm to reschedule? (Y/N) >");
        next = s.next().charAt(0);
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
        s.nextLine();
        s.nextLine();
    
    }
}

//Cancel Ticket
public void cancelTicket(Reservation reservation, Scanner s, RegisteredAccount user) {

    //Customer Request 
    Request request = new Request();
    request.setRequestDescription("Cancel Ticket Request");
    request.setOldReservation(reservation);
    request.setNewReservation(null);
    request.setRequestBy(user);

    Main.clearConsole();
    System.out.println("\nRESERVATION DETAILS\n====================\n"+reservation.displayReservation(reservation));

    
    //DO A MENU (LIST OF REASONS)
    request.requestReason(s);

    System.out.print("\nConfirm to cancel? (Y/N) > ");
    char next = s.next().charAt(0);
    while(!Main.validateOption(next)){
        System.out.println("Invalid Input.");
        System.out.print("Confirm to cancel? (Y/N) > ");
        next = s.next().charAt(0);
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
        s.nextLine();
        s.nextLine();
    }

}


public void welcome(){ // can remove if dw, main line 149
    System.out.println("\n\n\n\n\n\n\n\n\nWelcome To SaiLou Airline !");
    System.out.printf("User ID: %s", userID);
}


//Check Reservation

public void checkRequestStatus(Request reservationRequest, Scanner s) {

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
        s.nextLine();
        s.nextLine();

}
     /**
     * Method to choose payment method 
     * 
     */
    public boolean payment(Scanner s, double amount){
          //simply create bank details
        FPX fpx1 = new FPX("maybank", "vic123", "apple", "abcd1234", 1000.0, 1234);
        DebitCardAccount debit1 = new DebitCardAccount("maybank", 123456789, 111, "09/25", "Wong Jun Wei", 1000.0, 123);
        int option;
        boolean error = false;

        int inputCardNumber;
        int inputCVS;
        String inputValidDate;

    do{
        System.out.println("Enter 0 to exit");
        System.out.println("Which payment method would you like to choose? ");
        System.out.println("1. FPX\n2. Debit Card");
        option = s.nextInt();
        s.nextLine();
        if (option == 1)
        {
            String inputBank;
            String inputUserName;
            String inputPassword;
            System.out.println("\n\tFPX");
            System.out.println("================");
            do{ //	✓ bank name, ✓user name , X password or terbalik - loop here
            do{ //  X bank name                  - loop here
            System.out.println("*NOTE* \nKindly Enter 0 to exit\n");
            System.out.println("eg: maybank");
            System.out.print("Input preferred bank name > ");                 //input bank name
            inputBank = s.nextLine();
            if ( inputBank.equals("0"))                     // if user input 0 will exit
            {
                return false; 
            }
            if (!inputBank.equalsIgnoreCase(fpx1.getBank()))                // if bank name not exsist 
            {                                                      // print error message 
                System.out.println("Incorrect Bank Name!");
                error = true;
            }
            } while(error == true);                             //loop to the beginning
    
            System.out.print("UserName > ");              // input user name
            inputUserName = s.next();
            s.nextLine();
            if ( inputUserName.equals("0"))                 //as long as not 0, will proceed to input password
            {
                return false; 
            }
            System.out.print("Password > ");
            inputPassword = s.nextLine();
            if ( inputUserName.equals("0"))
            {
                return false; 
            }                                               // if either username or password wrong, will loop back to the top
            if(!inputPassword.equals(fpx1.getPassword()) || !inputPassword.equals(fpx1.getPassword())){
                System.out.println("Incorrect User Name or Password!");
                error = true;
            }
        
            }while(error == true);
    
            System.out.print("TAC (check your phone) > ");        // input tac
            int inputTac = s.nextInt();
            s.nextLine();
            if(inputTac != fpx1.getTac())
            {
                System.out.println("Incorrect TAC\nPayment Terminated\nPlease Try Again!");
                return false;
            }
            fpx1.pay(amount);
            s.nextLine();
            s.nextLine();
            return true;
        }
               
            
        else if ( option == 2)
        {
            System.out.println("\n\tCard Payment");
            System.out.println("================");
            do{ //	either 1 wrong will loop here at the end
            System.out.println("*NOTE* \nKindly Enter 0 to exit");
            System.out.print("Card Number > ");                 //input card 
            inputCardNumber = s.nextInt();
            s.nextLine();
            if ( inputCardNumber == 0)                     // if user input 0 will exit
            {
                return false; 
            }
            System.out.print("CVS > ");              // input CVS
            inputCVS = s.nextInt();
            s.nextLine();
            if ( inputCVS == 0 )             
            {
                return false; 
            }

            System.out.print("Valid Date (MM/YY) > ");
            inputValidDate = s.nextLine();
            if ( inputValidDate.equals("0"))
            {
                return false; 
            }                 
            if((inputCardNumber!=debit1.getCardNo())|| (inputCVS != debit1.getCvsNo()) || (!inputValidDate.equals(debit1.getValidDate()))){
                System.out.println("Incorrect Card Details!");
                error = true;
            }
            }while(error == true);

                debit1.pay(amount);
                s.nextLine();
                s.nextLine();
                return true;

        }
     
        else if (option == 0)
        {
            return false;
        }
        else 
        {
            System.out.println("Incorrect Input! Please Try Again");
        }
            

}while(option!=0);

    System.out.println("Payment Cancelled!");
    return false;
    }

}
