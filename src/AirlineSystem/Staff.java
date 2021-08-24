package AirlineSystem;

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
        
    }

    public void acceptRequest(){

    }

    public void rejectRequest(){
        
    }
}
