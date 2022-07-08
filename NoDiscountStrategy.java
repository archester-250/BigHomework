public class NoDiscountStrategy implements IPricingStrategy {

    @Override
    public double getSubTotal(SaleLineItem item) {
        return item.getCopies() * item.getProdSpec().getPrice();
    }
}
