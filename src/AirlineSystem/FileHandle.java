package AirlineSystem;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class FileHandle {

    //Address is part of Account
    //Account <- Reservation
    //Reservation <- FlightSchedule
    //FlightSchedule <- flight, airport, fseats
    //flight <-- airport, airline
    //Airport done
    //Airline done

    private static List<Airport> airports = new ArrayList<Airport>();
    private static List<Airline> airlineList = new ArrayList<Airline>();
    private static List<Flight> flightList = new ArrayList<Flight>();
    private static List<FlightSchedule> flightSchedules = new ArrayList<FlightSchedule>();
    public static void main(String[] args) {
        airlineDataInsert();
        airportDataInsert();
        airports.forEach((e) -> System.out.println(e));
        makeFlights();
        airlineList.forEach((e) -> System.out.println(e));
    }

    public static void makeFlightSchedules(){

        Random rand = new Random();

        for(int i = 0; i < 10; i++){

        }
    }

    public static void makeFlights(){
        Random rand = new Random();
        
        for(int i = 0; i < 100; i++)
        {
            // 8 col
            int random_integer2 = rand.nextInt(airlineList.size()-0); 
            int randomSeat = rand.nextInt(3-1) + 1;
            int totalSeat = randomSeat == 3 ? 104 : randomSeat == 2 ? 112 : 120;


            flightList.add(new Flight(airlineList.get(random_integer2),totalSeat));
            airlineList.get(random_integer2).addFlight(flightList.get(i));
        }
        flightList.forEach((i) -> System.out.println(i));
    }

    public static void airportDataInsert(){

        try{
            File myobj = new File("src\\AirlineSystem\\Airports.txt");
            Scanner myReader = new Scanner(myobj);
            while(myReader.hasNext()){
                String data = myReader.nextLine();
                String[] item = data.split("-");
                
                airports.add(new Airport(item[1],item[0]));
                
            }
        }catch(FileNotFoundException e){
            System.out.println("An error occured");
        }

        System.out.println("Total items in the list is " + airports.size());

    }


    public static void airlineDataInsert() {
        
        //take values from airlines.txt -> make in an object

        try{
            File myobj = new File("src\\AirlineSystem\\Airlines.txt");
            Scanner myReader = new Scanner(myobj);
            while(myReader.hasNext()){
                String data = myReader.nextLine();
                String[] item = data.split("-");
                airlineList.add(new Airline(item[0],item[1]));
                
            }
        }catch(FileNotFoundException e){
            System.out.println("An error occured");
        }

        System.out.println("Total items in the list is " + airlineList.size());

    }

    public static void webScrap() {

        //Scraping from the element from website for all the airlines
        //written in 3 am :)
    ArrayList<String>airlines = new ArrayList<String>();
try{
    File myobj = new File("src\\AirlineSystem\\itemToScrap.txt");
    Scanner myReader = new Scanner(myobj);
    int row = 1;
    while(myReader.hasNext()){
        String data = myReader.nextLine();
        if(data.contains("a href")){
            String airline = data.substring(data.indexOf("a href"));
            airline = airline.substring(airline.indexOf('>'));
            airline = airline.substring(airline.indexOf('>') + 1,airline.indexOf('<'));
            airlines.add(airline);
        }

    }
}catch(FileNotFoundException e){
    System.out.println("An error occured");
}

try {
    FileWriter myWriter = new FileWriter("src\\AirlineSystem\\Airports.txt");
    for (String string : airlines) {
        myWriter.write(string + "\n");
    }
    myWriter.close();
    System.out.println("Successfully wrote to the file.");
  } catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
  }
    }

}
