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
    public void addFilght(){
        Scanner add = new Scanner(System.in);
        List<Flight> flightList = Main.getFlightList();
        
        //Input details 
        System.out.println("Add Flight");
        System.out.println("==========");
        System.out.print("Enter Airline Name: ");
        String airlineName = add.nextLine();
        System.out.print("Enter Airline Code: ");
        String airlineCode = add.next();
        System.out.print("Enter Total Seats: ");
        int totalSeat = add.nextInt();
        
        //Create object 
        Airline airline = new Airline(airlineName, airlineCode);
        Flight flight = new Flight(airline, totalSeat);
        flightList.add(flight);

        //close scanner 
        add.close();
    }

    public void addAirport(){
        Scanner add = new Scanner(System.in);
        List<Airport> airportList = Main.getAirportList();

        //Input details 
        System.out.println("Add Airport");
        System.out.println("===========");
        System.out.print("Enter Airport Name: ");
        String airportName = add.nextLine();
        System.out.print("Enter Location: ");
        String location = add.nextLine();

        //Create object 
        Airport airport = new Airport(airportName, location);
        airportList.add(airport);

        //close scanner 
        add.close();
    }

    public void addAirline(){
        Scanner add = new Scanner(System.in);
        List<Airline> airlineList = Main.getAirlineList();

        //Input details 
        System.out.println("Add Airline");
        System.out.println("===========");
        System.out.print("Enter Airline Name: ");
        String airlineName = add.nextLine();
        System.out.print("Enter Airline Code: ");
        String airlineCode = add.next();

        //Create object 
        Airline airline = new Airline(airlineName, airlineCode);
        airlineList.add(airline);

        //close scanner 
        add.close();
    }

    //update or delete flights 
    public void updateDeleteFlights(int choice){
        Scanner update = new Scanner(System.in);
        List<Flight> flightList = Main.getFlightList();

        //Display flight details 
        for (Flight flight : flightList) {
            System.out.println(flight.toString());
        }

        if(choice == 1){
            System.out.println("Update Flight");
        }else{
            System.out.println("Delete Flight");
        }
        //Enter Flight code 
        System.out.println("-------------");
        System.out.print("Flight Code: ");
        String flightCode = update.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<flightList.size(); i++){
            if(flightList.get(i).getFlightCode()== flightCode)
                updateIndex = i;
        }

        if(choice == 1){
            //Input Details 
            System.out.println("Update Flight Details");
            System.out.println("=====================");
            System.out.print("Enter Airline Name: ");
            String airlineName = update.nextLine();
            System.out.print("Enter Airline Code: ");
            String airlineCode = update.next();
            System.out.print("Enter Total Seats: ");
            int totalSeat = update.nextInt();

            //Create object 
            Airline airline = new Airline(airlineName, airlineCode);
            Flight flight = new Flight(airline, totalSeat);
            flightList.set(updateIndex, flight);
        }else{
            flightList.remove(updateIndex);
        }

        update.close();
    }
    //update or delete airline
    public void updateDeleteAirline(int choice){
        Scanner update = new Scanner(System.in);
        List<Airline> airlineList = Main.getAirlineList();

        //Display airline details 
        for (Airline airline : airlineList) {
            System.out.println(airline.toString());
        }

        if(choice == 1){
            System.out.println("Update Airline");
        }else{
            System.out.println("Delete Airline");
        }

        //Input airline code to update 
        System.out.println("--------------");
        System.out.print("Airline Code: ");
        String updateAirlineCode = update.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<airlineList.size(); i++){
            if(airlineList.get(i).getAirlineCode() == updateAirlineCode)
                updateIndex = i;
            i++;
        }

        if(choice == 1){
            //Input details 
            System.out.println("Update Airline");
            System.out.println("==============");
            System.out.print("Enter Airline Name: ");
            String airlineName = update.nextLine();
            System.out.print("Enter Airline Code: ");
            String airlineCode = update.next();

            //Create object 
            Airline airline = new Airline(airlineName, airlineCode);
            airlineList.set(updateIndex, airline); 
        }else{
            airlineList.remove(updateIndex); 
        }
        
        update.close();
    }
    //update or delete airport 
    public void updateDeleteAirport(int choice){
        Scanner update = new Scanner(System.in);
        List<Airport> airportList = Main.getAirportList();
        
        //Display details 
        for (Airport airport : airportList) {
            System.out.println(airport.toString());
        }

        if(choice == 1){
            System.out.println("Update Airport");
        }else{
            System.out.println("Delete Airport");
        }
        //Input airport name to update 
        System.out.println("--------------");
        System.out.print("Airport Name: ");
        String updateAirportName = update.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<airportList.size(); i++){
            if(airportList.get(i).getAirportName() == updateAirportName)
                updateIndex = i;
            i++;
        }

        if(choice == 1){
            //Input details 
            System.out.println("Update Airport");
            System.out.println("==============");
            System.out.print("Airport Name: ");
            String airportName = update.nextLine();
            System.out.print("Location: ");
            String location = update.nextLine();

            //Create object 
            Airport airport = new Airport(airportName, location);
            airportList.set(updateIndex, airport);
        }else{
            airportList.remove(updateIndex);
        }
        
        update.close();
    }
    //update or delete flightschedule
    public void updateDeleteFlightSchedule(int choice){
        Scanner update = new Scanner(System.in);
        List<FlightSchedule> flightScheduleList = Main.getFlightSchedules();

        //Display Details 
        for (FlightSchedule flightSchedule : flightScheduleList) {
            System.out.println(flightSchedule.toString());
        }

        if(choice == 1){
            System.out.println("Update Flight Schedule");
        }else{
            System.out.println("Delete Flight Schedule");
        }
        //Input 
        //dk wat to enter for update 

        //get index 
        // int updateIndex = 0;
        // for(int i=0; i<flightScheduleList.size(); i++){
        //     if()
        //         updateIndex = i;
        //     i++;
        // }

        if(choice == 1){
            //Input Details 
            System.out.println("Update Flight Schedule");
            System.out.println("======================");
            System.out.print("Departure Time: ");
            String departureTime = update.nextLine();
            System.out.print("Flight Date: ");
            String flightDate = update.nextLine();
            System.out.print("Location - Airport Name: ");
            String airportName = update.nextLine();
            System.out.print("         - Location: ");
            String locationAirport = update.nextLine();
            System.out.print("Destination - Airport Name: ");
            String destinationAirport = update.nextLine();
            System.out.print("            - Location: ");
            String destinationLocation = update.nextLine();
            System.out.print("Estimated Arrival Time: ");
            String estimatedArrivalTime = update.nextLine();
            System.out.print("Airline - Name: ");
            String airlineName = update.nextLine();
            System.out.print("        - Code: ");
            String airlineCode = update.nextLine();
            System.out.print("Total Seat: ");
            int totalSeat = update.nextInt();

            //Create object 
            Airport location = new Airport(airportName, locationAirport);
            Airport destination = new Airport(destinationAirport, destinationLocation);
            Airline airline = new Airline(airlineName, airlineCode);
            Flight flight = new Flight(airline, totalSeat);
            //FlightSchedule flightSchedule = new FlightSchedule(departureTime, flightDate, location, destination, estimatedArrivalTime, flight);
        }

        update.close();
    }
    //update or delete seats 

    //delete seats 

    //create staff if staffID == S001 

    public void checkRequest(){
        Scanner check = new Scanner(System.in);
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
            String requestID = check.nextLine();

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
            char choice = check.next().charAt(0);

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
            continueToModify = check.next().charAt(0);

            //validate continueToModify
            if(!super.validateOption(continueToModify)){
                System.out.println("Invalid input.");
                valid = false;
            }
        }while((Character.toUpperCase(continueToModify) == 'Y') || valid == false); 

        check.close();
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

    //change password 
}
