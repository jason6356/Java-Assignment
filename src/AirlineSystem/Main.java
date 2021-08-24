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

    public static void registerAccount(){

        Scanner regScan = new Scanner(System.in);
        Account acc = new Account();
        String regPassword;
        Boolean passwordMatch = true; 

        System.out.println("Fill in the details below to sign up: ");
        System.out.print("1. First Name > ");
        String regFirstName = regScan.nextLine();
        System.out.print("2. Last Name > ");
        String regLastName = regScan.nextLine();
        System.out.print("3. Gender (F/M) > ");
        char regGender = regScan.next().charAt(0);
        System.out.print("4. Age > ");
        int regAge = regScan.nextInt();
        System.out.print("5. Phone Number  > ");
        String regPhoneNum = regScan.nextLine();
        System.out.print("6. Email > ");
        String regEmail = regScan.nextLine();
        while (acc.validateEmailFormat(regEmail)==false)
        {
            System.out.println("Invalid Email. Try Again ");
            System.out.print("6. Email > ");
            regEmail = regScan.nextLine();
        }
        System.out.println("7. Address > ");
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
        System.out.print("8. Password > ");
        regPassword = regScan.nextLine();
        
        while (acc.Checking(regPassword)==false)
        {
            System.out.println("Invalid Password. ");
            System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
            System.out.print("8. Password > ");
            regPassword = regScan.nextLine();
        }

        System.out.print("8.1 Confirm Password > ");
        String confirmPassword = regScan.nextLine();
        if (regPassword.equals(confirmPassword) == false)
        {
            passwordMatch = false;
            System.out.println("Password Does Not Match! Re-Enter Again! ");
        }

    }while (passwordMatch == false );
    

        Address regAddress = new Address(regAddUnit, regAddRoad, regAddPostcode, regAddCity, regAddState, regAddCountry);
        RegisteredAccount register = new RegisteredAccount((1000+RegisteredAccount.getRegAccCount()), regPassword, regFirstName,regLastName, regAddress, regGender, regAge, regEmail,regPhoneNum);
        regScan.close();
        accountList.add(register);
        register.welcome(); // can remove if dont want  line 210
    }
}
