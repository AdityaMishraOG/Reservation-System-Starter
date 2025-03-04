package flight.reservation.payment;

public interface PaymentStrategy {
    /**
     * @param amount total price to pay.
     * @return true if payment was successful, otherwise throw an IllegalStateException
     */
    boolean pay(double amount) throws IllegalStateException;
}
