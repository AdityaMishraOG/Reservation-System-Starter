package flight.reservation.payment;

public class PayPalPaymentStrategy implements PaymentStrategy {

    private final String email;
    private final String password;

    public PayPalPaymentStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) throws IllegalStateException {
        if (!Paypal.DATA_BASE.containsKey(password) || !email.equals(Paypal.DATA_BASE.get(password))) {
            throw new IllegalStateException("PayPal credentials invalid");
        }
        System.out.println("Paying " + amount + " using PayPal.");
        return true;
    }
}
