package AirlineSystem;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Staff extends Account{
    Scanner staff = new Scanner(System.in);
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
    //add records 
    public void addRecordsMenu(){
        int choice; 
        do{
            System.out.println("Add Records");
            System.out.println("===========");
            System.out.println("1. Flights");
            System.out.println("2. Airline");
            System.out.println("3. Airport");
            System.out.println("4. Back to Staff Menu");
            System.out.print("Enter Choice: ");
            choice = staff.nextInt();

            switch(choice){
                case 1:
                    addFlight();
                    break;
                case 2:
                    addAirline();
                    break;
                case 3:
                    addAirport();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid Input! Please enter again... ");
                    break;
            }
        }while(choice!= 1 && choice!=2 && choice!=3 && choice!=4);
    }

    public void addFlight(){
        List<Flight> flightList = Main.getFlightList();
        
        //Input details 
        System.out.println("Add Flight");
        System.out.println("==========");
        System.out.print("Enter Airline Name: ");
        String airlineName = staff.nextLine();
        System.out.print("Enter Airline Code: ");
        String airlineCode = staff.next();
        System.out.print("Enter Total Seats: ");
        int totalSeat = staff.nextInt();
        
        //Create object 
        Airline airline = new Airline(airlineName, airlineCode);
        Flight flight = new Flight(airline, totalSeat);
        flightList.add(flight);
    }

    public void addAirport(){
        List<Airport> airportList = Main.getAirportList();

        //Input details 
        System.out.println("Add Airport");
        System.out.println("===========");
        System.out.print("Enter Airport Name: ");
        String airportName = staff.nextLine();
        System.out.print("Enter Location: ");
        String location = staff.nextLine();

        //Create object 
        Airport airport = new Airport(airportName, location);
        airportList.add(airport);
    }

    public void addAirline(){
        List<Airline> airlineList = Main.getAirlineList();

        //Input details 
        System.out.println("Add Airline");
        System.out.println("===========");
        System.out.print("Enter Airline Name: ");
        String airlineName = staff.nextLine();
        System.out.print("Enter Airline Code: ");
        String airlineCode = staff.next();

        //Create object 
        Airline airline = new Airline(airlineName, airlineCode);
        airlineList.add(airline);
    }

    //update records 
    public void updateRecordsMenu(){
        int choice;
        do{
            System.out.println("Update Records");
            System.out.println("==============");
            System.out.println("1. Flights");
            System.out.println("2. Airline");
            System.out.println("3. Airport");
            System.out.println("4. Flight Schedule");
            System.out.println("5. Back to Staff Menu");
            System.out.print("Enter Choice: ");
            choice = staff.nextInt();

            switch(choice){
                case 1:
                    updateFlights();
                    break;
                case 2:
                    updateAirline();
                    break;
                case 3:
                    updateAirport();
                    break;
                case 4:
                    updateFlightSchedule();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Input! Please enter again... ");
                    break;
            }
        }while(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5);
    }

    public void updateFlights(){
        List<Flight> flightList = Main.getFlightList();

        //Display flight details 
        for (Flight flight : flightList) {
            System.out.println(flight.toString());
        }

        //Enter Flight code 
        System.out.println("-------------");
        System.out.print("Flight Code: ");
        String flightCode = staff.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<flightList.size(); i++){
            if(flightList.get(i).getFlightCode()== flightCode)
                updateIndex = i;
        }

        System.out.println("Update Flight Details");
        System.out.println("=====================");
        System.out.print("Enter Airline Name: ");
        String airlineName = staff.nextLine();
        System.out.print("Enter Airline Code: ");
        String airlineCode = staff.next();
        System.out.print("Enter Total Seats: ");
        int totalSeat = staff.nextInt();

        //Create object 
        Airline airline = new Airline(airlineName, airlineCode);
        Flight flight = new Flight(airline, totalSeat);
        flightList.set(updateIndex, flight);
    }

    public void updateAirline(){
        List<Airline> airlineList = Main.getAirlineList();

        //Display airline details 
        for (Airline airline : airlineList) {
            System.out.println(airline.toString());
        }

        //Input airline code to update 
        System.out.println("--------------");
        System.out.print("Airline Code: ");
        String updateAirlineCode = staff.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<airlineList.size(); i++){
            if(airlineList.get(i).getAirlineCode() == updateAirlineCode)
                updateIndex = i;
            i++;
        }

        //Input details 
        System.out.println("Update Airline");
        System.out.println("==============");
        System.out.print("Enter Airline Name: ");
        String airlineName = staff.nextLine();
        System.out.print("Enter Airline Code: ");
        String airlineCode = staff.next();

        //Create object 
        Airline airline = new Airline(airlineName, airlineCode);
        airlineList.set(updateIndex, airline); 
    }

    public void updateAirport(){
        List<Airport> airportList = Main.getAirportList();
        
        //Display details 
        for (Airport airport : airportList) {
            System.out.println(airport.toString());
        }

        //Input airport name to update 
        System.out.println("--------------");
        System.out.print("Airport Name: ");
        String updateAirportName = staff.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<airportList.size(); i++){
            if(airportList.get(i).getAirportName() == updateAirportName)
                updateIndex = i;
            i++;
        }

        //Input details 
        System.out.println("Update Airport");
        System.out.println("==============");
        System.out.print("Airport Name: ");
        String airportName = staff.nextLine();
        System.out.print("Location: ");
        String location = staff.nextLine();

        //Create object 
        Airport airport = new Airport(airportName, location);
        airportList.set(updateIndex, airport);
    }

    public void updateFlightSchedule(){
        List<FlightSchedule> flightScheduleList = Main.getFlightSchedules();

        //Display Details 
        for (FlightSchedule flightSchedule : flightScheduleList) {
            System.out.println(flightSchedule.toString());
        }

        //Input 
        System.out.println("--------------");
        System.out.print("Flight Schedule Code: ");
        String flightScheduleCode = staff.nextLine();

        // get index 
        int updateIndex = 0;
        for(int i=0; i<flightScheduleList.size(); i++){
            if(flightScheduleList.get(i).getFlightScheduleCode() == flightScheduleCode)
                updateIndex = i;
            i++;
        }

        //Input Details 
        System.out.println("Update Flight Schedule");
        System.out.println("======================");

        System.out.print("Departure Time (HH.MM): ");
        String timeStr = staff.nextLine();
        LocalTime departureTime = LocalTime.parse(timeStr);

        System.out.print("Flight Date (DD-MM-YYYY): ");
        String dateStr = staff.nextLine();
        LocalDate flightDate = LocalDate.parse(dateStr);

        System.out.print("Location - Airport Name: ");
        String airportName = staff.nextLine();

        System.out.print("         - Location: ");
        String locationAirport = staff.nextLine();
        System.out.print("Destination - Airport Name: ");
        String destinationAirport = staff.nextLine();
        System.out.print("            - Location: ");
        String destinationLocation = staff.nextLine();

        System.out.print("Estimated Arrival Time: ");
        String eArrivalTimeStr = staff.nextLine();
        LocalDateTime estimatedArrivalTime = LocalDateTime.parse(eArrivalTimeStr);

        System.out.print("Airline - Name: ");
        String airlineName = staff.nextLine();
        System.out.print("        - Code: ");
        String airlineCode = staff.nextLine();

        System.out.print("Total Seat: ");
        int totalSeat = staff.nextInt();

        //Create object 
        Airport location = new Airport(airportName, locationAirport);
        Airport destination = new Airport(destinationAirport, destinationLocation);
        Airline airline = new Airline(airlineName, airlineCode);
        Flight flight = new Flight(airline, totalSeat);
        FlightSchedule flightSchedule = new FlightSchedule(departureTime, flightDate, location, destination, estimatedArrivalTime, flight);
        flightScheduleList.set(updateIndex, flightSchedule);
    }

    //delete records 
    public void deleteRecordsMenu(){
        int choice;
        do{
            System.out.println("Delete Records");
            System.out.println("==============");
            System.out.println("1. Flights");
            System.out.println("2. Airline");
            System.out.println("3. Airport");
            System.out.println("4. Flight Schedule");
            System.out.println("5. Back to Staff Menu");
            System.out.print("Enter Choice: ");
            choice = staff.nextInt();

            switch(choice){
                case 1:
                    deleteFlights();
                    break;
                case 2:
                    deleteAirline();
                    break;
                case 3:
                    deleteAirport();
                    break;
                case 4: 
                    deleteFlightSchedule();
                    break;
                case 5: 
                    return;
                default:
                    System.out.println("Invalid Input! Please enter again... ");
                    break;
            }
        }while(choice!=1 && choice !=2 && choice!=3 && choice!=4 && choice!=5);

    }

    public void deleteFlights(){
        List<Flight> flightList = Main.getFlightList();

        //Display flight details 
        for (Flight flight : flightList) {
            System.out.println(flight.toString());
        }

        //Enter Flight code 
        System.out.println("-------------");
        System.out.print("Flight Code: ");
        String flightCode = staff.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<flightList.size(); i++){
            if(flightList.get(i).getFlightCode()== flightCode)
                updateIndex = i;
        }

        flightList.remove(updateIndex);
    }

    public void deleteAirline(){
        List<Airline> airlineList = Main.getAirlineList();

        //Display airline details 
        for (Airline airline : airlineList) {
            System.out.println(airline.toString());
        }

        //Input airline code to update 
        System.out.println("--------------");
        System.out.print("Airline Code: ");
        String updateAirlineCode = staff.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<airlineList.size(); i++){
            if(airlineList.get(i).getAirlineCode() == updateAirlineCode)
                updateIndex = i;
            i++;
        }

        airlineList.remove(updateIndex); 
    }

    public void deleteAirport(){
        List<Airport> airportList = Main.getAirportList();
        
        //Display details 
        for (Airport airport : airportList) {
            System.out.println(airport.toString());
        }

        //Input airport name to update 
        System.out.println("--------------");
        System.out.print("Airport Name: ");
        String updateAirportName = staff.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<airportList.size(); i++){
            if(airportList.get(i).getAirportName() == updateAirportName)
                updateIndex = i;
            i++;
        }

        airportList.remove(updateIndex);
    }

    public void deleteFlightSchedule(){
        List<FlightSchedule> flightScheduleList = Main.getFlightSchedules();

        //Display Details 
        for (FlightSchedule flightSchedule : flightScheduleList) {
            System.out.println(flightSchedule.toString());
        }

        //Input 
        System.out.println("--------------");
        System.out.print("Flight Schedule Code: ");
        String flightScheduleCode = staff.nextLine();

        // get index 
        int updateIndex = 0;
        for(int i=0; i<flightScheduleList.size(); i++){
            if(flightScheduleList.get(i).getFlightScheduleCode() == flightScheduleCode)
                updateIndex = i;
            i++;
        }

        flightScheduleList.remove(updateIndex);
    }
    
    public void createStaffAcc(){
        List<Staff> staffList = Main.getStaffAccountList();

        Account acc = new Account();
        String regPassword;
        Boolean passwordMatch = true;

        //Input Details 
        System.out.println("Create Staff");
        System.out.println("============");

        //First Name
        System.out.print("First Name: "); 
        String regFirstName = staff.nextLine();
        while (!acc.validateName(regFirstName)) {
            System.out.println("Invalid Name. Must contian alphabets only. ");
            System.out.print("First Name: ");
            regFirstName = staff.nextLine();
        }

        //Last Name
        System.out.print("Last Name: "); 
        String regLastName = staff.nextLine();
        while (!acc.validateName(regLastName)) {
            System.out.println("Invalid Name. Must contian alphabets only. ");
            System.out.print("Last Name: "); 
            regLastName = staff.nextLine();
        }

        //Gender
        System.out.print("Gender (M/F): "); 
        char regGender = staff.next().charAt(0);
        //validate gender (M or F only)
        while (!acc.validateGender(regGender)) {
            System.out.println("Male or Female only");
            System.out.print("Gender (M/F): "); 
            regGender = staff.next().charAt(0);
        }

        //Age
        System.out.print("Age: "); 
        int regAge = staff.nextInt();
        //validate age (only contains number)
        while (regAge < 0 || regAge > 100) {
            System.out.println("Male or Female only");
            System.out.print("Age: "); 
            regAge = staff.nextInt();
        }

        //PhoneNum
        System.out.print("Phone Number: "); 
        String regPhoneNum = staff.nextLine();
        while (!acc.validatePhoneNum(regPhoneNum)) {
            System.out.println("Phone Number Should Start With '+' followed by country code and phone number");
            System.out.println("Eg : +60123456789");
            System.out.print("Phone Number: "); 
            regPhoneNum = staff.nextLine();
        }

        //Email 
        System.out.print("Email: "); 
        String regEmail = staff.nextLine();
        while (!acc.validateEmailFormat(regEmail)) {
            System.out.println("Invalid Email. Try Again ");
            System.out.print("Email: ");
            regEmail = staff.nextLine();
        }

        //Address 
        System.out.println("Address - Unit: "); 
        String regAddUnit = staff.nextLine();
        System.out.print("          - Road: ");
        String regAddRoad = staff.nextLine();
        System.out.print("          - PostCode: ");
        int regAddPostcode = staff.nextInt();
        System.out.print("          - City: ");
        String regAddCity = staff.nextLine();
        System.out.print("          - State: ");
        String regAddState = staff.nextLine();
        System.out.print("          - Country: ");
        String regAddCountry = staff.nextLine();

        //Password 
        do {
            System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
            System.out.print("Password: "); 
            regPassword = staff.nextLine();

            while (!acc.validatePassword(regPassword)) {
                System.out.println("Invalid Password. ");
                System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
                System.out.print("Password: "); 
                regPassword = staff.nextLine();
            }

            System.out.print("Confirm Password: "); 
            String confirmPassword = staff.nextLine();
            if (!regPassword.equals(confirmPassword)) {
                passwordMatch = false;
                System.out.println("Password Does Not Match! Re-Enter Again! ");
            }

        } while (passwordMatch == false);

        //Create object 
        Address regAddress = new Address(regAddUnit, regAddRoad, regAddPostcode, regAddCity, regAddState, regAddCountry);
        Staff createStaff = new Staff(regPassword, regFirstName, regLastName, regAddress, regGender, regAge, regEmail, regPhoneNum);
        staffList.add(createStaff);
    }

    public void checkRequest(){
        char continueToModify;
        boolean valid = true;
        do{
            //Get RequestList
            List<Request> requestList = Main.getRequests();

            //Display all list
            for (Request request : requestList) {
                System.out.println(request.toString());
            }

            Request requestToModify;
            do{
                //input requestID 
                System.out.print("Enter Request ID: ");
                String requestID = staff.nextLine();

                //go to specific index of the list
                requestToModify = null;
                for (Request request : requestList) {
                    if(request.getRequestID() == requestID)
                        requestToModify = request;
                }

                //Display details of the request 
                //TODO : Check validaity of this Nicole!, if not found need to display error msgs
                if(requestToModify == null){
                    System.out.println("Request ID not found. Please try it again... ");
                }
                System.out.println(requestToModify);
            }while(requestToModify == null);
            

            //approve or reject
            System.out.print("Approve (Y/y) or Reject (N/n): ");
            char choice = staff.next().charAt(0);

            //validate choice 
            if(!super.validateOption(choice)){
                System.out.println("Invalid input.");
                valid = false;
            }
            
            //if else for accept or reject request 
            if(Character.toUpperCase(choice) == 'Y'){
                acceptRequest(requestToModify);
            }
            else if (Character.toUpperCase(choice) == 'N'){
                rejectRequest(requestToModify);
            }
            else //return back to menu
                return;
            
            //continue to modify request? 
            System.out.print("Continue to modify request? (Y/N) ");
            continueToModify = staff.next().charAt(0);

            //validate continueToModify
            if(!super.validateOption(continueToModify)){
                System.out.println("Invalid input.");
                valid = false;
            }
        }while((Character.toUpperCase(continueToModify) == 'Y') || valid == false); 
    }

    public void acceptRequest(Request request){
        //change request status 
        request.setRequestStatus(rqStatus.APPROVED);

        //call updateRequest methods
        request.updateRequest(request);
    }

    public void rejectRequest(Request request){
        //change request status 
        request.setRequestStatus(rqStatus.REJECTED);
    }

    public void changePassword(){
        List<Staff> staffList = Main.getStaffAccountList();
        Account acc = new Account();

        //Enter Staff ID 
        System.out.print("Staff ID: ");
        String staffId = staff.nextLine();

        //get index 
        int updateIndex = 0;
        for(int i=0; i<staffList.size(); i++){
            if(staffList.get(i).getStaffID()== staffId)
                updateIndex = i;
        }

        //Update password 
        String updPassword;
        boolean passwordMatch = true;
        do {
            System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
            System.out.print("Password: "); 
            updPassword = staff.nextLine();

            while (!acc.validatePassword(updPassword)) {
                System.out.println("Invalid Password. ");
                System.out.println("\t*NOTE*\n1.At Least 7 Characters\n2.At Least 1 Letter\n3.At Least 1 Number\n");
                System.out.print("Password: "); 
                updPassword = staff.nextLine();
            }

            System.out.print("Confirm Password: "); 
            String confirmPassword = staff.nextLine();
            if (!updPassword.equals(confirmPassword)) {
                passwordMatch = false;
                System.out.println("Password Does Not Match! Re-Enter Again! ");
            }

            if(updPassword == staffList.get(updateIndex).getPassword()){
                passwordMatch = false;
                System.out.println("Update password cannot be the same as previous password! Please Re-Enter again.");
            }

        } while (passwordMatch == false);
    }

}
