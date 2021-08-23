package AirlineSystem;
import java.util.List;
import java.util.ArrayList;

class Flight {

    String flightCode;
    Airline airline;
    int totalSeat;
    List<Seat> seatList = new ArrayList<Seat>();

    //Parameterized Constructor
    Flight(String flightCode,Airline airline, int totalSeat){

        this.flightCode = flightCode;
        this.airline = airline;
        this.totalSeat = totalSeat;
        seatList = createSeatList(totalSeat);
    }
    //No-args Constructor
    Flight(){
        this("",null,0);
    }

    public String getFlightCode(){
        return flightCode;
    }

    //ToString
    @Override
    public String toString() {
        return String.format("FlightCode : %s \nTotalSeat : %d\n",flightCode,totalSeat) + airline;
    }

    //Methods
    /**
     * Method to create a list of seats to the flight
     * @param totalSeat
     * @return List of seats according to the totalSeats
     */
    private List<Seat> createSeatList(int totalSeat){
        //If there is no seat, then give null
        if(totalSeat == 0)
            return null;
    
        List<Seat> seatList = new ArrayList<Seat>();
        //Set the totalSeat in the Seat Class
        //There are some comments before the Seat Class
        Seat.setTotalSeat(totalSeat);

        for(int i = 0; i < totalSeat; i++){
            seatList.add(new Seat());
       }

       return seatList;
    }


}