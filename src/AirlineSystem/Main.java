package AirlineSystem;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    //predefined data to store
    Staff staffAccount = new Staff();
    List<RegisteredAccount> accountList = new ArrayList<RegisteredAccount>();
    List<FlightSchedule> flightScheduleList = new ArrayList<FlightSchedule>();
    List<Airport> airportList = new ArrayList<Airport>();
    List<Airline> airlineList = new ArrayList<Airline>();
    List<Flight> flightList = new ArrayList<Flight>();
    List<Request> requests = new ArrayList<Request>();
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
}
