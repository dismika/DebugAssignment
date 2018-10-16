package test;


import hotel.checkout.CheckoutCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceType;
import hotel.service.RecordServiceCTL;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sandun
 */
public class UnitTest {

    Booking booking;

    public UnitTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void checkoutTester() {

        Hotel hotel = new Hotel();
        Room room = new Room(201, RoomType.SINGLE); 
        hotel.addRoom(RoomType.SINGLE, 201);
        Guest guest = hotel.registerGuest("Dismika", "keilor", 0452547372);
        CreditCard creditCard = new CreditCard(CreditCardType.MASTERCARD, 1, 1); 
        hotel.findAvailableRoom(RoomType.SINGLE, new Date(2018, 12, 19), 5);
        Long confirmationNumber = hotel.book(room, guest, new Date(2018, 12, 19), 5, 1, creditCard); 
        hotel.checkin(confirmationNumber);
        int roomId = 201;

        CheckoutCTL cOutCTRL = new CheckoutCTL(hotel);
        cOutCTRL.run();
        cOutCTRL.roomIdEntered(roomId);
        cOutCTRL.chargesAccepted(true);
        cOutCTRL.creditDetailsEntered(CreditCardType.VISA, 4217, 415);

        RecordServiceCTL recordService = new RecordServiceCTL(hotel); 
        recordService.roomNumberEntered(roomId);
        recordService.serviceDetailsEntered(ServiceType.BAR_FRIDGE, 150.0);

        booking = hotel.findActiveBookingByRoomId(roomId);
        boolean roomAvailable = true;
        if (booking != null) {
            roomAvailable = false;
        }
        assertTrue("true", roomAvailable);
    }

    @Test
    public void serviceChargeTester() {
        System.out.println("addServiceCharge");

        Hotel hotel = new Hotel();
        hotel.addRoom(RoomType.SINGLE, 101);
        hotel.addRoom(RoomType.DOUBLE, 201);
        hotel.addRoom(RoomType.TWIN_SHARE, 301);
        double cost = 100.00;
        Guest guest = new Guest("Sandun", "St albans", 1);
        CreditCard card = new CreditCard(CreditCardType.VISA, 1, 1);
        Room room = hotel.findAvailableRoom(RoomType.TWIN_SHARE, new Date(2018, 12, 05), 1);
        long confNo = hotel.book(room, guest, new Date(2018, 11, 05), 1, 2, card);
        Booking booking = hotel.findBookingByConfirmationNumber(confNo);
        hotel.checkin(confNo);
        booking.addServiceCharge(ServiceType.ROOM_SERVICE, cost);
        assertEquals(booking.getCharges().get(0).getCost(), 100.0);
    }
}