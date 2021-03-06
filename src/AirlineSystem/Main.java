package AirlineSystem;

import java.util.Scanner;

import java.util.List;
import java.time.*;
import java.util.ArrayList;

public class Main {
    // predefined data to store
    private static List<Staff> staffAccountList = new ArrayList<Staff>();
    private static List<RegisteredAccount> accountList = new ArrayList<RegisteredAccount>();
    private static List<FlightSchedule> flightScheduleList = new ArrayList<FlightSchedule>();
    private static List<Airport> airportList = new ArrayList<Airport>();
    private static List<Airline> airlineList = new ArrayList<Airline>();
    private static List<Flight> flightList = new ArrayList<Flight>();
    private static List<Request> requests = new ArrayList<Request>();
    private static List<Reservation> reservations = new ArrayList<Reservation>();
    
    public static void main(String[] args) {
       
        clearConsole();
        Account guestAcc = new Account();
        insertDataToList();
        Scanner s = new Scanner(System.in);
        boolean end = false;

    do{
        guestAcc = new Account();
        int choice = displayMenu(s);
        switch (choice) {
            case 1:
                guestAcc.availableFlights();
                guestAcc = promptUserChoice(s, guestAcc);
                break;
            case 2:
                guestAcc = registerAccount(s);
                break;
            case 3:
                guestAcc = userLogin(s);
                break;
            case 4:
                guestAcc = staffLogin(s);
                break;
            case 5:
                guestAcc = null;
                end = true;
                break;
            default:
                System.out.println("Invalid choice!!!");
                break;
        }

        // check whether the user is a guest or a registered account or staff
        if (guestAcc instanceof RegisteredAccount) {
            
            //Cast the guestAcc to RegisteredAccount type
            RegisteredAccount userAccount = ((RegisteredAccount)guestAcc);

            //Auto cancel the reservation if the reservation is not confirmed for 14 days
            Reservation.autoCancelReservation();
            // TODO : Junwei -> Payment
            // TODO : Huiyi -> Check Request Status
            // TODO : Huiyi -> Cancel Ticket
            // TODO : Huiyi -> Reschedule Ticket
            // Update Profile (Done)
            int selection;
            do {
                clearConsole();

                selection = displayRegisteredAccountMenu(s);

                while(selection < 1 || selection > 8){
                    System.out.println("Invalid input");
                    selection = displayRegisteredAccountMenu(s);
                }

                switch (selection) {
                    case 1:
                        // Update Profile (Done)
                        userAccount.updateProfile(s);
                        break;
                    case 2:
                        userAccount.displayReservation();
                        break;
                    case 3:
                        userAccount.makeReservation(s);
                        break;
                    case 4:
                        // TODO : KangSheng -> Confirm Ticket
                        userAccount.confirmTicket(s);
                        // TODO : Junwei -> Payment ( done )
                        break;
                    case 5:
                        // TODO : Huiyi -> Reschedule Ticket

                        //Modified , get reservations from the user who login , not all 
                        clearConsole();
                        List<Reservation> reservation = userAccount.getReservations();
                        List<Reservation> currentReservation = new ArrayList<Reservation>();

                        boolean reservationFound = false;
                        for (Reservation reservationCheck : reservation) {
                            if(reservationCheck.getReservationStatus() != rStatus.CANCELLED)
                                reservationFound = true;
                        }

                        if(reservation.isEmpty()){
                            System.out.println("\nYou have no reservation.");
                            System.out.println("ENTER ANY KEY TO CONTINUE >");
                            s.nextLine();
                            s.nextLine();
                        }
                        else if(reservationFound == true){
                            int choiceReschedule;
                            int reservationCount;
                            do{
                                System.out.println("\n\n-----RESCHEDULE TICKET-----");

                                reservationCount = 1;
                                System.out.println("\nYour Reservations:");
                                for (Reservation res : reservation) {
                                    if(res.getReservationStatus() != rStatus.CANCELLED) {
                                        System.out.println(reservationCount + ". ");
                                        System.out.println(res.displayReservation(res));
                                        currentReservation.add(res);
                                        reservationCount++;
                                    }
                                }
                                System.out.printf("Select Reservation to Reshedule[1...%d] > ",reservationCount - 1);
                                choiceReschedule = s.nextInt();

                                if(choiceReschedule < 1 || choiceReschedule > reservationCount - 1){
                                    System.out.println("\nInvalid Choice, Please Enter Again!");
                                }

                            } while(choiceReschedule < 1 || choiceReschedule > reservationCount - 1);

                            userAccount.rescheduleTicket(currentReservation.get(choiceReschedule - 1), s, userAccount);
                        }
                        else{
                            System.out.println("\nYour reservation have been all cancelled.");
                            System.out.println("ENTER ANY KEY TO CONTINUE >");
                            s.nextLine();
                            s.nextLine();
                        }

                        break;
                    case 6:
                        // TODO : Huiyi -> Cancel Ticket
                        clearConsole();
                        List<Reservation> resToCancel = userAccount.getReservations();
                        List<Reservation> currentReservations = new ArrayList<Reservation>();

                        boolean reservationsFound = false;
                        for (Reservation reservationCheck : resToCancel) {
                            if(reservationCheck.getReservationStatus() != rStatus.CANCELLED)
                                reservationsFound = true;
                        }

                        if(resToCancel.isEmpty()){
                            System.out.println("\nYou have no reservation.");
                            System.out.println("ENTER ANY KEY TO CONTINUE >");
                            s.nextLine();
                            s.nextLine();
                        }
                        else if(reservationsFound == true) {
                            int choiceCancel;
                            int reservationsCount;
                            do{
                                System.out.println("\n\n-----CANCEL TICKET-----");

                                reservationsCount = 1;
                                System.out.println("\nYour Reservations:");
                                for (Reservation res : resToCancel) {
                                     if(res.getReservationStatus() != rStatus.CANCELLED) {
                                        System.out.println(reservationsCount + ". ");
                                        System.out.println(res.displayReservation(res));
                                        currentReservations.add(res);
                                        reservationsCount++;
                                    }
                                }

                                System.out.printf("Select Reservation to Cancel [1...%d]> ",reservationsCount - 1);
                                choiceCancel = s.nextInt();

                                if(choiceCancel < 1 || choiceCancel > reservationsCount - 1){
                                    System.out.println("\nInvalid Choice, Please Enter Again!");
                                }

                            } while(choiceCancel < 1 || choiceCancel > reservationsCount - 1);
                            
                            userAccount.cancelTicket(currentReservations.get(choiceCancel - 1), s, userAccount);
                        }
                        else{
                            System.out.println("\nYour reservation have been all cancelled.");
                            System.out.println("ENTER ANY KEY TO CONTINUE >");
                            s.nextLine();
                            s.nextLine();
                        }
                        break;
                    case 7:
                        // TODO : Huiyi -> Check Request Status
                        clearConsole();
                        List<Request> requestToCheck = userAccount.getRequests();
                        
                        if(requestToCheck.isEmpty()){
                            System.out.println("\nYou have no request.");
                            System.out.println("ENTER ANY KEY TO CONTINUE >");
                            s.nextLine();
                            s.nextLine();
                        }
                        else {
                            int requestCheck;
                            int requestCount;
                            do{
                                System.out.println("\n\n-----CHECK REQUEST STATUS-----");

                                requestCount = 1;
                                System.out.println("\nYour Requests:");
                                for (Request res : requestToCheck) {
                                    System.out.println(requestCount + ". ");
                                    System.out.println(res.toString());
                                    requestCount++;
                                }
                                System.out.printf("Select Request to Check Request Status [1...%d]> ",requestCount - 1);
                                requestCheck = s.nextInt();

                                if(requestCheck < 1 || requestCheck > requestCount - 1){
                                    System.out.println("\nInvalid Choice, Please Enter Again!");
                                }

                        }while(requestCheck < 1 || requestCheck > requestCount - 1);

                            userAccount.checkRequestStatus(requestToCheck.get(requestCheck - 1), s);
                    }
                        break;
                    default:
                        System.out.println("Invalid Selection.");
                        break;
                }
            } while (selection != 8);
        } else if (guestAcc instanceof Staff) {

            Staff stfAccount = ((Staff)guestAcc);
            int selection;
            do {
                // Staff Menu
                selection = displayStaffMenu(s);

                while(selection < 1 || selection > 10){
                    System.out.println("Invalid input");
                    selection = displayStaffMenu(s);
                }

                switch (selection) {
                    case 1:
                        stfAccount.addRecordsMenu(s);
                        break;
                    case 2: 
                        stfAccount.updateRecordsMenu(s);
                        break;
                    case 3: 
                        stfAccount.deleteRecordsMenu(s);
                        break;
                    case 4:
                        if(stfAccount.getStaffID().equals("S001"))
                            stfAccount.createStaffAcc(s);
                        else
                            System.out.println("\nThis Staff ID does not have the access to create a new staff account.");
                        break;
                    case 5:
                        stfAccount.checkRequest(s);
                        break;
                    case 6:
                        stfAccount.changePassword(s);
                        break;
                    case 7:
                        stfAccount.report(s);
                        break;
                    case 8:
                        userList(s);
                        break;
                    case 9:
                        dailySummaryReport();
                        break;
                    default:
                        System.out.println("Invalid Input. Please enter again... ");
                        break;
                }
            } while (selection!=10);

        }
    }while(!end);
    }

