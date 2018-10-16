package hotel.test;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import java.util.Date;
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
public class BookingTest {
    
    public BookingTest() {
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
    public void testAddServiceCharge() {
        System.out.println("addServiceCharge");
        
        Hotel hotel = new Hotel();
    	hotel.addRoom(RoomType.SINGLE, 101);
    	hotel.addRoom(RoomType.DOUBLE, 201);
    	hotel.addRoom(RoomType.TWIN_SHARE, 301);
        
        Guest guest = new Guest("Dismika", "Keilor east", 2);
	    CreditCard card = new CreditCard(CreditCardType.VISA, 1, 1);
		
    	Room room = hotel.findAvailableRoom(RoomType.TWIN_SHARE, new Date(2018, 12, 19), 5);
    	long confNo = hotel.book(room, guest, new Date(2018, 12, 19), 1, 1, card);
    	Booking booking = hotel.findBookingByConfirmationNumber(confNo);
    	hotel.checkin(confNo);
        double cost = 5.0;
        
        booking.addServiceCharge(ServiceType.ROOM_SERVICE, cost);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(booking.getCharges().get(0).getCost(), 5.0);
    }

}