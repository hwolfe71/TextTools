/**
 * TextTools.java
 * @author Herb Wolfe Jr <hwolfe71@gmail.com>
 * 
 * Implementation of the TextToolsInterface
 */

import java.util.*;

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
        int pos = 0;

        if (Character.isLetter(c)) {
            if (Character.isUpperCase(c)) {
                pos = (int)c - UPPEROFFSET;
            } else {
                pos = (int)c - LOWEROFFSET;
            }
        } else {
			pos = -1;
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
            while ( (i < l) && (!Character.isLetter(str.charAt(i))) )
                i++;
            if (i < l) {
                count++;
                while ( (i < l) && (Character.isLetter(str.charAt(i))) )
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
        String sb = new String();
		char ch;
        
		// Strip the non-letters from the string.
        for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                sb += ch;
            }
        }
       
        String rev = new String(reverseString(sb));
        return rev.equalsIgnoreCase(sb);
    }

    /**
     * Reverses a string
     * @param String - the string to reverse
     * @return String - the reversed string
     */

    public static String reverseString(String str) {
        String sb = new String();
		int l = str.length();

        for (int i = 1; i <= l; i++) { 
            sb += str.charAt(l - i);
        }
        return sb;
    }

}
