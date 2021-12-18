package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class StringBuildifier {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            System.out.println("sb.append(\"" + in.nextLine().trim().replace('\"', '\'') + "\");");
//        }
        String str = "http://localhost:8084/Fossils";
        String str2 = "cswebstage.mads.bloomu.edu/Fossils";
        // change local host to cswebstage
        if (str.contains("localhost")) {

        }
    }

    static int LeapYearsBetween(int start, int end)
    {
        return LeapYearsBefore(end) - LeapYearsBefore(start + 1);
    }

    static int LeapYearsBefore(int year)
    {
        year--;
        return (year / 4) - (year / 100) + (year / 400);
    }
}
