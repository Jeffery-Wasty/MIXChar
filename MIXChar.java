package q3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <p>Represents a MIXChar object. A MIXChar object has a value
 * assoicated with it, and that value corresponds to a MIXChar
 * character. There are 56 different MIXChar characters in total.
 * </p>
 * <p>The object has many useful methods, including those that
 * allow converting between Java chars, and MIXChar chars, converting
 * a string to an array of MIXChars, and back, and finding the value
 * of any MIXChar character.</p>
 * <p>The conversions between Java char, Unicode value, MIXChar char, 
 * and MIXChar value are accomplished using a 'key' which in 
 * instantiated by reading a string of all possible MIXChars, and a 
 * file of all Unicode values corresponding to those MIXChars. If 
 * one tries to use the MIXChar methods with a non-MIXChar character, 
 * errors are displayed.</p>
 * <p>Finally we can express any number of MIXChars (and by extension
 * any string) as an array of long. Each long in the array is able to
 * hold 11 characters, and conversion between long and MIXChar is done
 * quickly. The main method demonstrates this conversion.</p>
 *
 * @author Jeffery Wasty
 * @version 1.0
 */
public final class MIXChar {
    
    /**
     * <p>The number of rows in the key; three.</p>
     */
    public static final int ROWS = 3;
    
    /**
     * <p>The maximum string length that can be converted into
     * a single long; eleven.</p>
     */
    public static final int MAX_LENGTH = 11;
    
    /**
     * <p>The number of columns in the key; fifty-six.</p>
     */
    public static final int COLUMNS = 56;
    
    /**
     * <p>The base that MIXChars use. Useful for converting
     * to decimal, and back; fifty-six.</p>
     */
    public static final int BASE = 56;
    
    /**
     * <p>An array of string holding MIXChar characters, their values, and
     * the corresponding Unicode values of the MIXChar characters.</p>
     */
    private static String[][] key;
    
    /**
     * <p>A character representing the capital delta.</p>
     */
    private static final char DELTA = '\u0394';
    
    /**
     * <p>A character representing the capital sigma.</p>
     */
    private static final char SIGMA = '\u03A3';
    
    /**
     * <p>A character representing the capital pi.</p>
     */
    private static final char PI = '\u03A0';
    
    /**
     * <p>A string containing all possible MICChar character values.</p>
     */
    private static final String MIX_STRING = " ABCDEFGHI" + DELTA
                                           + "JKLMNOPQR" + SIGMA + PI
                                           + "STUVWXYZ0123456789.,()+-*"
                                           + "/=$<>@;:'";
    
    
    /**
     * <p>An int holding the current value of the MIXChar, corresponds
     * to a character.</p>
     */
    private int value;
    
    /**
     * <p>Constructs an object of type MIXChar.</p>
     * 
     * @param value an int
     */
    private MIXChar(int value) {
        this.value = value;
    }
    
