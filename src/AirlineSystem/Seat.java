package AirlineSystem;

enum sClass {
    ECONOMY,BUSSINESS
}

/**
 * Documentation About Create the Seat Class,
 * The user is required to set the total amount of seat before start creating the list of seat
 */

class Seat {
    private String seatNo;
    protected sClass seatClass;
    //No-args Constructor
    Seat(String seatNo){ 
        this.seatNo = seatNo;
        this.seatClass = createSeatClass();
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

