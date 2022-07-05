import java.util.ArrayList;

public class _2020211415_王祥龙_大作业_Test {
    public static double Test()
    {
        ArrayList items = new ArrayList<_2020211415_王祥龙_大作业_SaleLineItem>();
        String[] test_isbn = new String[]{"9787111145189", "9787505380004", "9787508353937", "9787532471638", "9787504849649"};
        double[] test_price = new double[]{18, 34, 58, 30, 20};
        String[] test_title = new String[]{"《UML与模式应用》", "《Java与模式》", "《HeadFirst 设计模式》", "《爱丽丝历险记》", "《煲汤大全》"};
        int[] type = new int[]{0, 2, 2, 1, 3};
        int[] test_copies = new int[]{2, 2, 1, 3, 1};
        for(int i = 0; i < 5; i++)
        {
            _2020211415_王祥龙_大作业_ProductSpecification productSpecification = new _2020211415_王祥龙_大作业_ProductSpecification(test_isbn[i], test_price[i], test_title[i], type[i]);
            _2020211415_王祥龙_大作业_IPricingStrategy iPricingStrategy = _2020211415_王祥龙_大作业_PricingStrategyFactory.getInstance().getPricingStrategy(type[i]);
            items.add(new _2020211415_王祥龙_大作业_SaleLineItem(test_copies[i], productSpecification, iPricingStrategy));
        }
        _2020211415_王祥龙_大作业_Sale sale = new _2020211415_王祥龙_大作业_Sale(items);
        return sale.getTotal();
    }
}
