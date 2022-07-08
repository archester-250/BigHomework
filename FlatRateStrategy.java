public class FlatRateStrategy implements IPricingStrategy {
    private double discountPerBook;

    public FlatRateStrategy(double discountPerBook) {
        this.discountPerBook = discountPerBook;
    }

    @Override
    public double getSubTotal(SaleLineItem item) {
        return item.getCopies() * (item.getProdSpec().getPrice() - discountPerBook);
    }
}
