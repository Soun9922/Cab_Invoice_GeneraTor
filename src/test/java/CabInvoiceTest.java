import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CabInvoiceTest {

    CabInvoiceService cabInvoiceService = null;

    @BeforeEach
    public void setUp(){
        cabInvoiceService = new CabInvoiceService();
    }


    @Test
    void givenDistanceAndTimeShouldReturnTotalFare(){
        double fare = cabInvoiceService.calculateFare(10,3);
        Assertions.assertEquals(103.0, fare);
    }

    @Test
    void givenLessDistanceAndTimeShouldReturnTrue(){
        double fare = cabInvoiceService.calculateFare(0.1,1);
        Assertions.assertEquals(5,fare);
    }

    @Test
    void givenMultipleRidesShouldReturnInvoiceSummary(){
        Ride[] rides = {new Ride(8,10),
                        new Ride(11,9)};
        InvoiceSummary invoiceSummary = cabInvoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,209.0);
        Assertions.assertEquals(expectedInvoiceSummary,invoiceSummary);
    }

    @Test
    void givenUserIdAndRidesShouldReturnInvoiceSummary(){
        String user1 = "bhairav";
        Ride[] rides1 = {new Ride(4,8),
                        new Ride(7,15)};
        cabInvoiceService.addRides(user1,rides1);
        String user2 = "jimmy";
        Ride[] rides2 = {new Ride(15,20),
                        new Ride(4.6,8)};
        cabInvoiceService.addRides(user2,rides2);
        InvoiceSummary invoiceSummary1 = cabInvoiceService.getInvoiceSummary(user1);
        InvoiceSummary expectedInvoiceSummary1 = new InvoiceSummary(2,133);
        InvoiceSummary invoiceSummary2 = cabInvoiceService.getInvoiceSummary(user2);
        InvoiceSummary expectedInvoiceSummary2 = new InvoiceSummary(2,224);
        Assertions.assertEquals(expectedInvoiceSummary1,invoiceSummary1);
        Assertions.assertEquals(expectedInvoiceSummary2,invoiceSummary2);
    }

    @Test
    void givenNormalAndPremiumRidesShouldReturnInvoiceSummary(){
        String user1 = "bhairava";
        Ride[] rides1 = {new Ride(RideCategory.PREMIUM ,18,9),
                new Ride(RideCategory.NORMAL, 7,15)};
        cabInvoiceService.addRides(user1,rides1);
        String user2 = "jimmy";
        Ride[] rides2 = {new Ride(RideCategory.PREMIUM, 15,20),
                new Ride(RideCategory.NORMAL, 4.6,8)};
        cabInvoiceService.addRides(user2,rides2);
        InvoiceSummary invoiceSummary1 = cabInvoiceService.getInvoiceSummary(user1);
        InvoiceSummary expectedInvoiceSummary1 = new InvoiceSummary(2,274);
        Assertions.assertEquals(expectedInvoiceSummary1,invoiceSummary1);
    }
}
