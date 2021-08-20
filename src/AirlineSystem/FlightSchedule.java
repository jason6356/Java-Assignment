package AirlineSystem;

import java.util.List;
import java.util.ArrayList;

public class FlightSchedule {
    private String departureTime;
    private String flightDate;
    private String location;
    private String destination;
    private String estimatedArrivalTime;
    private Flight flight;
    List<FlightSchedule> flightSeat = new ArrayList<FlightSchedule>();

    // getter
    public String getDepartureTime() {
        return departureTime;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getLocation() {
        return location;
    }

    public String getDestination() {
        return destination;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<FlightSchedule> getFlightSeat() {
        return flightSeat;
    }

    // setter
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return "\nDeparture Time: " + departureTime + "\nFlight Date: " + flightDate + "\nLocation: " + location
                + "\nDestination: " + destination + "\nEstimated Arrival Time: " + estimatedArrivalTime + "\nFlight: "
                + flight.getFlightCode();
    }
}
