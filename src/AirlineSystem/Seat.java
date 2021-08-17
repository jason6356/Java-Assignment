package AirlineSystem;
enum sClass {
    ECONOMY,BUSSINESS
}

class Seat {
    private String seatNo;
    private sClass seatClass;

    Seat(){

    }

    Seat(String seatNo, sClass seatClass){

        this.seatNo = seatNo;
        this.seatClass = seatClass;
    }



}

