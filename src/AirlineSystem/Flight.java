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
        this(null,0);
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
        // return String.format("FlightCode : %s \nTotalSeat : %d\n Airline : %s \n",flightCode,totalSeat,airline.getAirlineName());
        return String.format("%-15s %-20s %-20s\n", flightCode, airline.getAirlineName(), totalSeat);
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
        char col = 'A';
        int row = 1;
        String seatNo;
        for(int i = 0; i < totalSeat; i++){
            
            if(col > 'H'){
                col = 'A';
                row++;
            }
            seatNo = String.format("%c%d", col,row);
            seatList.add(new Seat(seatNo));
            col++;
        }
       return seatList;
    }


}