    /**
     * Method to Display Menu
     * @param s
     * @return an int choice
     */

    private static int displayMenu(Scanner s) {
        clearConsole();
        System.out.println("Menu");
        System.out.println("1. Display Available Flights");
        System.out.println("2. Register an account");
        System.out.println("3. Login");
        System.out.println("4. Staff Login");
        System.out.println("5. Exit Program");
        return s.nextInt();
    }

    private static int displayStaffMenu(Scanner s){
        System.out.println("\n+=========================+");
        System.out.println("| \tStaff Menu        |");
        System.out.println("+=========================+");
        System.out.println("| 1. Add Records          |");
        System.out.println("| 2. Update Records       |");
        System.out.println("| 3. Delete Records       |");
        System.out.println("| 4. Create staff account |");
        System.out.println("| 5. Check Request List   |");
        System.out.println("| 6. Change Password      |");
        System.out.println("| 7. History Report       |");
        System.out.println("| 8. User List            |");
        System.out.println("| 9. Daily Summary Report |");
        System.out.println("|10. Logout               |");
        System.out.println("+-------------------------+");
        System.out.print("Enter Selection: ");
        return s.nextInt();
    }

    private static int displayRegisteredAccountMenu(Scanner s){
        System.out.println("\n\nREGISTERED ACCOUNT MENU");
        System.out.println("1. Update Profile");
        System.out.println("2. Display Reservations");
        System.out.println("3. Make Reservation");
        System.out.println("4. Confirm Ticket");
        System.out.println("5. Reschedule Ticket");
        System.out.println("6. Cancel Ticket");
        System.out.println("7. Check Request Status");
        System.out.println("8. Logout");
        System.out.print("Enter Selection > ");
        return s.nextInt();
    }