    /**
     * <p>Checks to see if the key is already made,
     * if not then it makes it.</p>
     */
    static void keyMaker() {
            
        key = new String[ROWS][COLUMNS];
        
        File file = new File("src/unicodes.txt");
        Scanner scan;
        try {
            scan = new Scanner(file);
            for (int i = 0; i < key[0].length; i++) {
                key[0][i] = Character.toString(MIX_STRING.charAt(i));
                key[1][i] = Integer.toString(i);
                key[2][i] = scan.next();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * <p>Checks to see if a given input character corresponds to a
     * MIXChar character. Does this by converting the character
     * to its Unicode value and looking up the value in the key.</p>
     * 
     * @param c a char
     * @return true if the character corresponds to a MIXChar character,
     *              false otherwise
     */
    static boolean isMIXChar(char c) {
        for (int k = 0; k < key[0].length; k++) {
            if (key[2][k].equals(Integer.toString((int) (c)))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * <p>Converts the character to a MIXChar, if possible. If
     * the character is not a MIXChar character, throws an
     * exception.</p>
     * 
     * @param c a char, must be a MIXChar character
     * @return mix as a MIXChar
     */
    static MIXChar toMIXChar(char c) {
        if (!isMIXChar(c)) {
            throw new IllegalArgumentException("Character is not a "
                    + "MIXChar character.");
        } else {
            for (int l = 0; l < key[0].length; l++) {
                if (key[2][l].equals(Integer.toString((int) (c)))) {
                    MIXChar mix = new MIXChar(Integer.parseInt(key[1][l]));
                    return mix;
                }
            }
        }
        return null;
    }
    
    /**
     * <p>Converts a MIXChar character to the corresponding Java character.</p>
     * 
     * @param x a MIXChar, must be constructed with a valid value
     * @return c as a char
     */
    static char toChar(MIXChar x) {
        char c = 0;
        for (int m = 0; m < key[0].length; m++) {
            if (Integer.parseInt(key[1][m]) == x.value) {
                c =  key[0][m].charAt(0);
                return c;
            }
        }
        if (isMIXChar(c)) {
            return c;
        } else {
            throw new IllegalArgumentException("The MIXChar object has "
                                               + "an invalid value.");
        }
    }
    
    /**
     * <p>Turns a MIXChar array into a String with characters
     * corresponding to those in the array. Will ignore empty
     * positions.</p>
     * 
     * @param m a MIXChar
     * @return result as a String
     */
    static String toString(MIXChar[] m) {
        String result = "";
        for (int n = 0; n < m.length; n++) {
            if (m[n] != null) {
                result += toChar(m[n]);
            }
        }
        return result;
    }
    
    /**
     * <p>Converts a String to an array of MIXChar characters.</p>
     * 
     * @param s a String, throws an exception if any characters
     *              are non-MIXChar characters
     * @return mixArray as a MIXChar[]
     */
    static MIXChar[] toMIXChar(String s) {
        MIXChar[] mixArray = new MIXChar[s.length()];
        for (int o = 0; o < s.length(); o++) {
            if (!isMIXChar(s.charAt(o))) {
                throw new IllegalArgumentException("String contains "
                                                   + "non-MIXChar characters.");
            } else {
                mixArray[o] = toMIXChar(s.charAt(o));
            }
        }
        return mixArray;
    }
    
    /**
     * <p>Returns an array of longs holding the values of array m
     * with 11 MIXChar characters per long (max).</p>
     * 
     * @param m a MIXChar[]
     * @return as a long[]
     */
    static long[] encode(MIXChar[] m) {
        String temp = toString(m);
        long[] result;
        if (m.length % MAX_LENGTH == 0) {
            result = new long[m.length / MAX_LENGTH];
        } else {
            result = new long[(m.length / MAX_LENGTH) + 1];
        }
        int longPosition = 0;
        int start = 0;
        boolean moreString = true;
        while (moreString) {
            try {
                String substring = temp.substring(start, start + MAX_LENGTH);
                result[longPosition] = converter(substring);
                start += MAX_LENGTH;
                if (longPosition + 1 < result.length) {
                    longPosition++;
                } else {
                    moreString = false;
                }
            } catch (java.lang.StringIndexOutOfBoundsException e) {
                String substring = temp.substring(start);
                result[longPosition] = converter(substring);
                moreString = false;
            }
        }
        return result;
    }
    
    /**
     * <p>Used in the process to turn the array of MIXChars
     * into an array of long. Converts by multipling the
     * mix value of the char with 56 to the power of the chars
     * position in the string (increases from right to left,
     * starts with 0).</p>
     * 
     * @param string a String
     * @return result as a long
     */
    static long converter(String string) {
        int place = string.length() - 1;
        long result = 0L;
        for (int t = 0; t < string.length(); t++) {
            result += (valueOf(string.charAt(t)) 
                       * (long) Math.pow(BASE, place));
            place -= 1;
        }
        
        return result;
    }
    
    /**
     * <p>Returns an array of MIXChar characters holding the
     * values of array l with 11 MIXChar characters being
     * stored in each position of l.</p>
     * 
     * @param l a long[]
     * @return as a MIXChar[]
     */
    static MIXChar[] decode(long[] l) {
        MIXChar[] result = new MIXChar[l.length * MAX_LENGTH];
        int position = 0;
        for (int n = 0; n < l.length; n++) {
            String temp = "";
            while (!deconverter(l[n]).equals("0")) {
                temp += deconverter(l[n]);
                l[n] = Long.divideUnsigned(l[n], BASE);
            }
            for (int m = temp.length() - 1; m >= 0; m--) {
                MIXChar mix = new MIXChar(valueOf(temp.charAt(m)));
                result[position] = mix;
                position++;
            }
        }
        return result;
    }
    
    /**
     * <p>Used in the process to turn the array of long
     * back in to the array of MIXChars. Converts by dividing the
     * long by the base (56) and storing the remainders.</p>
     * 
     * @param l a long
     * @return m as a string
     */
    static String deconverter(long l) {
        if (l == 0) {
            return "0";
        } else {
            MIXChar m = new MIXChar((int) Long.remainderUnsigned(l, BASE));
            return m.toString();
        }
    }
    
    /**
     * <p>Returns a string containing the current MIXChar
     * character as a Java character.</p>
     * 
     * @return character as a string
     */
    public String toString() {
        String character = "";
        for (int r = 0; r < key[1].length; r++) {
            if (Integer.parseInt(key[1][r]) == ordinal()) {
                character += key[0][r];
                return character;
            }
        }
        return character;
    }
    
    /**
     * <p>Returns the value corresponding to this MIXChar.</p>
     * 
     * @return value as an int
     */
    int ordinal() {
        return value;
    }
    
    /**
     * <p>Will return the MIXChar value of any MIXChar character.</p>
     * 
     * @param c a character
     * @return result as an int
     */
    static int valueOf(char c) {
        int result = 0;
        for (int i = 0; i < key[2].length; i++) {
            if (Integer.parseInt(key[2][i]) == (int) c) {
                result = Integer.parseInt(key[1][i]);
                return result;
            }
        }
        return result;
    }

    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments unused
     */
    public static void main(String[] args) {
        keyMaker();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please print a line to convert.");
        String test = scan.nextLine();
        scan.close();
        MIXChar[] mixArray = toMIXChar(test);
        long[] longArray = encode(mixArray);
        System.out.println(Arrays.toString(longArray));
        MIXChar[] mixArrayTwo = decode(longArray);
        System.out.println(toString(mixArrayTwo));

        System.out.println("Question three was called and ran sucessfully.");
    }
    
}