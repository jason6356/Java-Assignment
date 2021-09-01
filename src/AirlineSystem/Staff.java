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
    
    //getter 
    public String getStaffID() {
        return staffID;
    }

    //method 
    public boolean addFilght(Flight flight){
        Scanner add = new Scanner(System.in);
        List<Flight> flightList = Main.getFlightList();
        

        return false;
    }

    public boolean addAirport(Airport airport){
        List<Airport> airportList = Main.getAirportList();

        return false;
    }

    public boolean addAirline(Airline airline){
        List<Airline> airlineList = Main.getAirlineList();

        return false;
    }

    public void checkRequest(){
        Scanner s = new Scanner(System.in);
        char continueToModify;
        boolean valid = true;
        do{
            //Get RequestList
            List<Request> requestList = Main.getRequests();

            //Display all list
            for (Request request : requestList) {
                System.out.println(request.toString());
            }

            //input requestID 
            System.out.print("Enter Request ID: ");
            String requestID = s.nextLine();

            //go to specific index of the list
            Request requestToModify = null;
            for (Request request : requestList) {
                if(request.getRequestID() == requestID)
                    requestToModify = request;
            }

            //Display details of the request 
            //TODO : Check validaity of this Nicole!, if not found need to display error msgs
            System.out.println(requestToModify);

            //approve or reject
            System.out.print("Approve (Y/y) or Reject (N/n): ");
            char choice = s.next().charAt(0);

            //validate choice 
            if(!super.validateOption(choice)){
                System.out.println("Invalid input.");
                valid = false;
            }
            
            //if else for accept or reject request 
            if(Character.toUpperCase(choice) == 'Y'){
                // acceptRequest(requestToModify);
            }
            else if (Character.toUpperCase(choice) == 'N'){
                rejectRequest(requestToModify);
            }
            else //return back to menu
                return;
            
            //continue to modify request? 
            System.out.print("Continue to modify request? (Y/N) ");
            continueToModify = s.next().charAt(0);

            //validate continueToModify
            if(!super.validateOption(continueToModify)){
                System.out.println("Invalid input.");
                valid = false;
            }
        }while((Character.toUpperCase(continueToModify) == 'Y') || valid == false); 

        s.close();
    }

    // public void acceptRequest(Request request){
    //     //Get RequestList
    //     List<Request> requestList = Main.getRequests();

    //     //change request status 
    //     request.setRequestStatus(rqStatus.APPROVED);

    //     //call hui yi methodss
    //      //reschedule);
    // }

    public void rejectRequest(Request request){
        //change request status 
        request.setRequestStatus(rqStatus.REJECTED);
    }
}
