package AirlineSystem;
import java.util.Scanner;
import java.util.List;
import java.time.*;
import java.util.ArrayList;public class Main {
    //predefined data to store
    private static List<Staff> staffAccountList = new ArrayList<Staff>();
    private static List<RegisteredAccount> accountList = new ArrayList<RegisteredAccount>();
    private static List<FlightSchedule> flightScheduleList = new ArrayList<FlightSchedule>();
    private static List<Airport> airportList = new ArrayList<Airport>();    
    private static List<Airline> airlineList = new ArrayList<Airline>();
    private static List<Flight> flightList = new ArrayList<Flight>();
    private static List<Request> requests = new ArrayList<Request>();
    private static List<Reservation> reservations = new ArrayList<Reservation>();

    public static void main(String[] args){        
        Account guestAcc = new Account();
        insertDataToList();
        Scanner s = new Scanner(System.in);
        boolean leave = true;
        do{
            int choice = displayMenu(s);
            leave = true;
            switch(choice){
                case 1:
                    //TODO: Nicole -> Search Available Flights Feature
                        //dk y cannot display the details of flights (after search)
                    guestAcc.searchAvailableFlights();
                    break;
                case 2:
                    //Register Account
                    guestAcc = registerAccount();
                    break;
                case 3:
                    //TODO: JunWei -> User Login
                    guestAcc = new RegisteredAccount();
                    break;
                case 4:
                    //TODO: Nicole -> Staff Login
                    guestAcc = staffLogin();
                    break;
                default:
                    System.out.println("Invalid choice!!!");
                    leave = false;
                break;
            }
        }while(!leave);

        //check whether the user is a guest or a registered account or staff
        if(guestAcc instanceof RegisteredAccount){
            //TODO : KangSheng -> Make Reservation
            //TODO : KangSheng -> Confirm Ticket
            //TODO : Junwei -> Payment
            //TODO : Huiyi -> Check Reservation 
            //TODO : Huiyi -> Cancel Ticket
            //TODO : Huiyi -> Reschedule Ticket
            //Update Profile (Done)

            System.out.println("This is a registered account instance");
        }
        else if(guestAcc instanceof Staff){
            //TODO : Nicole -> add flight,airport,airline
            //TODO : Nicole -> Check, accept, reject requests 
            //TODO : Generate Report (Summary of profit, Ranking of most frequent flights made)
            System.out.println("This is a staff account instance");
        }
        else{
            //TODO : Nicole -> Prompt for either the user want to book a reservation
            
            //if yes -> ask to register or login
                // if register -> call register account -> assign the output to guest account
                // else if login - > call login account -> assign the login to guestAcc
                // else validation input error
            //else do nothing
            char yesNo;
            boolean valid = true;
            do{
                System.out.print("Wish to make a reservation(Y/N)? ");
                yesNo = s.next().charAt(0);
                if(Character.toUpperCase(yesNo) == 'Y'){
                    System.out.println("Register or Login (R/L)? ");
                    char registerOrLogin = s.next().charAt(0);
                    if(Character.toUpperCase(registerOrLogin) == 'R'){
                        guestAcc = registerAccount();
                    }

                    if(Character.toUpperCase(registerOrLogin) == 'L'){
                        //guestAcc = customerLogin();
                    }

                    //validate registerOrLogin
                    if(!guestAcc.validateOption(registerOrLogin)){
                        System.out.println("Invalid Input. Please enter again...");
                        valid = false;
                    }

                }

                //validate yesNo
                if(!guestAcc.validateOption(yesNo)){
                    System.out.println("Invalid Input. Please enter again...");
                    valid = false;
                }
                
            }while(valid == false);
            

            System.out.println("This is a guest account instance");
        }

    }

    private static int displayMenu(Scanner keyboard){
        System.out.println("Menu");
        System.out.println("1. Search Available Flights");
        System.out.println("2. Register an account");
        System.out.println("3. Login");
        System.out.println("4. Staff Login");
        return keyboard.nextInt();
    }

    private static void insertDataToList(){
        //Create 6 airports
        airportList.add(new Airport("Changi Airport","Singapore"));
        airportList.add(new Airport("Kuala Lumpur International Airport", "Malaysia"));
        airportList.add(new Airport("Beijing Capital International Airport", "China"));
        airportList.add(new Airport("Tokyo International Airport", "Japan"));
        airportList.add(new Airport("Taiwan Taoyuan International Airport", "Taiwan"));
        airportList.add(new Airport("Kota Kinabalu International Airport", "Malaysia"));

        //Create Airlines
        airlineList.add(new Airline("Singapore Airlines","S012"));
        airlineList.add(new Airline("All Nippon Airways","N029"));
        airlineList.add(new Airline("Thai Airways","T067"));
        airlineList.add(new Airline("Japan Airlines","N087"));
        airlineList.add(new Airline("Airasia", "A092"));
        airlineList.add(new Airline("Malaysia Airlines", "M073"));
        //Create 10 flights
        flightList.add(new Flight(airlineList.get(0),100));
        flightList.add(new Flight(airlineList.get(1),150));
        flightList.add(new Flight(airlineList.get(2),100));
        flightList.add(new Flight(airlineList.get(3),150));
        flightList.add(new Flight(airlineList.get(4),100));
        flightList.add(new Flight(airlineList.get(5),150));
        flightList.add(new Flight(airlineList.get(0),100));

        //Create FlightSchedules
        //SG -> MY
        flightScheduleList.add(new FlightSchedule(LocalTime.of(10,0,0),LocalDate.of(2021,10,25),airportList.get(0),airportList.get(1),LocalDateTime.of(2021,10,25,14,0,0),flightList.get(0)));
        //MY -> SG
        flightScheduleList.add(new FlightSchedule(LocalTime.of(18,0,0),LocalDate.of(2021,10,25),airportList.get(1),airportList.get(0),LocalDateTime.of(2021,10,25,22,0,0),flightList.get(0)));
        //MY -> JP
        flightScheduleList.add(new FlightSchedule(LocalTime.of(05,0,0),LocalDate.of(2021,9,22),airportList.get(1),airportList.get(3),LocalDateTime.of(2021,9,22,12,0),flightList.get(3)));
        //JP -> CN
        flightScheduleList.add(new FlightSchedule(LocalTime.of(14,0,0),LocalDate.of(2021,9,23),airportList.get(3),airportList.get(2),LocalDateTime.of(2021,9,22,12,0),flightList.get(1)));
        //MY -> EAST MY
        flightScheduleList.add(new FlightSchedule(LocalTime.of(15,0,0),LocalDate.of(2021,8,23),airportList.get(1),airportList.get(5),LocalDateTime.of(2021,8,23,18,0),flightList.get(5)));

        //Add a staff account
        staffAccountList.add(new Staff("abc123","Nicole", "Lai",null,'f',13,"abcasdeawdsa","+08238123123"));
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


    public static RegisteredAccount registerAccount(){

        Scanner regScan = new Scanner(System.in);
        Account acc = new Account();
        String regPassword;
        Boolean passwordMatch = true; 

        System.out.println("Fill in the details below to sign up: ");
        System.out.print("1. First Name > ");              //ENTER FIRST NAME
        String regFirstName = regScan.nextLine();           
        while (!acc.validateName(regFirstName))             //VALIDATE IF IT ONLY CONTAIN CHARACTERS
        {
            System.out.println("Invalid Name. Only Alphabets ");
            System.out.print("1. First Name > ");
            regFirstName = regScan.nextLine();
        }
        System.out.print("2. Last Name > ");                //ENTER LAST NAME
        String regLastName = regScan.nextLine();
        while (!acc.validateName(regLastName))
        {
            System.out.println("Invalid Name. Only Alphabets ");
            System.out.print("2. Last Name > ");
            regFirstName = regScan.nextLine();
        }

        System.out.print("3. Gender (M/F) > ");             //ENTER GENDER
        char regGender = regScan.next().charAt(0);
        while(!acc.validateGender(regGender))         //VALIDATE ONLY EITHER M OR F
        {
            System.out.println("Male or Female only");
            System.out.print("3. Gender (M/F) > ");
            regGender = regScan.next().charAt(0);
        }

        System.out.print("4. Age > ");                      //ENTER AGE
        int regAge = regScan.nextInt();

        System.out.print("5. Phone Number  > ");            //ENTER PHONE NUMBER
        String regPhoneNum = regScan.nextLine();
        while(!acc.validatePhoneNum(regPhoneNum))           //VALIDATE ONLY NUMBER WITH +COUNTRY CODE
        {
            System.out.println("Phone Number Should Start With '+' followed by country code and phone number");
            System.out.println("Eg : +60123456789");
            System.out.print("5. Phone Number  > ");
            regPhoneNum = regScan.nextLine();
        }

        System.out.print("6. Email > ");                      // ENTER EMAIL
        String regEmail = regScan.nextLine();
        while (!acc.validateEmailFormat(regEmail))             //VALIDATE EMAIL IF IT CONTAIN "@"
        {
            System.out.println("Invalid Email. Try Again ");
            System.out.print("6. Email > ");
            regEmail = regScan.nextLine();
        }
        System.out.println("7. Address > ");                    //ENTER ADDRESS
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

    do{
        System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
        System.out.print("8. Password > ");                         //ENTER PASSWORD
        regPassword = regScan.nextLine();
        
        while (!acc.validatePassword(regPassword))                  //VALIDATE PASSWORD REQUIREMENT
        {
            System.out.println("Invalid Password. ");
            System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
            System.out.print("8. Password > ");
            regPassword = regScan.nextLine();
        }

        System.out.print("8.1 Confirm Password > ");               //INPUT CONFIRM PASSWORD
        String confirmPassword = regScan.nextLine();                
        if (!regPassword.equals(confirmPassword))                   // COMPARE BOTH PASSWORD
        {
            passwordMatch = false;
            System.out.println("Password Does Not Match! Re-Enter Again! ");
        }

    }while (passwordMatch == false );
    
        //CREATE OBJECT 
        Address regAddress = new Address(regAddUnit, regAddRoad, regAddPostcode, regAddCity, regAddState, regAddCountry);
        RegisteredAccount register = new RegisteredAccount(regPassword, regFirstName,regLastName, regAddress, regGender, regAge, regEmail,regPhoneNum);
        regScan.close();
        accountList.add(register); // ADD TO ACCOUNTLIST
        register.welcome(); // can remove if dont want  line 210
        return register;
    }

    public static Staff staffLogin(){
        Scanner staffScanner = new Scanner(System.in);

        String[][] staffAcc = {
            {"S001", "aBC002"}, 
            {"S002", "pWd001"}
        };
        Boolean valid = true; 

        System.out.println("Staff Login");
        System.out.println("===========");
        System.out.print("Staff ID: ");
        String staffID = staffScanner.nextLine();
        System.out.print("Password: ");
        String staffPwd = staffScanner.nextLine();

        //Iterate the staff list from the main progra
        for (Staff stf : staffAccountList) {
            //perform checking
            if(stf.getStaffID() == staffID)
                if(stf.getPassword() == staffPwd){
                    valid = true;
                    return stf;
                }
            
            valid = false;
        }

        // if there no staff id and password then display error message and return null

        if(valid == true){
            System.out.println("Login Successful.");

            int selection;
            do{
                //Staff Menu 
                System.out.println("Staff Menu");
                System.out.println("==========");
                System.out.println("1. Add Records");
                System.out.println("2. Update Records");
                System.out.println("3. Delete Records");
                System.out.println("2. Create staff account");
                System.out.println("3. Check Request List");
                System.out.println("4. Change Password");
                System.out.print("Enter Selection: ");
                selection = staffScanner.nextInt();

                switch(selection){
                    case 1:
                        int choice;
                        do{
                            //modify records menu 
                            System.out.println("Modify Records");
                            System.out.println("1. Flights");
                            System.out.println("2. Airline");
                            System.out.println("3. Airport");
                            System.out.println("4. Flight Schedule");
                            System.out.println("5. Seats");
                            System.out.print("Enter Choice: ");
                            choice = staffScanner.nextInt();

                            switch(choice){
                                case 1: 
                                    //call modify flights
                                    break;
                                case 2:
                                    //call modify airline 
                                    break;
                                case 3:
                                    //call modify airport
                                    break;
                                case 4:
                                    //call modify flight schedule 
                                    break;
                                case 5:
                                    //call modify seats 
                                    break;
                                default:
                                    System.out.println("Invalid Input.");
                                    break;
                            }
                        }while(choice!=1&&choice!=2&&choice!=3&&choice!=4&&choice!=5);
                        break;
                    case 2:
                        //create staff account if staffid == S001
                        break;
                    case 3:
                        //check request list 
                        
                        break; 
                    case 4:
                        //change password
                        break;
                    default:
                        System.out.println("Invalid Input.");
                        break;
                }
            }while(selection!=1&&selection!=2&&selection!=3);
            
        }else{
            System.out.println("Login Failed.");
        }
            
        staffScanner.close();
    }
}
