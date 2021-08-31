package AirlineSystem;
import java.util.List;
import java.time.*;
import java.util.ArrayList;

public class FlightSchedule {
    private LocalTime departureTime;
    private LocalDate flightDate;
    private Airport location;
    private Airport destination;
    private LocalDateTime estimatedArrivalTime;
    private Flight flight;
    List<fSeat> flightSeat = new ArrayList<fSeat>();

    //Parameterized Constructor
    public FlightSchedule(LocalTime departureTime, LocalDate flightDate,Airport location,Airport destination, LocalDateTime estimatedArrivalTime, Flight flight){
        this.departureTime = departureTime;
        this.flightDate = flightDate;
        this.location = location;
        this.destination = destination;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.flight = flight;
        
        flightSeat = makefSeatList(flight.getTotalSeat());
    }

    //TODO: Refactor this toString method
    //ToString
    public String toString() {
        return String.format("DepartureTime : ");
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public Airport getDestination() {
        return destination;
    }
    public Flight getFlight() {
        return flight;
    }
    public LocalDateTime getFlightDate() {
        return flightDate;
    }
    public List<fSeat> getFlightSeat() {
        return flightSeat;
    }
    public Airport getLocation() {
        return location;
    }
    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    //Method
    /**
     * Create a list of flight seats to the flight schedule
     * @param totalSeats
     * @return List of fSeat
     */
    private static List<fSeat> makefSeatList(int totalSeats){
        if(totalSeats == 0)
            return null;
        fSeat.setTotalSeat(totalSeats);
        
        List<fSeat> fSeatList = new ArrayList<fSeat>();
        for(int i = 0; i < totalSeats; i++)
            fSeatList.add(new fSeat());

        return fSeatList;
    }
}