    /** 
     * Method to Insert Data
    */

    public static void insertDataToList() {
        // Create 6 airports
        airportList.add(new Airport("Changi Airport", "Singapore"));
        airportList.add(new Airport("Kuala Lumpur International Airport", "Malaysia"));
        airportList.add(new Airport("Beijing Capital International Airport", "China"));
        airportList.add(new Airport("Tokyo International Airport", "Japan"));
        airportList.add(new Airport("Taiwan Taoyuan International Airport", "Taiwan"));
        airportList.add(new Airport("Kota Kinabalu International Airport", "Malaysia"));

        // Create Airlines
        airlineList.add(new Airline("Singapore Airlines", "S012"));
        airlineList.add(new Airline("All Nippon Airways", "N029"));
        airlineList.add(new Airline("Thai Airways", "T067"));
        airlineList.add(new Airline("Japan Airlines", "N087"));
        airlineList.add(new Airline("Airasia", "A092"));
        airlineList.add(new Airline("Malaysia Airlines", "M073"));
        // Create 10 flights
        flightList.add(new Flight(airlineList.get(0), 100));
        flightList.add(new Flight(airlineList.get(1), 150));
        flightList.add(new Flight(airlineList.get(2), 100));
        flightList.add(new Flight(airlineList.get(3), 150));
        flightList.add(new Flight(airlineList.get(4), 100));
        flightList.add(new Flight(airlineList.get(5), 150));
        flightList.add(new Flight(airlineList.get(0), 100));

        //Linking the airport with the flights
        airportList.get(0).addFlight(flightList.get(0));
        airportList.get(1).addFlight(flightList.get(1));
        airportList.get(0).addFlight(flightList.get(4));
        airportList.get(3).addFlight(flightList.get(3));
        airportList.get(2).addFlight(flightList.get(2));
        airportList.get(1).addFlight(flightList.get(5));
        airportList.get(4).addFlight(flightList.get(6));

        // Create FlightSchedules
        // SG -> MY
        flightScheduleList.add(new FlightSchedule(LocalTime.of(10, 0, 0), LocalDate.of(2021, 10, 25),
                airportList.get(0), airportList.get(1), LocalDateTime.of(2021, 10, 25, 14, 0, 0), flightList.get(0)));
        // MY -> SG
        flightScheduleList.add(new FlightSchedule(LocalTime.of(18, 0, 0), LocalDate.of(2021, 10, 25),
                airportList.get(1), airportList.get(0), LocalDateTime.of(2021, 10, 25, 22, 0, 0), flightList.get(0)));
        // MY -> JP
        flightScheduleList.add(new FlightSchedule(LocalTime.of(05, 0, 0), LocalDate.of(2021, 9, 22), airportList.get(1),
                airportList.get(3), LocalDateTime.of(2021, 9, 22, 12, 0), flightList.get(3)));
        // JP -> CN
        flightScheduleList.add(new FlightSchedule(LocalTime.of(14, 0, 0), LocalDate.of(2021, 9, 23), airportList.get(3),
                airportList.get(2), LocalDateTime.of(2021, 9, 22, 12, 0), flightList.get(1)));
        // MY -> EAST MY
        flightScheduleList.add(new FlightSchedule(LocalTime.of(15, 0, 0), LocalDate.of(2021, 8, 23), airportList.get(1),
                airportList.get(5), LocalDateTime.of(2021, 8, 23, 18, 0), flightList.get(5)));

        // Add a staff account
        Address address1 = new Address("No 6", "Jalan SP123", 52100, "Kepong", "Selangor", "Malaysia");
        staffAccountList.add(new Staff("abc1234", "Nicole", "Lai", address1, 'F', 13, "nlai@gmail.com", "+60186538749"));
        Address address2 = new Address("No 11", "Jalan 3432", 40150, "Petaling Jaya", "Selangor", "Malaysia");
        staffAccountList.add(new Staff("1234abc", "John", "Lee", address2, 'M', 20, "johnl@gmail.com", "+60134889342"));

        // Add a customer account 
        Address sampleAddress = new Address("No 1", "Jalan Satu",40400, "Shah Alam", "Selangor", "Malaysia");
        accountList.add(new RegisteredAccount("abc1234", "Victor", "Wong", sampleAddress, 'M', 19, "wong@gmail.com", "+6012345678"));
    }

