import static org.junit.Assert.*;
import org.junit.Test;

public class HorribleSteve {
    @Test
    public void testIssameNumber(){
        assertTrue(Flik.isSameNumber(1,1));
        assertTrue(Flik.isSameNumber(64,64));
        assertTrue(Flik.isSameNumber(128,128));

    }
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
