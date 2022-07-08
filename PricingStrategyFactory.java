public class PricingStrategyFactory {
    private static PricingStrategyFactory instance = new PricingStrategyFactory();

    private static double discountPerBook = 1.0;
    private static int discountPercentage_1 = 7;
    private static int discountPercentage_2 = 3;

    private PricingStrategyFactory() {
    }

    public static synchronized PricingStrategyFactory getInstance() {
        return instance;
    }

    public IPricingStrategy getPricingStrategy(int type)
    {
        switch (type)
        {
            case 0://教材类图书
                return new FlatRateStrategy(discountPerBook);
            case 1://连环画类
                return new PercentageStrategy(discountPercentage_1);
            case 2://非教材类的计算机图书
                return new PercentageStrategy(discountPercentage_2);
            default:
                return new NoDiscountStrategy();
        }
    }

    public static double getDiscountPerBook() {
        return discountPerBook;
    }

    public static void setDiscountPerBook(double discountPerBook) {
        PricingStrategyFactory.discountPerBook = discountPerBook;
    }

    public static int getDiscountPercentage_1() {
        return discountPercentage_1;
    }

    public static void setDiscountPercentage_1(int discountPercentage_1) {
        PricingStrategyFactory.discountPercentage_1 = discountPercentage_1;
    }

    public static int getDiscountPercentage_2() {
        return discountPercentage_2;
    }

    public static void setDiscountPercentage_2(int discountPercentage_2) {
        PricingStrategyFactory.discountPercentage_2 = discountPercentage_2;
    }
}
