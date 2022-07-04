public class _2020211415_王祥龙_大作业_FlatRateStrategy implements _2020211415_王祥龙_大作业_IPricingStrategy{
    private double discountPerBook;

    public _2020211415_王祥龙_大作业_FlatRateStrategy(double discountPerBook) {
        this.discountPerBook = discountPerBook;
    }

    @Override
    public double getSubTotal(_2020211415_王祥龙_大作业_SaleLineItem item) {
        return item.getCopies() * (item.getProdSpec().getPrice() - discountPerBook);
    }
}
