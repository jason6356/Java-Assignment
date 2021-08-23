package AirlineSystem;

enum sClass {
    ECONOMY,BUSSINESS
}

/**
 * Documentation About Create the Seat Class,
 * The user is required to set the total amount of seat before start creating the list of seat
 */

class Seat {
    private static int totalSeat = 0;
    private static char seatCol = 'A';
    private static int seatRow = 1;
    private String seatNo;
    protected sClass seatClass;
    //No-args Constructor
    Seat(){ 
        this.seatNo = createSeatNo();
        this.seatClass = createSeatClass();
        seatCol++;
        //Reset the seatCol and seatRow once the List of Seats is done created
        if(totalSeat == 0){
            seatCol = 'A';
            seatRow = '1';
        }
        //decrement the total amount of seat to be created
        totalSeat--;
    }

    //Getter and setters
    public String getSeatNo() {
        return seatNo;
    }
    public sClass getSeatClass() {
        return seatClass;
    }
    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
    public void setSeatClass(sClass seatClass) {
        this.seatClass = seatClass;
    }
    public static void setTotalSeat(int totalSeat) {
        Seat.totalSeat = totalSeat;
    }

    public static int getTotalSeat(){
        return totalSeat;
    }

    /**
     * ToString
     * @return SeatNo, SeatClass in String Format
     */
    @Override
    public String toString() {
        return String.format("SeatNo: %s \nSeatClass: %s \n", seatNo, seatClass.toString());        
    }

    //Methods
    /**
     * Algorithm to create a SeatID in sequence
     * @return SeatNo
     */
    private String createSeatNo(){
        if(seatCol > 'H'){ 
            seatCol = 'A';
            seatRow++;
        }
        return String.format("%c%d", seatCol,seatRow);
    }

    /**
     * Algorithm to create a SeatClass according to the location of the seat
     * @return SeatClass
     */

    private sClass createSeatClass(){
        //2 seats near the window is considered as Bussiness Class
        if(this.seatNo.charAt(0) == 'A' || this.seatNo.charAt(0) == 'B' || this.seatNo.charAt(0) == 'G' || this.seatNo.charAt(0) == 'H')
            return sClass.BUSSINESS;
        //else Economy Class
        return sClass.ECONOMY;
    }

}

