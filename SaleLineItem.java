public class SaleLineItem {
    private int copies;
    private ProductSpecification prodSpec;
    private IPricingStrategy strategy;

    public SaleLineItem(int copies, ProductSpecification prodSpec, IPricingStrategy strategy) {
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

    public void setStrategy(IPricingStrategy strategy) {
        this.strategy = strategy;
    }

    public ProductSpecification getProdSpec() {
        return prodSpec;
    }
}
