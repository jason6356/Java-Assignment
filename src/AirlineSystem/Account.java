package AirlineSystem;

public class Account {
    private String password;
    private String firstName;
    private String lastName;
    private Address address; 
    private char gender;
    private int age;
    private String email;
    private String phoneNum; 

    //constructors
    public Account(){}

    public Account(String password, String firstName, String lastName, Address address, char gender, int age, 
                    String email, String phoneNum){
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.email = email; 
        this.phoneNum = phoneNum;
    }

    
    //getter 
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


    //setter 
    


    //method 
    public boolean validateEmailFormat(){

    }

    public boolean validatePassword(){

    }

    public void searchAvailableFlights(){
        System.out.println("Searching available flights?");
    }
}