    public static List<Request> getRequests() {
        return requests;
    }

    public static List<FlightSchedule> getFlightSchedules() {
        return flightScheduleList;
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }

    public static List<Flight> getFlightList() {
        return flightList;
    }

    public static List<Airport> getAirportList() {
        return airportList;
    }

    public static List<Airline> getAirlineList() {
        return airlineList;
    }

    public static List<Staff> getStaffAccountList(){
        return staffAccountList;
    }

    /**
     * Method to create an registered account
     * @return a new Registered Account instance
     */
    public static RegisteredAccount registerAccount(Scanner s) {

        Account acc = new Account();
        String regPassword;
        Boolean passwordMatch = true;

        System.out.println("Fill in the details below to sign up: ");
        System.out.print("1. First Name > "); // ENTER FIRST NAME
        s.nextLine();
        String regFirstName = s.nextLine();
 
        while (!acc.validateName(regFirstName)) // VALIDATE IF IT ONLY CONTAIN CHARACTERS
        {
            System.out.println("Invalid Name. Only Alphabets ");
            System.out.print("1. First Name > ");
            regFirstName = s.nextLine();
        }

        System.out.print("2. Last Name > "); // ENTER LAST NAME
   
        String regLastName = s.nextLine();
        while (!acc.validateName(regLastName)) {
            System.out.println("Invalid Name. Only Alphabets ");
            System.out.print("2. Last Name > ");
            regLastName = s.nextLine();
        }

        System.out.print("3. Gender (M/F) > "); // ENTER GENDER
        char regGender = s.next().charAt(0);
        while (!acc.validateGender(regGender)) // VALIDATE ONLY EITHER M OR F
        {
            System.out.println("Male or Female only");
            System.out.print("3. Gender (M/F) > ");
            regGender = s.next().charAt(0);
        }

        System.out.print("4. Age > "); // ENTER AGE
        int regAge = s.nextInt();

        s.nextLine();
        System.out.print("5. Phone Number  > "); // ENTER PHONE NUMBER
        String regPhoneNum = s.nextLine();
        while (!acc.validatePhoneNum(regPhoneNum)) // VALIDATE ONLY NUMBER WITH +COUNTRY CODE
        {
            System.out.println("Phone Number Should Start With '+' followed by country code and phone number");
            System.out.println("Eg : +60123456789");
            System.out.print("5. Phone Number  > ");
            regPhoneNum = s.nextLine();
        }

        System.out.print("6. Email > "); // ENTER EMAIL
        String regEmail = s.nextLine();
        while (!acc.validateEmailFormat(regEmail)) // VALIDATE EMAIL IF IT CONTAIN "@"
        {
            System.out.println("Invalid Email. Try Again ");
            System.out.print("6. Email > ");
            regEmail = s.nextLine();
        }
        System.out.println("7. Address > "); // ENTER ADDRESS
        System.out.print("7.1 Unit > ");
        String regAddUnit = s.nextLine();
        System.out.print("7.2 Road > ");
        String regAddRoad = s.nextLine();
        System.out.print("7.3 PostCode > ");
        int regAddPostcode = s.nextInt();
        s.nextLine();
        System.out.print("7.4 City > ");
        String regAddCity = s.nextLine();
        System.out.print("7.5 State > ");
        String regAddState = s.nextLine();
        System.out.print("7.6 Country > ");
        String regAddCountry = s.nextLine();

        do {
            System.out.println("\n\n\n");
            System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
            System.out.print("8. Password > "); // ENTER PASSWORD
            regPassword = s.nextLine();

            while (!acc.validatePassword(regPassword)) // VALIDATE PASSWORD REQUIREMENT
            {
                System.out.println("Invalid Password. ");
                System.out.println("\t*NOTE*\n- At Least 7 Characters\n- At Least 1 Letter\n- At Least 1 Number\n");
                System.out.print("8. Password > ");
                regPassword = s.nextLine();
            }

            System.out.print("8.1 Confirm Password > "); // INPUT CONFIRM PASSWORD
            String confirmPassword = s.nextLine();
            if (!regPassword.equals(confirmPassword)) // COMPARE BOTH PASSWORD
            {
                passwordMatch = false;
                System.out.println("Password Does Not Match! Re-Enter Again! ");
            }

        } while (passwordMatch == false);

        // CREATE OBJECT
        Address regAddress = new Address(regAddUnit, regAddRoad, regAddPostcode, regAddCity, regAddState,
                regAddCountry);
        RegisteredAccount register = new RegisteredAccount(regPassword, regFirstName, regLastName, regAddress,
                regGender, regAge, regEmail, regPhoneNum);
        accountList.add(register); // ADD TO ACCOUNTLIST
        register.welcome(); // can remove if dont want line 210
        s.nextLine();
        s.nextLine();
        return register;
    }

