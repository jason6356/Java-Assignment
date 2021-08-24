package AirlineSystem;
import java.util.List;
import java.util.Scanner;

public class Staff extends Account{
    private String staffID;
    private static int nthStaff = 0; // default value is 0

    //constructor 
    public Staff(){
        this("", "", "", null, '\0', 0, "", "");
    }

    public Staff(String password, String firstName, String lastName, Address address, char gender, int age, String email, 
                 String phoneNum){
        super(password, firstName, lastName, address, gender, age, email, phoneNum);
        this.staffID = makeStaffID();
        //Increment Once a staff has been created
        nthStaff++;
    }
    /**
    Method to create StaffID in sequence
    @return StaffID in String
     */
    private static String makeStaffID(){
        if(nthStaff < 10)
            return "S00" + nthStaff;
        else if(nthStaff < 100)
            return "S0" + nthStaff;

        return "S" + nthStaff;
    }
    
    public String getStaffID() {
        return staffID;
    }

    //method 
    public boolean addFilght(Flight flight){
        //stub method
        return false;
    }

    public boolean addAirport(Airport airport){
        return false;
    }

    public boolean addAirline(Airline airline){
        return false;
    }

    public void checkRequest(){
        Scanner s = new Scanner(System.in);
        //Get RequestList
        List<Request> requestList = Main.getRequests();

        //Display all list
        for (Request request : requestList) {
            System.out.println(request.toString());
        }

        //Ask for user input
        int input = s.nextInt();

        //go to specific index of the list
        Request requestToModify = requestList.get(input - 1);

        //Display details of the request
        //print it out

        //ask for approve or reject

        //scanner.next() <-- input string String <-- "Yes" 
        char choice = s.next().charAt(0);
        s.close();
        if(choice == 'Y'){
            acceptRequest(requestToModify);
        }
        else if (choice == 'N'){
            rejectRequest(requestToModify);
        }
        else //return back to menu
            return;
    }

    public void acceptRequest(Request request){
        // make it approve
        request.setRequestStatus(rqStatus.APPROVED);

        //call hui yi methods

        //if request is cancel then cancel
        //if request is reschedule then reschedule
    }

    public void rejectRequest(Request request){
        //make the request status to rejected
        // do nothing
    }
}
