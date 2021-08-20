package AirlineSystem;

enum sStatus{
    SEATED,NOTSEATED
}


class fSeat extends Seat {

    double seatPrice;
    sStatus seatStatus;

    fSeat(){

    }

    fSeat(String seatNo, sClass seatClass, double seatPrice, sStatus seatStatus){
        super(seatNo,seatClass);
        this.seatPrice = seatPrice;
        this.seatStatus = seatStatus;
    }
}