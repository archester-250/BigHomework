public class _2020211415_王祥龙_大作业_PricingStrategyFactory {
    private static  _2020211415_王祥龙_大作业_PricingStrategyFactory instance = new _2020211415_王祥龙_大作业_PricingStrategyFactory();

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
                return new _2020211415_王祥龙_大作业_FlatRateStrategy(1.0);
            case 1://连环画类
                return new _2020211415_王祥龙_大作业_PercentageStrategy(7);
            case 2://非教材类的计算机图书
                return new _2020211415_王祥龙_大作业_PercentageStrategy(3);
            default:
                return new _2020211415_王祥龙_大作业_NoDiscountStrategy();
        }
    }
}
