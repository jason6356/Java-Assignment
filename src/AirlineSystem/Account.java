package AirlineSystem;

import java.util.List;

public class Account {

    private String password;
    private String firstName;
    private String lastName;
    private Address address;
    private char gender;
    private int age;
    private String email;
    private String phoneNum;

    //No-args Constructor
    public Account() {
        this("","","",null,'\0',0,"","");
    }
    //Parameterized Constructor
    public Account(String password, String firstName, String lastName, Address address, char gender, int age,
            String email, String phoneNum) {

        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phoneNum = phoneNum;

    }

    //Getter
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    //Setter
    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    //Methods
    
    public boolean validateEmailFormat(String email) {
        return email.matches("^(.+)@(.+)$"); // anything but must have "@"
    }

    public boolean validatePassword(String password) {
        return password.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}");
    }//Minimum 7 characters, at least one letter and one number


    /**
     * Method to display Available Flights
     */
    public void availableFlights() {
        System.out.println("\n                                                               ---Flight Schedule---");
        System.out.println("=============================================================================================================================================================");
        System.out.printf("%-5s|%11s|%5s|%-37s|%-37s|%13s|%-24s|\n", "Code", "Flight Date", "Departure Time", "Location", "Destination", "Estimated Arrival Time", "Location -> Destination");
        System.out.println("=============================================================================================================================================================");
        
        List<FlightSchedule> flightScheduleList = Main.getFlightSchedules(); 
        //display all
        for (FlightSchedule flightSchedule : flightScheduleList) {
            System.out.println(flightSchedule.displayFlightSchedule());
        }
    }

    /**
     * Method to validate Name
     * @param name
     * @return
     */

    public boolean validateName(String name){
        return name.matches("[A-Za-z]+"); // only alphabet
    }

    /**
     * Method to validate Gender
     * @param gender
     * @return
     */

    public boolean validateGender(char gender){
        return (Character.toUpperCase(gender)=='F' || Character.toUpperCase(gender)=='M');
    }

    /**
     * Method to validate Phone Number
     * @param phoneNumber
     * @return
     */

    public boolean validatePhoneNum(String phoneNumber)
    {
        return phoneNumber.matches("(\\+[1-9]{1})[0-9]{9,14}"); //+X123456 ; Xis country code(can more than 1 digit)
    }
}
