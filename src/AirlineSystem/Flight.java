package AirlineSystem;
import java.util.List;
import java.util.ArrayList;

class Flight {

    private String flightCode;
    private Airline airline;
    private int totalSeat;
    private List<Seat> seatList = new ArrayList<Seat>();
    private static int nthFlight = 0;

    //Parameterized Constructor
    Flight(Airline airline, int totalSeat){

         //use a method
        this.flightCode = createFlightCode();
        this.airline = airline;
        this.totalSeat = totalSeat;
        seatList = createSeatList(totalSeat);
        nthFlight++;
    }

    private String createFlightCode(){
        if(nthFlight < 10)
            return "F00" + nthFlight;
        else if(nthFlight < 100)
            return "F0" + nthFlight;
        else
            return "F"+nthFlight;

    }

    //No-args Constructor
    Flight(){
        this("",null,0);
    }

    public String getFlightCode(){
        return flightCode;
    }

    //getter
    public int getTotalSeat() {
        return totalSeat;
    }
    public List<Seat> getSeatList() {
        return seatList;
    }
    public Airline getAirline() {
        return airline;
    }
    //setter
    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }
    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    //ToString
    @Override
    public String toString() {
        return String.format("FlightCode : %s \nTotalSeat : %d\n Airline : %s \n",flightCode,totalSeat,airline.getAirlineName());
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
        //Set the totalSeat in the Seat ClassF
        //There are some comments before the Seat Class
        Seat.setTotalSeat(totalSeat);

        for(int i = 0; i < totalSeat; i++){
            seatList.add(new Seat());
       }

       return seatList;
    }


}