package hotel.checkout;

import hotel.credit.CreditCardType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dismika
 */
public class CheckoutCTLTest {
    
    public CheckoutCTLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   
    @Test
    public void testRoomIdEntered() {
        System.out.println("roomIdEntered");
        int roomId = 0;
        CheckoutCTL instance = null;
        instance.roomIdEntered(roomId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case ");
    }

    
    
}