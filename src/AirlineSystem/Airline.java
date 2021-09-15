package AirlineSystem;
import java.util.List;
import java.util.ArrayList;

public class Airline {
    private String airlineName;
    private String airlineCode;
    private List<Flight> flightList = new ArrayList<Flight>();

    //parameter constructor
    public Airline(String airlineName, String airlineCode){
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
    }

    //no args constructor
    public Airline(){
        this("","");
    };
    //getter 
    public String getAirlineName() {
        return airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }
    //setter 
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    //toString
    /**
     * @return AirlineName, AirlineCode, List of FlightCode in String format
     */
    @Override
    public String toString() {
        String str = String.format("%-20s %-20s", airlineName, airlineCode);
        for (Flight flight : flightList) {
            str += flight.getFlightCode() + " ";
        }
        str+= "\n";
        return str;
    }

    public String displayAirlineForReport(){
        String str = String.format("%s|%s|", airlineName, airlineCode);
        for (Flight flight : flightList) {
            str += flight.getFlightCode() + " ";
        }
        str+= "\n";
        return str;
    }
    
    //Method of the class
    /**
     * Add Flights to the Airline
     * @param flight
     */
    public void addFlight(Flight flight){
        flightList.add(flight);
        System.out.println("Flight added successfully!");
    }
    /**
     * Remove Specific Flight from the Airline
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
