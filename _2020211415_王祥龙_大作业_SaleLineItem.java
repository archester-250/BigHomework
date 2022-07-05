public class _2020211415_王祥龙_大作业_SaleLineItem {
    private int copies;
    private _2020211415_王祥龙_大作业_ProductSpecification prodSpec;
    private _2020211415_王祥龙_大作业_IPricingStrategy strategy;

    public _2020211415_王祥龙_大作业_SaleLineItem(int copies, _2020211415_王祥龙_大作业_ProductSpecification prodSpec, _2020211415_王祥龙_大作业_IPricingStrategy strategy) {
        this.copies = copies;
        this.prodSpec = prodSpec;
        this.strategy = strategy;
    }

    public double getSubTotal()
    {
        return strategy.getSubTotal(this);
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setStrategy(_2020211415_王祥龙_大作业_IPricingStrategy strategy) {
        this.strategy = strategy;
    }

    public _2020211415_王祥龙_大作业_ProductSpecification getProdSpec() {
        return prodSpec;
    }
}
