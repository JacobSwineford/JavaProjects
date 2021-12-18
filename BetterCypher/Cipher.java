package Misc.CustomClasses.BetterCypher;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that may be used to encode and decode messages using a digital Cipher Disk.
 * Currently, There are two layers for this disk. Both can be spun by using either
 * <code>spinLeft()</code>, <code>spinRight()</code>, or <code>spinToMatch()</code>
 * and set using <code>setOuter()</code> or <code>setInner()</code>.
 *
 * @author Jacob Swineford
 */
public class Cipher {

    private static final String DEFAULT_KEY = " abcdefghijklmnopqrstuvwxyz";
    private String outerKey = DEFAULT_KEY;
    private String innerKey = DEFAULT_KEY;

    private Cipher(char pair1, char pair2) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int r = rand.nextInt(innerKey.length());
        char c = innerKey.charAt(r);
        innerKey = spinToMatch(outerKey, pair1, innerKey, pair2);
    }

    /**
     * Sets the Outer Cipher to the specified key.
     *
     * @param key given key
     */
    void setOuter(String key) {
        outerKey = key;
    }

    /**
     * Sets the Inner Cipher to the specified key.
     *
     * @param key given key
     */
    void setInner(String key) {
        innerKey = key;
    }

    /**
     * Encodes the given string using the current configuration of the inner and
     * outer ciphers. If a given character isn't recognized by the outer cipher
     * then it is replaced by '?'.
     *
     * @param str string to encode
     * @return Encoded string. If the inner and outer ciphers have a different length,
     *         null is returned.
     */
    private String encode(String str) {
        if (outerKey.length() != innerKey.length()) return null;
        StringBuilder s = new StringBuilder();
        for (char c : str.toCharArray()) {
            int o = outerKey.indexOf(c);
            if (o != -1) s.append(innerKey.charAt(o));
            else s.append('?');
        }
        return s.toString();
    }

    /**
     * Decodes the given string using the current configuration of the inner and
     * outer ciphers. If a given character isn't recognized by the inner cipher,
     * then it is replaced by '?'.
     *
     * @param str string to decode
     * @return Encoded string. If the inner and outer ciphers have a different length,
     *         null is returned.
     */
    private String decode(String str) {
        if (outerKey.length() != innerKey.length()) return null;
        StringBuilder s = new StringBuilder();
        for (char c : str.toCharArray()) {
            int i = innerKey.indexOf(c);
            if (i != -1) s.append(outerKey.charAt(i));
            else s.append('?');
        }
        return s.toString();
    }

    /**
     * Returns a new string that represents the given base string spun to the right
     * any given number of times.
     *
     * @param base base string
     * @param times number of places to rotate
     *
     * @return string spun to the right
     */
    private static String spinRight(String base, int times) {
        if (times < 0) times *= -1;
        times %= base.length();
        StringBuilder spin = new StringBuilder();

        for (int i = 0; i  < base.length(); i++) {
            int spinIndex = i - times;
            if (spinIndex < 0) {
                spinIndex = base.length() + spinIndex;
            }
            spin.append(base.charAt(spinIndex));
        }
        return spin.toString();
    }

    /**
     * Returns a new string that represents the given base string spun to the left
     * any given number of times.
     *
     * @param base base string
     * @param times number of places to rotate
     *
     * @return string spun to the left
     */
    private static String spinLeft(String base, int times) {
        if (times < 0) times *= -1;
        times %= base.length();
        StringBuilder spin = new StringBuilder();

        for (int i = 0; i  < base.length(); i++) {
            int spinIndex = i + times;
            if (spinIndex >= base.length()) {
                spinIndex %= base.length();
            }
            spin.append(base.charAt(spinIndex));
        }
        return spin.toString();
    }

    /**
     * Returns a new string that represents the given toSpin string spun to match
     * the base string, such that a character from the base string lines up with a
     * character from the toSpin string. If a given character isn't recognized by
     * the this cipher, then it is replaced by '?'.
     *
     * @param base given base string
     * @param baseChar guiding base character
     * @param toSpin given spin string
     * @param spinChar guiding spin character
     *
     * @return string that is spun to match the baseChar, based on the toSpin string
     */
    private static String spinToMatch(String base, char baseChar, String toSpin, char spinChar) {
        int times = base.indexOf(baseChar) - toSpin.indexOf(spinChar);
        if (times > 0) return spinRight(toSpin, times);
        return spinLeft(toSpin, times);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a word to be ciphered: ");
        String s = in.nextLine();
        Cipher c = new Cipher(' ', 'r');
        String e = c.encode(s);
        String d = c.decode(e);
        System.out.println(e);
        System.out.println(d);
    }
}
