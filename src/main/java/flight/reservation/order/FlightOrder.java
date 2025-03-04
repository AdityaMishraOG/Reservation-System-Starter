package flight.reservation.order;

import java.util.Arrays;
import java.util.List;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.payment.CreditCard;
import flight.reservation.payment.PaymentStrategy;


public class FlightOrder extends Order {
    private final List<ScheduledFlight> flights;
    static List<String> noFlyList = Arrays.asList("Peter", "Johannes");

    // NEW: We store the current PaymentStrategy we will use
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public FlightOrder(List<ScheduledFlight> flights) {
        this.flights = flights;
    }

    public static List<String> getNoFlyList() {
        return noFlyList;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    private boolean isOrderValid(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
        boolean valid = true;
        valid = valid && !noFlyList.contains(customer.getName());
        valid = valid && passengerNames.stream().noneMatch(passenger -> noFlyList.contains(passenger));
        valid = valid && flights.stream().allMatch(scheduledFlight -> {
            try {
                return scheduledFlight.getAvailableCapacity() >= passengerNames.size();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        });
        return valid;
    }

    // /**
    //  * Optionally keep this method if your test calls it directly.
    //  * Internally, it sets the PaymentStrategy, then calls processOrder().
    //  */
    // public boolean processOrderWithCreditCardDetail(String number, Date expirationDate, String cvv)
    //         throws IllegalStateException {
    //     if (isClosed()) {
    //         return true;
    //     }
    //     // Create and validate card
    //     CreditCard creditCard = new CreditCard(number, expirationDate, cvv);
    //     if (!cardIsPresentAndValid(creditCard)) {
    //         throw new IllegalStateException("Payment information is not set or not valid.");
    //     }
    //     // set the strategy, then do the pay
    //     setPaymentStrategy(new CreditCardPaymentStrategy(creditCard));
    //     return processOrder();
    // }

    // /**
    //  * Optionally keep this method if your test calls it directly.
    //  */
    // public boolean processOrderWithCreditCard(CreditCard creditCard) throws IllegalStateException {
    //     if (isClosed()) {
    //         return true;
    //     }
    //     // validate payment information
    //     if (!cardIsPresentAndValid(creditCard)) {
    //         throw new IllegalStateException("Payment information is not set or not valid.");
    //     }
    //     setPaymentStrategy(new CreditCardPaymentStrategy(creditCard));
    //     return processOrder();
    // }

    // /**
    //  * Optionally keep this method if your test calls it directly.
    //  */
    // public boolean processOrderWithPayPal(String email, String password) throws IllegalStateException {
    //     if (isClosed()) {
    //         return true;
    //     }
    //     // validate payment information
    //     if (email == null || password == null || !email.equals(Paypal.DATA_BASE.get(password))) {
    //         throw new IllegalStateException("Payment information is not set or not valid.");
    //     }
    //     setPaymentStrategy(new PayPalPaymentStrategy(email, password));
    //     return processOrder();
    // }

    /**
     * Unified method to process the order using whichever
     * PaymentStrategy is currently set.
     */
    public boolean processOrder() throws IllegalStateException {
        if (isClosed()) {
            // Payment is already proceeded
            return true;
        }
        if (paymentStrategy == null) {
            throw new IllegalStateException("No payment strategy set.");
        }
        // attempt the pay
        boolean paid = paymentStrategy.pay(this.getPrice());
        if (paid) {
            this.setClosed();
        }
        return paid;
    }

    private boolean cardIsPresentAndValid(CreditCard card) {
        return card != null && card.isValid();
    }
}
