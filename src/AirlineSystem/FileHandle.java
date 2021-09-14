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

    ArrayList<String>airlines = new ArrayList<String>();
try{
    File myobj = new File("src\\AirlineSystem\\itemToScrap.txt");
    Scanner myReader = new Scanner(myobj);
    while(myReader.hasNext()){
        String data = myReader.nextLine();
        if(data.contains("_top")){
            String airline = data.substring(data.indexOf("_top"));
            airline = airline.substring(0,airline.indexOf('<'));
            airline = airline.substring(6,airline.length());
            airlines.add(airline);
        }

    }
}catch(FileNotFoundException e){
    System.out.println("An error occured");
}

try {
    FileWriter myWriter = new FileWriter("src\\AirlineSystem\\Airlines.txt");
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
