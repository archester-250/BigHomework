public class _2020211415_王祥龙_大作业_PercentageStrategy implements _2020211415_王祥龙_大作业_IPricingStrategy{
    private int discountPercentage;

    public _2020211415_王祥龙_大作业_PercentageStrategy(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getSubTotal(_2020211415_王祥龙_大作业_SaleLineItem item) {
        return (1 - discountPercentage / 100.0) * item.getCopies() * item.getProdSpec().getPrice();
    }
}
