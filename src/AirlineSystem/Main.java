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
        boolean leave = true;
        do {
            int choice = displayMenu(s);
            leave = true;
            switch (choice) {
                case 1:
                    // TODO: Nicole -> Search Available Flights Feature
                    guestAcc.availableFlights();
                    break;
                case 2:
                    // Register Account
                    guestAcc = registerAccount();
                    break;
                case 3:
                    // TODO: JunWei -> User Login
                    guestAcc = new RegisteredAccount();
                    break;
                case 4:
                    // TODO: Nicole -> Staff Login
                    guestAcc = staffLogin();
                    break;
                default:
                    System.out.println("Invalid choice!!!");
                    leave = false;
                    break;
            }
        } while (!leave);

        // check whether the user is a guest or a registered account or staff
        if (guestAcc instanceof RegisteredAccount) {
            System.out.println("This is a registered account instance\n");

            // TODO : KangSheng -> Make Reservation
            // TODO : KangSheng -> Confirm Ticket
            // TODO : Junwei -> Payment
            // TODO : Huiyi -> Check Request Status
            // TODO : Huiyi -> Cancel Ticket
            // TODO : Huiyi -> Reschedule Ticket
            // Update Profile (Done)

            int selection;
            do {
                clearConsole();
                System.out.println("\n\nREGISTERED ACCOUNT MENU");
                System.out.println("1. Update Profile");
                System.out.println("2. Make Reservation");
                System.out.println("3. Confirm Ticket");
                System.out.println("4. Reschedule Ticket");
                System.out.println("5. Cancel Ticket");
                System.out.println("6. Check Request Status");
                System.out.print("Enter Selection > ");
                selection = s.nextInt();

                switch (selection) {
                    case 1:
                        // Update Profile (Done)
                        break;
                    case 2:
                        // TODO : KangSheng -> Make Reservation
                        ((RegisteredAccount)guestAcc).makeReservation(s);
                        break;
                    case 3:
                        // TODO : KangSheng -> Confirm Ticket
                        // TODO : Junwei -> Payment
                        break;
                    case 4:
                        // TODO : Huiyi -> Reschedule Ticket
                        List<Reservation> reservation = Main.getReservations();

                        System.out.println("\n\n-----RESCHEDULE TICKET-----");

                        int n = 1;
                        System.out.println("Your Reservations:");
                        for (Reservation res : reservation) {
                            System.out.print(n + ". ");
                            System.out.println(res.toString());
                            System.out.println("\n");
                            n++;
                        }
                        System.out.print("Select Reservation to Reshedule > ");
                        int choiceReschedule = s.nextInt();

                        ((RegisteredAccount) guestAcc).rescheduleTicket(reservation.get(choiceReschedule - 1),s);
                        break;
                    case 5:
                        // TODO : Huiyi -> Cancel Ticket
                        List<Reservation> resToCancel = Main.getReservations();

                        System.out.println("\n\n-----CANCEL TICKET-----");

                        int o = 1;
                        System.out.println("Your Reservations:");
                        for (Reservation res : resToCancel) {
                            System.out.print(o + ". ");
                            System.out.println(res.toString());
                            o++;
                        }
                        System.out.print("Select Reservation to Cancel > ");
                        int choiceCancel = s.nextInt();

                        ((RegisteredAccount) guestAcc).cancelTicket(resToCancel.get(choiceCancel - 1),s);
                        break;
                    case 6:
                        // TODO : Huiyi -> Check Request Status
                        List<Reservation> requestReservation = Main.getReservations();

                        System.out.println("\n\n-----CHECK REQUEST STATUS-----");

                        int r = 1;
                        System.out.println("Your Reservations:");
                        for (Reservation res : requestReservation) {
                            System.out.print(r + ". ");
                            System.out.println(res.toString());
                            r++;
                        }
                        System.out.print("Select Reservation to Check Request Status > ");
                        int requestCheck = s.nextInt();

                        ((RegisteredAccount) guestAcc).checkRequestStatus(requestReservation.get(requestCheck - 1));
                        break;
                    default:
                        System.out.println("Invalid Selection.");
                        break;
                }

            } while (selection > 1 && selection < 7);
        } else if (guestAcc instanceof Staff) {
            // TODO : Nicole -> add flight,airport,airline (done)
            // TODO : Nicole -> Check(done), accept, reject requests
            // TODO : Generate Report (Summary of profit, Ranking of most frequent flights
            // made)
            System.out.println("This is a staff account instance");
        } else {
            // TODO : Nicole -> Prompt for either the user want to book a reservation

            // if yes -> ask to register or login
            // if register -> call register account -> assign the output to guest account
            // else if login - > call login account -> assign the login to guestAcc
            // else validation input error
            // else do nothing
            char yesNo;
            boolean valid = true;
            do {
                System.out.print("Wish to make a reservation(Y/N)? ");
                yesNo = s.next().charAt(0);

                if (Character.toUpperCase(yesNo) == 'Y') {
                    System.out.println("Register or Login (R/L)? ");
                    char registerOrLogin = s.next().charAt(0);
                    // Register account
                    if (Character.toUpperCase(registerOrLogin) == 'R') {
                        guestAcc = registerAccount();
                    }

                    // Login account
                    if (Character.toUpperCase(registerOrLogin) == 'L') {
                        // guestAcc = customerLogin();
                    }

                    // validate registerOrLogin
                    if (!guestAcc.validateOption(registerOrLogin)) {
                        System.out.println("Invalid Input. Please enter again...");
                        valid = false;
                    }

                }

                // validate yesNo
                if (!guestAcc.validateOption(yesNo)) {
                    System.out.println("Invalid Input. Please enter again...");
                    valid = false;
                }

            } while (valid == false);

            System.out.println("This is a guest account instance");
        }

    }

    private static int displayMenu(Scanner keyboard) {
        System.out.println("Menu");
        System.out.println("1. Search Available Flights");
        System.out.println("2. Register an account");
        System.out.println("3. Login");
        System.out.println("4. Staff Login");
        return keyboard.nextInt();
    }

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
        staffAccountList.add(new Staff("abc123", "Nicole", "Lai", null, 'f', 13, "abcasdeawdsa", "+08238123123"));
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

    public static RegisteredAccount registerAccount() {

        Scanner regScan = new Scanner(System.in);
        Account acc = new Account();
        String regPassword;
        Boolean passwordMatch = true;

        System.out.println("Fill in the details below to sign up: ");
        System.out.print("1. First Name > "); // ENTER FIRST NAME
        String regFirstName = regScan.nextLine();
        while (!acc.validateName(regFirstName)) // VALIDATE IF IT ONLY CONTAIN CHARACTERS
        {
            System.out.println("Invalid Name. Only Alphabets ");
            System.out.print("1. First Name > ");
            regFirstName = regScan.nextLine();
        }
        System.out.print("2. Last Name > "); // ENTER LAST NAME
        String regLastName = regScan.nextLine();
        while (!acc.validateName(regLastName)) {
            System.out.println("Invalid Name. Only Alphabets ");
            System.out.print("2. Last Name > ");
            regFirstName = regScan.nextLine();
        }

        System.out.print("3. Gender (M/F) > "); // ENTER GENDER
        char regGender = regScan.next().charAt(0);
        while (!acc.validateGender(regGender)) // VALIDATE ONLY EITHER M OR F
        {
            System.out.println("Male or Female only");
            System.out.print("3. Gender (M/F) > ");
            regGender = regScan.next().charAt(0);
        }

        System.out.print("4. Age > "); // ENTER AGE
        int regAge = regScan.nextInt();

        System.out.print("5. Phone Number  > "); // ENTER PHONE NUMBER
        String regPhoneNum = regScan.nextLine();
        while (!acc.validatePhoneNum(regPhoneNum)) // VALIDATE ONLY NUMBER WITH +COUNTRY CODE
        {
            System.out.println("Phone Number Should Start With '+' followed by country code and phone number");
            System.out.println("Eg : +60123456789");
            System.out.print("5. Phone Number  > ");
            regPhoneNum = regScan.nextLine();
        }

        System.out.print("6. Email > "); // ENTER EMAIL
        String regEmail = regScan.nextLine();
        while (!acc.validateEmailFormat(regEmail)) // VALIDATE EMAIL IF IT CONTAIN "@"
        {
            System.out.println("Invalid Email. Try Again ");
            System.out.print("6. Email > ");
            regEmail = regScan.nextLine();
        }
        System.out.println("7. Address > "); // ENTER ADDRESS
        System.out.print("7.1 Unit > ");
        String regAddUnit = regScan.nextLine();
        System.out.print("7.2 Road > ");
        String regAddRoad = regScan.nextLine();
        System.out.print("7.3 PostCode > ");
        int regAddPostcode = regScan.nextInt();
        System.out.print("7.4 City > ");
        String regAddCity = regScan.nextLine();
        System.out.print("7.5 State > ");
        String regAddState = regScan.nextLine();
        System.out.print("7.6 Country > ");
        String regAddCountry = regScan.nextLine();

        do {
            System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
            System.out.print("8. Password > "); // ENTER PASSWORD
            regPassword = regScan.nextLine();

            while (!acc.validatePassword(regPassword)) // VALIDATE PASSWORD REQUIREMENT
            {
                System.out.println("Invalid Password. ");
                System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
                System.out.print("8. Password > ");
                regPassword = regScan.nextLine();
            }

            System.out.print("8.1 Confirm Password > "); // INPUT CONFIRM PASSWORD
            String confirmPassword = regScan.nextLine();
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
        regScan.close();
        accountList.add(register); // ADD TO ACCOUNTLIST
        register.welcome(); // can remove if dont want line 210
        return register;
    }

    public static Staff staffLogin() {
        Boolean valid = true;

        Staff staff = new Staff();

        System.out.println("Staff Login");
        System.out.println("===========");
        System.out.print("Staff ID: ");
        String staffID = staffScanner.nextLine();
        System.out.print("Password: ");
        String staffPwd = staffScanner.nextLine();

        // Iterate the staff list from the main progra
        for (Staff stf : staffAccountList) {
            // perform checking
            if (stf.getStaffID() == staffID)
                if (stf.getPassword() == staffPwd) {
                    valid = true;
                    return stf;
                }

            valid = false;
        }
        
        // if there no staff id and password then display error message and return null
        if(valid == false){
            return null;
        }

        if (valid == true) {
            System.out.println("Login Successful.");

            int selection;
            do {
                // Staff Menu
                System.out.println("Staff Menu");
                System.out.println("==========");
                System.out.println("1. Add Records");
                System.out.println("2. Update Records");
                System.out.println("3. Delete Records");
                System.out.println("4. Create staff account");
                System.out.println("5. Check Request List");
                System.out.println("6. Change Password");
                System.out.println("7. Back to Main Menu");
                System.out.print("Enter Selection: ");
                selection = staffScanner.nextInt();

                int choice;
                switch (selection) {
                    case 1:
                        staff.addRecordsMenu();
                        break;
                    case 2: 
                        staff.updateRecordsMenu();
                        break;
                    case 3: 
                        staff.deleteRecordsMenu();
                        break;
                    case 4:
                        staff.createStaffAcc();
                        break;
                    case 5:
                        staff.checkRequest();
                        break;
                    case 6:
                        staff.changePassword();
                        break;
                    case 7:
                        //back to main menu 
                        break;
                    default:
                        System.out.println("Invalid Input. Please enter again... ");
                        break;
                }
            } while (selection!=1 && selection!=2 && selection!=3 && selection!=4 && selection!=5 && selection!=6 selection!=7);

        } else {
            System.out.println("Login Failed.");
        }
        return null;
    }

    public final static void clearConsole(){

        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void printLine(int n){
        for(int i = 0; i < n; i++){
        System.out.print("-");
        }
    }
}
