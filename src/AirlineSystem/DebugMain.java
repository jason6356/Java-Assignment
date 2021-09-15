package AirlineSystem;

import java.time.LocalDate;
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
        
        LocalDate date;

        Scanner s = new Scanner(System.in);

        String datestr = s.next();
        date = LocalDate.parse(datestr);
        System.out.println(date);
    }
}
