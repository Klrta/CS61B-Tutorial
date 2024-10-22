import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    // Your tests go here.
    public void testequalChars(){
        char a = 'a';
        char b = 'b';
        assertTrue(offByOne.equalChars(a,b));//expect pass
    }


}
