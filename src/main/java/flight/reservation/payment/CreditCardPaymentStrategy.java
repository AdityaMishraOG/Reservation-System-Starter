package flight.reservation.payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {

    private final CreditCard creditCard;

    public CreditCardPaymentStrategy(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean pay(double amount) throws IllegalStateException {
        // 1) validate
        if (creditCard == null || !creditCard.isValid()) {
            throw new IllegalStateException("Credit card is not valid.");
        }

        double remaining = creditCard.getAmount() - amount;
        if (remaining < 0) {
            // Not enough balance
            throw new IllegalStateException("Card limit reached");
        }
        // 2) execute payment
        System.out.println("Paying " + amount + " using Credit Card.");
        creditCard.setAmount(remaining);
        return true;
    }
}
