package AirlineSystem;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    //predefined data to store
    private static Staff staffAccount = new Staff();
    private static List<RegisteredAccount> accountList = new ArrayList<RegisteredAccount>();
    private static List<FlightSchedule> flightScheduleList = new ArrayList<FlightSchedule>();
    private static List<Airport> airportList = new ArrayList<Airport>();
    private static List<Airline> airlineList = new ArrayList<Airline>();
    private static List<Flight> flightList = new ArrayList<Flight>();
    private static List<Request> requests = new ArrayList<Request>();
    private static List<Reservation> reservations = new ArrayList<Reservation>();
    public static void main(String[] args){        
        Account guestAcc = new Account();
        
        Scanner s = new Scanner(System.in);
        boolean leave = true;
        do{
            int choice = displayMenu(s);
            leave = true;
            switch(choice){
                case 1:
                    guestAcc.searchAvailableFlights();
                    break;
                case 2:
                    //call the register account here
                    //implement the logic as well
                    guestAcc = new RegisteredAccount();
                    registerAccount();
                    break;
                case 3:
                    //call the login function here, included with validation and loop
                    guestAcc = new RegisteredAccount();
                    break;
                case 4:
                    //staff login
                    guestAcc = new Staff();
                    staffLogin();
                    break;
                default:
                    System.out.println("Invalid choice!!!");
                    leave = false;
                break;
            }
        }while(!leave);

        //check whether the user is a guest or a registered account or staff
        if(guestAcc instanceof RegisteredAccount){
            //all the registered account is here
            System.out.println("This is a registered account instance");
        }
        else if(guestAcc instanceof Staff){
            //staff module here
            System.out.println("This is a staff account instance");
        }
        else{
            //guest account stuff here
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
            return;    
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


    public static void registerAccount(){

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
    }

    public static void staffLogin(){
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

        for(int i=0; i<staffAcc.length; i++){
            for(int j=0; j<staffAcc[i].length; i++){
                if((staffID == staffAcc[i][j]) || staffPwd == staffAcc[i][j])
                    valid = true; 
                else 
                    valid = false; 
            }
        }

        if(valid == true){
            System.out.println("Login Successful.");

            int selection;
            do{
                //Staff Menu 
                System.out.println("Staff Menu");
                System.out.println("==========");
                System.out.println("1. Modify Records");
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
                        staffAccount.checkRequest();
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
