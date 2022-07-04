public class _2020211415_王祥龙_大作业_SaleLineItem {
    private int copies;
    private _2020211415_王祥龙_大作业_ProductSpecification prodSpec;
    private _2020211415_王祥龙_大作业_IPricingStrategy strategy;

    public double getSubTotal()
    {
        return strategy.getSubTotal(this);
    }

    public int getCopies() {
        return copies;
    }

    public _2020211415_王祥龙_大作业_ProductSpecification getProdSpec() {
        return prodSpec;
    }
}
