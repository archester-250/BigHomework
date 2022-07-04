public class _2020211415_王祥龙_大作业_NoDiscountStrategy implements _2020211415_王祥龙_大作业_IPricingStrategy{

    @Override
    public double getSubTotal(_2020211415_王祥龙_大作业_SaleLineItem item) {
        return item.getCopies() * item.getProdSpec().getPrice();
    }
}
