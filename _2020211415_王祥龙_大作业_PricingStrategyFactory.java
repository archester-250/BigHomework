public class _2020211415_王祥龙_大作业_PricingStrategyFactory {
    private static  _2020211415_王祥龙_大作业_PricingStrategyFactory instance = new _2020211415_王祥龙_大作业_PricingStrategyFactory();

    private static double discountPerBook = 1.0;
    private static int discountPercentage_1 = 7;
    private static int discountPercentage_2 = 3;

    private _2020211415_王祥龙_大作业_PricingStrategyFactory() {
    }

    public static _2020211415_王祥龙_大作业_PricingStrategyFactory getInstance() {
        return instance;
    }

    public _2020211415_王祥龙_大作业_IPricingStrategy getPricingStrategy(int type)
    {
        switch (type)
        {
            case 0://教材类图书
                return new _2020211415_王祥龙_大作业_FlatRateStrategy(discountPerBook);
            case 1://连环画类
                return new _2020211415_王祥龙_大作业_PercentageStrategy(discountPercentage_1);
            case 2://非教材类的计算机图书
                return new _2020211415_王祥龙_大作业_PercentageStrategy(discountPercentage_2);
            default:
                return new _2020211415_王祥龙_大作业_NoDiscountStrategy();
        }
    }

    public static double getDiscountPerBook() {
        return discountPerBook;
    }

    public static void setDiscountPerBook(double discountPerBook) {
        _2020211415_王祥龙_大作业_PricingStrategyFactory.discountPerBook = discountPerBook;
    }

    public static int getDiscountPercentage_1() {
        return discountPercentage_1;
    }

    public static void setDiscountPercentage_1(int discountPercentage_1) {
        _2020211415_王祥龙_大作业_PricingStrategyFactory.discountPercentage_1 = discountPercentage_1;
    }

    public static int getDiscountPercentage_2() {
        return discountPercentage_2;
    }

    public static void setDiscountPercentage_2(int discountPercentage_2) {
        _2020211415_王祥龙_大作业_PricingStrategyFactory.discountPercentage_2 = discountPercentage_2;
    }
}