    public static Account promptUserChoice(Scanner s,Account guestAcc)
    {
        char yesNo;
        boolean valid = true;

        System.out.print("\nWish to make a reservation(Y/N)? ");
        yesNo = s.next().charAt(0);

        // validate yesNo
        if (!validateOption(yesNo)) {
            valid = false;
        }
        while(valid == false){
            System.out.println("Invalid Input. Please enter again...");
            System.out.print("\nWish to make a reservation(Y/N)? ");
            yesNo = s.next().charAt(0);

            if (validateOption(yesNo)) {
                valid = true;
            }
        }

        if (Character.toUpperCase(yesNo) == 'Y') {
            System.out.print("Register or Login (R/L)? ");
            char registerOrLogin = s.next().charAt(0);

            //validate registerOrLogin
            while(Character.toUpperCase(registerOrLogin)!='R' && Character.toUpperCase(registerOrLogin)!='L'){
                System.out.println("\nInvalid Input. Please enter again...\n");
                System.out.print("Register or Login (R/L)? ");
                registerOrLogin = s.next().charAt(0);

                // Register account
                if (Character.toUpperCase(registerOrLogin) == 'R') {
                    guestAcc = registerAccount(s);
                }

                // Login account
                if (Character.toUpperCase(registerOrLogin) == 'L') {
                    guestAcc = userLogin(s);
                }
            }

            // Register account
            if (Character.toUpperCase(registerOrLogin) == 'R') {
                guestAcc = registerAccount(s);
            }

            // Login account
            if (Character.toUpperCase(registerOrLogin) == 'L') {
                guestAcc = userLogin(s);
            }

        }else{
            guestAcc = null;
        }

        return guestAcc;
    }

