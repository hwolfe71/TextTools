/**
 * TexttoolsInterface.java
 * @author Herb Wolfe Jr <hwolfe71@gmail.com>
 * 
 * This is an interface for several of the text processing projects, or
 * variations of them, from 
 * http://www.dreamincode.net/forums/topic/78802-martyr2s-mega-project-ideas-list/
 * including a string reversal, a palindrome checker, letter and word
 * counters. 
 */

public interface TextToolsInterface {
    public int[] getLetterCount(String str);
    public int getWordCount(String str);
    public boolean isPalindrome(String str);
    public String reverseString(String str);
}
