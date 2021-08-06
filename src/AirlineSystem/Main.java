package AirlineSystem;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //list of registered account
        List<Account> accountList = new ArrayList<Account>();

        //pre-generated guest account

        Account guestAcc = new Account();
        
        Scanner s = new Scanner(System.in);
        int choice = displayMenu(s);

        if(choice == 1)
            guestAcc.searchAvailableFlights();
        else if(choice == 2){}
            //get user input for registration //validate the input
            //down cast the guestAcc to RegisteredAccount
            //append the account to the registered account list
        
            //iterate the registered account list 
            // if user id == user id, then check password,
            //if password not equal then display error in password
            //else login change the guestAcc object reference to the object reference found in the list
            // 


    }

    private static int displayMenu(Scanner keyboard){
        System.out.println("Menu");
        System.out.println("1. Search Available Flights");
        System.out.println("2. Register an account");
        System.out.println("3. Login");

        return keyboard.nextInt();

    }
}
