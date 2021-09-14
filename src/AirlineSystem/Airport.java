package AirlineSystem;
import java.util.List;
import java.util.ArrayList;

class Airport {

    private String airportName;
    private String location;
    private List<Flight> flightList = new ArrayList<Flight>();

    //Parameterized Constructor
    public Airport(String airportName,String location){
        this.airportName = airportName;
        this.location = location;
    }
    //No-args constructor
    public Airport(){
        this("","");
    }

    //getter and setter
    public String getAirportName() {
        return airportName;
    }
    public String getLocation() {
        return location;
    }
    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    //toString
    @Override
    public String toString() {
        // String str = String.format("AirportName : %s \n Location : %s \n FlightList : ", airportName,location);
        String str = String.format("%-40s %-20s ", airportName, location);
        for (Flight flight : flightList) {
            str += flight.getFlightCode();
        }
        return str;
    }
    
    //Method of the class
    /**
     * Add Flights to the Airport
     * @param flight
     */
    public void addFlight(Flight flight){
        flightList.add(flight);
        System.out.println("Flight added successfully!");
    }
    /**
     * Remove Specific Flight from the Airport
     * @param flightCode
     */
    public void removeFlight(String flightCode){
        int i = 0;
        for (Flight flight : flightList) {
            if(flight.getFlightCode() == flightCode){
                flightList.remove(i);
            }
            i++;
        }
    }


}