package AirlineSystem;

import java.util.List;
import java.util.Scanner;

/**
 * Debug Your Classes Here!, if you don't want to mess up the main program! Put
 * your test code here and just click the run button. it wont bump with the
 * Main.java
 */

// A B C D E F G H
// 1
// 2
// 3
// 4
// 5
// 6
// 7 X
//

class DebugMain {

    public static void main(String[] args) {

        Main.insertDataToList();
        Scanner sc = new Scanner(System.in);
       
        RegisteredAccount acc = new RegisteredAccount();

        acc.makeReservation(sc);

        List<Reservation> reservation = Main.getReservations();

        System.out.println("\n\n-----RESCHEDULE TICKET-----");

        int n = 1;
        System.out.println("\nYour Reservations:");
        for (Reservation res : reservation) {
            System.out.print(n + ". ");
            System.out.println(res.toString());
            System.out.println("\n");
            n++;
        }
        System.out.print("Select Reservation to Reshedule > ");
        int choiceReschedule = sc.nextInt();

        ((RegisteredAccount) acc).rescheduleTicket(reservation.get(choiceReschedule - 1), sc);

        

        sc.close();
    }
}
