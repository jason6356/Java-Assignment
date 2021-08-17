package AirlineSystem;
import java.util.List;
import java.util.ArrayList;

class Airport {

    private String airportName;
    private String location;
    private List<Flight> flightList = new ArrayList<Flight>();

    Airport(){

        this.airportName = "";
        this.location = "";
    }

    Airport(String airportName, String location){
        this.airportName = airportName;
        this.location = location;
    }

    



}