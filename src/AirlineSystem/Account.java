package AirlineSystem;

import java.util.Scanner;
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


    public void searchAvailableFlights(Scanner searchFlight) {
        Scanner search = new Scanner(System.in);
        List<Flight> flightList = Main.getFlightList(); 

        //Input Flight Details
        System.out.print("Search flight code: ");
        String flightCode = searchFlight.nextLine();

        //get index 
        int flightIndex = 0;
        for(int i=0; i<flightList.size(); i++){
            if(flightList.get(i).getFlightCode() == flightCode)
                flightIndex = i;
            i++;
        }

        System.out.println(flightList.get(flightIndex));
        search.close();
    }

    public boolean validateName(String name){
        return name.matches("[A-Za-z]+"); // only alphabet
    }

    public boolean validateGender(char gender){
        return (Character.toUpperCase(gender)=='F' || Character.toUpperCase(gender)=='M');
    }

    public boolean validatePhoneNum(String phoneNumber)
    {
        return phoneNumber.matches("(\\+[1-9]{1})[0-9]{9,14}"); //+X123456 ; Xis country code(can more than 1 digit)
    }

    public boolean validateOption(char option){ 
        return (Character.toUpperCase(option)=='Y' || Character.toUpperCase(option)=='N');
    
    }
}
