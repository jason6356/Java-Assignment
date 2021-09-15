package AirlineSystem;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FileHandle {

    //Address is part of Account
    //Account <- Reservation
    //Reservation <- FlightSchedule
    //FlightSchedule <- flight, airport, fseats
    //flight <-- airport, airline
    //Airport 
    //Airline
    public static void main(String[] args) {

        //Scraping from the element from website for all the airlines
        //written in 3 am :)
    ArrayList<String>airlines = new ArrayList<String>();
try{
    File myobj = new File("src\\AirlineSystem\\itemToScrap.txt");
    Scanner myReader = new Scanner(myobj);
    while(myReader.hasNext()){
        String data = myReader.nextLine();
        if(data.contains("a href")){
            String airline = data.substring(data.indexOf("a href"));
            airline = airline.substring(airline.indexOf('>'));
            if(airline.length() < 50 && airline.contains("Airport")){
                airline = airline.substring(1, airline.indexOf("</a>"));
                System.out.println(airline);
                airlines.add(airline);
            }
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
