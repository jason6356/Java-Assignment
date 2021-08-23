package AirlineSystem;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Debug Your Classes Here!, if you don't want to mess up the main program!
 * Put your test code here and just click the run button. it wont bump with the Main.java
 */

class DebugMain{
    
    public static void main(String[] args) {
        //Linking Connection Test <- can delete
        Airline a1 = new Airline("Airasia","A001");
        Flight flight = new Flight("F001",a1,50);
        a1.addFlight(flight);
        System.out.println(flight);
    }
    
}