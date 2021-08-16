package AirlineSystem;

public class Address {
    private String unit;
    private String road;
    private int postcode;
    private String city;
    private String state;
    private String country;

    public Address(){}

    public Address(String unit, String road, int postcode, String city, String state, String country){
        this.unit = unit;
        this.road = road;
        this.postcode = postcode;
        this.city = city; 
        this.state = state;
        this.country = country;
    }

    ///////////////////////////////////////////getter/////////////////////////////////////
    public String getUnit() {
        return unit;
    }
    public String getRoad() {
        return road;
    }
    public int getPostcode() {
        return postcode;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getCountry() {
        return country;
    }
    ///////////////////////////////////setter///////////////////////////////////////
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void setRoad(String road) {
        this.road = road;
    }
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return String.format("Unit    : %s\nRoad    : %s\nPostcode : %d\nCity    : %s\nState   : %s\nCountry : %s\n", unit,road,postcode,city,state,country);
    }
}
