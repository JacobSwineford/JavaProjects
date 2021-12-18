package Misc.ConsoleApplications;

import java.util.*;

/**
 * Find the ascii values of a sentence and puts the
 * words in order based on their ascii values.
 *
 * @author Jacob Swineford
 */
public class WordCost {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sentence to be organized: ");
        String[] words = in.nextLine().split(" ");
        Integer[] values = new Integer[words.length];
        Map<Integer, String> map = new HashMap<>();

        // word[0] value will be contained in values[0]
        for (int i = 0; i < words.length; i++) {
            // find the value of word[i]
            String word = words[i];
            char[] chars = word.toCharArray();
            int sum = 0;
            for (char cha : chars) {
                sum += cha;
            }
            values[i] = sum;
            // word and it's ascii value are found,
            // bind them together in the binding arrays
            map.put(values[i], words[i]);
        }

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i] + " " + words[i]);
        }
        System.out.println();

        Arrays.sort(values);
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i] + " " + map.get(values[i]));
        }
    }
}

class cost implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
