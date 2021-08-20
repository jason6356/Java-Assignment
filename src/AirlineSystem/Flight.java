package AirlineSystem;
import java.util.List;
import java.util.ArrayList;

class Flight {

    String flightCode;
    Airline airline;
    int totalSeat;
    List<Seat> seatList = new ArrayList<Seat>();

    Flight(){

    }

    Flight(String flightCode,Airline airline, int totalSeat){

        this.flightCode = flightCode;
        this.airline = airline;
        this.totalSeat = totalSeat;
    }

    public String getFlightCode(){
        return flightCode;
    }

    



}