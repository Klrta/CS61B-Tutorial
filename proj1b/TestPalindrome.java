import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);

    }
    @Test
    //test isPalindrome
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindrome("abba"));
        //assertFalse(palindrome.isPalindrome("ala"));

    }

    @Test
    public void testIsPalindromeWithComparator() {
        // Test with the OffByOne comparator
        CharacterComparator offByOne = new OffByOne();

        // Test for standard palindromes
        assertTrue(palindrome.isPalindrome("abba"));
        assertFalse(palindrome.isPalindrome("abc"));

        // Test for off-by-one palindromes
        assertTrue(palindrome.isPalindrome("flake", offByOne));  // Off-by-one palindrome
        assertFalse(palindrome.isPalindrome("abba", offByOne));  // Not an off-by-one palindrome
        assertTrue(palindrome.isPalindrome("ab", offByOne));     // 'a' and 'b' are off by one
        assertFalse(palindrome.isPalindrome("abc", offByOne));   // 'a' and 'c' are not off by one

        // Corner cases
        assertTrue(palindrome.isPalindrome("", offByOne));       // Empty string is a palindrome
        assertTrue(palindrome.isPalindrome("a", offByOne));      // Single character is a palindrome
    }


}