    /**
     * Method to perform User Login
     * @param s
     * @return Exisiting RegisteredAccount Instance when found
     */
    public static RegisteredAccount userLogin(Scanner s){
        System.out.println("\nUser Login");
        System.out.println("===========");
        System.out.print("User ID: ");
        String userID = s.next();
        System.out.print("Password: ");
        String userPwd = s.next();
        

        for ( RegisteredAccount user : accountList){
            if (user.getUserID().equals(userID)){
                if(user.getPassword().equals(userPwd)){
                    System.out.println("Login Successful.");
                    System.out.printf("Welcome %s %s\n",user.getFirstName(),user.getLastName());
                    System.out.printf("User ID: %s\n",user.getUserID());
                    s.nextLine();
                    s.nextLine();
                    return user;
                }
            }
        }   
            // account doesnt exist, return null
            System.out.println("Wrong ID or Password.");
            s.nextLine();
            s.nextLine();
            return null;
    }

    /**
     * Method to Do Staff Login
     * @param staffScanner
     * @return Existing Staff Instance when Success
     * @return null when no such Staff is found
     */
    public static Staff staffLogin(Scanner s) {
        System.out.println("\nStaff Login");
        System.out.println("===========");
        System.out.print("Staff ID: ");
        String staffID = s.next();
        System.out.print("Password: ");
        String staffPwd = s.next();

        // Iterate the staff list from the main program
        for (Staff stf : staffAccountList) {
            // perform checking
            if (stf.getStaffID().equals(staffID)){
                if (stf.getPassword().equals(staffPwd)) {
                    System.out.println("Welcome " + stf.getFirstName() + " " + stf.getLastName() + ".");
                    return stf;
                }
            }
        }
        
        System.out.println("Wrong ID or Password.");
        s.nextLine();
        s.nextLine();
        return null;
    }
    /**  
     * Method to clear console output
     */
    public final static void clearConsole(){

        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * Method to display a line
     * @param n
     */
    public static void printLine(int n){
        for(int i = 0; i < n; i++){
        System.out.print("-");
        }
        System.out.println();
    }

    
    /**
     * Method to validate Options
     * @param option
     * @return
     */

    public static boolean validateOption(char option){ 
        return (Character.toUpperCase(option)=='Y' || Character.toUpperCase(option)=='N');
    
    }

    public static void userList(Scanner s){
        clearConsole();
        System.out.println("\t SaiLou Airline Registered User List");
        System.out.println("+=======+=====================+=========+===========================+================+");
        System.out.println("|UserID |Name                 |Gender   |Email                      |Phone Number    |");
        System.out.println("+=======+=====================+=========+===========================+================+");
        
        
        for (RegisteredAccount list : accountList) {
            System.out.printf("|%-7s|%-21s|%c        |%-27s|%-16s|\n",list.getUserID(),(list.getFirstName()+" "+list.getLastName()),list.getGender(),list.getEmail(),list.getPhoneNum());
            System.out.println("+=======+=====================+=========+===========================+================+");
        }

        s.nextLine();
        s.nextLine();
    }

    public static void dailySummaryReport(){

        List<Reservation> rList = Main.getReservations();
        if(rList.isEmpty())
            System.out.println("No Reservations Today");
        double profit = 0.0;
        clearConsole();
        System.out.println("\t SaiLou Airline Daily Summary Report");
        System.out.println("+===============================================+");
        System.out.printf("|%-4s|%-20s|Total Amount | Status |\n","ID","Booked Seats");
        for (Reservation reservation : rList) {
            if(reservation.getReservationStatus() == rStatus.PAID){
            System.out.printf("|%-4s|%-20s|RM%11.2f|%-8s|\n",reservation.getReservationNo(),reservation.getBookedSeaString(reservation),reservation.getTotalAmount(),reservation.getReservationStatus().name());
            profit += reservation.getTotalAmount();
            }
        }
        System.out.println("+===============================================+");

        System.out.printf("Total Profit for the day : RM%.2f\n",profit);

    }

}
