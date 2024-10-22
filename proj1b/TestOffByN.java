import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy5 = new OffByN(5);
    static CharacterComparator offBy4 = new OffByN(4);
    static CharacterComparator offBy3 = new OffByN(3);
    static CharacterComparator offBy2 = new OffByN(2);
    static CharacterComparator offBy1 = new OffByN(1);

    @Test
    // Test for OffBy5
    public void testOffBy5() {
        // Positive cases (expect true)
        assertTrue(offBy5.equalChars('a', 'f'));  // true
        assertTrue(offBy5.equalChars('f', 'a'));  // true
        assertTrue(offBy5.equalChars('b', 'g'));  // true
        assertTrue(offBy5.equalChars('c', 'h'));  // true

        // Negative cases (expect false)
        assertFalse(offBy5.equalChars('a', 'g'));  // false (diff is 6)
        assertFalse(offBy5.equalChars('f', 'h'));  // false (diff is 2)
        assertFalse(offBy5.equalChars('c', 'i'));  // false (diff is 6)
        assertFalse(offBy5.equalChars('a', 'a'));  // false (same character)
    }

    @Test
    // Test for OffBy1 (General edge case tests)
    public void testOffBy1() {
        // Positive cases (expect true)
        assertTrue(offBy1.equalChars('a', 'b'));  // true
        assertTrue(offBy1.equalChars('r', 'q'));  // true
        assertTrue(offBy1.equalChars('&', '%'));  // true (non-alphabetic chars)

        // Negative cases (expect false)
        assertFalse(offBy1.equalChars('a', 'c'));  // false
        assertFalse(offBy1.equalChars('z', 'x'));  // false
        assertFalse(offBy1.equalChars('a', 'a'));  // false (same character)
        assertFalse(offBy1.equalChars('A', 'a'));  // false (case-sensitive)
    }

    @Test
    // Test for OffByN with N=2,3,4 (Multiple cases)
    public void testOffByMultiple() {
        // OffBy2
        assertTrue(offBy2.equalChars('a', 'c'));  // true
        assertTrue(offBy2.equalChars('d', 'b'));  // true
        assertFalse(offBy2.equalChars('a', 'b')); // false

        // OffBy3
        assertTrue(offBy3.equalChars('a', 'd'));  // true
        assertTrue(offBy3.equalChars('d', 'a'));  // true
        assertFalse(offBy3.equalChars('a', 'c')); // false

        // OffBy4
        assertTrue(offBy4.equalChars('a', 'e'));  // true
        assertTrue(offBy4.equalChars('e', 'a'));  // true
        assertFalse(offBy4.equalChars('a', 'd')); // false
    }
}
