import java.util.ArrayList;

public class Test {
    public static double Test()
    {
        ArrayList items = new ArrayList<SaleLineItem>();
        String[] test_isbn = new String[]{"9787111145189", "9787505380004", "9787508353937", "9787532471638", "9787504849649"};
        double[] test_price = new double[]{18, 34, 58, 30, 20};
        String[] test_title = new String[]{"《UML与模式应用》", "《Java与模式》", "《HeadFirst 设计模式》", "《爱丽丝历险记》", "《煲汤大全》"};
        int[] type = new int[]{0, 2, 2, 1, 3};
        int[] test_copies = new int[]{2, 2, 1, 3, 1};
        for(int i = 0; i < 5; i++)
        {
            ProductSpecification productSpecification = new ProductSpecification(test_isbn[i], test_price[i], test_title[i], type[i]);
            PricingStrategyFactory pricingStrategyFactory = PricingStrategyFactory.getInstance();
            IPricingStrategy iPricingStrategy = pricingStrategyFactory.getPricingStrategy(type[i]);
            items.add(new SaleLineItem(test_copies[i], productSpecification, iPricingStrategy));
        }
        Sale sale = new Sale(items);
        return sale.getTotal();
    }
}
