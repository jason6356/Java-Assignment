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

    private String convertDateToString(){
        return flightDate.getYear() + "-" + flightDate.getMonth() + "-" + flightDate.getDayOfMonth();
    }

    private String convertTimeToString(){
        return departureTime.getHour() + ":" + departureTime.getMinute();
    }

    private String convertLocalDateTimeToString(){
        return estimatedArrivalTime.getYear() + "-" + estimatedArrivalTime.getMonthValue() + "-" + estimatedArrivalTime.getDayOfMonth() + " " + estimatedArrivalTime.getHour() + ":" + estimatedArrivalTime.getMinute();
    }

    //TODO: Refactor this toString method
    //ToString
    public String toString() {
        //| DepartDate | DepartTime | Depart Airport | Destination Airport | Estimated Time | Direction |
        return String.format("%10s|%5s|%-20s|%-20s|%10s|%10s -> %10s|", convertDateToString(),convertTimeToString(),location.getAirportName(),destination.getAirportName(),convertLocalDateTimeToString(),location.getLocation(),destination.getLocation());
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public Airport getDestination() {
        return destination;
    }
    public Flight getFlight() {
        return flight;
    }
    public LocalDate getFlightDate() {
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

        List<fSeat> fSeatList = new ArrayList<fSeat>();
        char col = 'A';
        int row = 1;
        String seatNo;
        for(int i = 0; i < totalSeats; i++){
            
            if(col > 'H'){
                col = 'A';
                row++;
            }
            seatNo = String.format("%c%d", col,row);
            fSeatList.add(new fSeat(seatNo));
            col++;
        }
        return fSeatList;
    }
    // //front cabinet
    // A B  C D E F  G H
    // [][] [][][][] [][]
    // [][] [][][][] [][]
    // [][] [][][][] [][]
    // [][] [][][][] [][]

    // //back cabinet

    public void displaySeats(){
        System.out.println("  A  B   C  D  E  F   G  H ");
        int row = 0;
        int col = 9;
        for (fSeat seat : flightSeat) 
        {
            if(col > 8){
                System.out.println();
                row++;
                System.out.print(row);
                col = 1;
            }
            else if(col == 3 || col == 7){
                System.out.print(" ");
            }
            
            if(seat.getSStatus() == sStatus.SEATED){
                System.out.print("[X]");
            }
            else{
                if(seat.getSeatClass() == sClass.BUSSINESS){
                    System.out.print("[B]");
                }
                else
                    System.out.print("[E]");
            }
            col++;
        }
        //Next line
        System.out.println();
    }
}
