package AirlineSystem;

public class Staff extends Account{
    private String staffID;

    //constructor 
    public Staff(){}

    public Staff(String password, String firstName, String lastName, Address address, char gender, int age, String email, 
                 String phoneNum, String staffID){
        super(password, firstName, lastName, address, gender, age, email, phoneNum);
        this.staffID = staffID;
    }

    //method 
    public boolean addFilght(Flight flight){

    }

    public boolean addAirport(Airport airport){

    }

    public boolean addAirline(Airline airline){

    }

    public void checkRequest(){

    }

    public void acceptRequest(){

    }

    public void rejectRequest(){
        
    }
}
