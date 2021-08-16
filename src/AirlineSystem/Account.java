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
  
    public Account(){}
    public Account(String password, String firstName, String lastName, 
    Address address, char gender, int age, String email, String phoneNum)
    {
      
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.age =age;
        this.email = email;
        this.phoneNum = phoneNum;

    }

    ///////////////////////////////////////////getter///////////////////////////////////////
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
 
    ///////////////////////////////////Setter/////////////////////////////////////////////
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




    ///////////////////////////////////methods////////////////////////////////////
    
    public boolean validateEmailFormat(String email)
    {
        return email.matches("^(.+)@(.+)$");
    }


    public boolean Checking(String password)
    {
        return password.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}"); 
    } 

    
    

    public void searchAvailableFlights(){
        System.out.println("Searching available flights?");
    }
}
