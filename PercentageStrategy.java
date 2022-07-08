public class PercentageStrategy implements IPricingStrategy {
    private int discountPercentage;

    public PercentageStrategy(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getSubTotal(SaleLineItem item) {
        return (1 - discountPercentage / 100.0) * item.getCopies() * item.getProdSpec().getPrice();
    }
}
