/**
 * TextTools.java
 * @author Herb Wolfe Jr <hwolfe71@gmail.com>
 * 
 * Implementation of the TextToolsInterface
 */

import java.util.*;
import static java.lang.Character.*;

public class Texttools {
    // number of letters, and the ascii offsets for upper/lower case letters
    private static int ALPHAS = 26;
    private static int UPPEROFFSET = 65;
    private static int LOWEROFFSET = 97;

    /**
     * Get the position of a given character in the alphabet
     * @param char - the character
     * @return int - the position of the letter in the alphabet or 0, if it
     * isn't a letter
     */

    private static int getAlphaPos(char c) {
        int pos = -1;

        if (isLetter(c)) {
			pos = (int)c - (isUpperCase(c) ? UPPEROFFSET : LOWEROFFSET);
        } 

        return pos;
    }

    /**
     * counts the number of each letter in a given string
     * @param String - the string in which to count the letters
     * @return int[26] - an array containing the number of each letter
     */

    public static int[] getLetterCount(String str) {
        int[] count = new int[ALPHAS];
        int len = str.length();

        for (int i = 0; i < len; i++) {
            int l = getAlphaPos(str.charAt(i));
			if (l >= 0) {
				++count[l];
			}
        }

        return count;
    }

    /**
     * Counts the number of words in a given string
     * A word in this case consists of letters separated by non-letters
     * @param String - the string in which to count the words
     * @return int - the number of words
     */

    public static int getWordCount(String str) {
        int count = 0;
        int i = 0;
        int l = str.length();

        while (i < l) {
            while ( (i < l) && (!isLetter(str.charAt(i))) )
                i++;
            if (i < l) {
                count++;
                while ( (i < l) && (isLetter(str.charAt(i))) )
                    i++;
            }
        }

		return count;
    }

    /**
     * Determines if the string is a palindrome
     * @param String - the string to check
     * @return boolean - if the string is a palindrome
     */

    public static boolean isPalindrome(String str) {
        String sb = str.replaceAll("[^a-zA-z]","");
        String rev = reverseString(sb);
        return rev.equalsIgnoreCase(sb);
    }

    /**
     * Reverses a string
     * @param String - the string to reverse
     * @return String - the reversed string
     */

    public static String reverseString(String str) {
        return new StringBuffer(str).reverse().toString();
    }

}
