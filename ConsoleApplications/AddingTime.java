package Misc.ConsoleApplications;

import javafx.util.Pair;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class AddingTime {

    public static void main(String[] args) {
        System.out.println("Enter the current time (hh:mm pm/am): ");
        Scanner in = new Scanner(System.in);
        String[] time = in.next().split(":");
        String m = in.next();
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);

        System.out.println("Enter the time to be added (hh:mm) ");
        String[] addedTime = in.next().split(":");
        minutes += Integer.parseInt(addedTime[1]);
        hours += Integer.parseInt(addedTime[0]) + (minutes / 60);
        minutes = minutes % 60;

        Pair<String, Integer> trueTime = truncatedTime(m, hours);
        String h; String min;

        if (trueTime.getValue() < 10) h = "0" + trueTime.getValue();
        else h = trueTime.getValue() + "";
        if (minutes < 10) min = "0" + minutes;
        else min = minutes + "";

        String fin;
        fin = h + ":" + min + " " + trueTime.getKey();
        System.out.println(fin);

    }

    private static Pair<String, Integer> truncatedTime(String m, int hours) {
        while (hours / 12 != 0) {
            System.out.println(m);
            if (m.equals("am")) m = "pm";
            else if (m.equals("pm")) m = "am";
            System.out.println(m);
            hours -= 12;
        }
        return new Pair<>(m, hours);
    }
}
