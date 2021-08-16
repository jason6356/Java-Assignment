package AirlineSystem;

import java.util.List;
import java.util.ArrayList;

public class Airline {
    private String airlineName;
    private String airlineCode;
    List<Airline> flightList = new ArrayList<Airline>();

    //constructor 
    public Airline(){}

    // public Airline(String airlineName, String airlineCode) 
    //not sure how to do another constructor 

    //getter 
    public String getAirlineName() {
        return airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public List<Airline> getFlightList() {
        return flightList;
    }


    //setter 
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public void setFlightList(List<Airline> flightList) {
        this.flightList = flightList;
    }
}
