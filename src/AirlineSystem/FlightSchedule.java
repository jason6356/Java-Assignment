package AirlineSystem;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightSchedule {
    private String flightScheduleCode;
    private static int nthFlightSchedule = 0; 
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
        this.flightScheduleCode = makeflightScheduleCode();
        nthFlightSchedule++;
        
        flightSeat = makefSeatList(flight.getTotalSeat());
        flightScheduleCode = makeflightScheduleCode();
    }

    private String makeflightScheduleCode(){
        return nthFlightSchedule < 10 ? "FS00" + nthFlightSchedule : nthFlightSchedule < 100 ? "FS0" + nthFlightSchedule : "FS" + nthFlightSchedule;
    }

    //TODO: Refactor this toString method
    //ToString
    public String toString() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatBoth = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        //| DepartDate | DepartTime | Depart Airport | Destination Airport | Estimated Time | Direction |

        return String.format("%4s|%10s|%5s|%-37s|%-37s|%13s|%-10s -> %-10s|", flightScheduleCode,flightDate.format(formatDate),departureTime.format(formatTime),location.getAirportName(),destination.getAirportName(),estimatedArrivalTime.format(formatBoth),location.getLocation(),destination.getLocation());
    }

    public String displayFlightSchedule(){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatBoth = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return String.format("%5s|%-11s|%-14s|%-37s|%-37s|%-22s|%-10s -> %-10s|", flightScheduleCode,flightDate.format(formatDate),departureTime.format(formatTime),location.getAirportName(),destination.getAirportName(),estimatedArrivalTime.format(formatBoth),location.getLocation(),destination.getLocation());
    }

    public String displayInfo(){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatBoth = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        //| DepartDate | DepartTime | Depart Airport | Destination Airport | Estimated Time | Direction |

        // return String.format("%4s|%10s|%5s|%-37s|%-37s|%13s|%-10s -> %-10s|", flightScheduleCode,flightDate.format(formatDate),departureTime.format(formatTime),location.getAirportName(),destination.getAirportName(),estimatedArrivalTime.format(formatBoth),location.getLocation(),destination.getLocation());
        return String.format("%-10s|%5s|%-37s|%-37s|%-13s|%-10s -> %-10s|",flightDate.format(formatDate),departureTime.format(formatTime),location.getAirportName(),destination.getAirportName(),estimatedArrivalTime.format(formatBoth),location.getLocation(),destination.getLocation());
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
    public String getFlightScheduleCode() {
        return flightScheduleCode;
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
        System.out.print("    A  B   C  D  E  F   G  H ");
        int row = 0;
        int col = 9;
        for (fSeat seat : flightSeat) 
        {
            if(col > 8){
                System.out.println();
                row++;
                System.out.printf("%-2d ", row);
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

    public List<fSeat> bookSeat(Scanner s){

        List<fSeat> bookedSeats = new ArrayList<fSeat>();

        System.out.print("Enter Number of Seats to Book - ");
            int numberOfSeats = s.nextInt();
            char bookSeatConfirm;
            //Loop through n times when buying the seat
            for(int j = 0; j < numberOfSeats; j++){
                //Clear Screen
                Main.clearConsole();
                //Display Seats
                displaySeats();
                //Get seat number
                System.out.print("Enter the seatNo according to the rows and columns (Ex: A1) :");
                String seatInput = s.next();
                fSeat seat = validateSeatID(seatInput);
                //validate
                while(seat == null){
                    System.out.print("Enter the seatNo according to the rows and columns (Ex: A1) :");
                    seatInput = s.next();
                    seat = validateSeatID(seatInput);
                }
                //Display the details
                System.out.println(seat);

                //ask for confirmation
                System.out.print("Confirm To Book The Seat ? (Y/N)");
                bookSeatConfirm = s.next().charAt(0);

                if(Character.toUpperCase(bookSeatConfirm) == 'Y'){
                    bookedSeats.add(seat);
                    seat.makeSeatSeated();
                }
                else
                    //force to loop another time 
                    j--;
            }   
        return bookedSeats;
    }

    private fSeat validateSeatID(String seatID){

        for (fSeat seat : flightSeat) {
            if(seatID.equals(seat.getSeatNo())){
                if(seat.getSStatus() == sStatus.SEATED){
                    System.out.println("The seat is seated");
                    return null;
                }
                else
                    return seat;
            }
        }
        System.out.println("No Such SeatID found!");
        return null;
    }
}
