import java.util.HashMap;
import java.util.Map;

public class CabInvoiceService {

    static final int COST_PER_KM = 10;
    static final int COST_PER_MINUTE = 1;
    static final int MINIMUM_FARE = 5;
    private final Map<String,Ride[]> rideRepository = new HashMap<>();
    public static double calculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MINUTE;
        if (totalFare <= MINIMUM_FARE){
            return MINIMUM_FARE;
        }
        return totalFare;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride: rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.put(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId){
        Ride[] rides = rideRepository.get(userId);
        return new CabInvoiceService().calculateFare(rides);
    }
}
