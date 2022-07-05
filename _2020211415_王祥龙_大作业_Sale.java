import java.util.ArrayList;

public class _2020211415_王祥龙_大作业_Sale {
    private ArrayList<_2020211415_王祥龙_大作业_SaleLineItem> items;

    public _2020211415_王祥龙_大作业_Sale(ArrayList<_2020211415_王祥龙_大作业_SaleLineItem> items) {
        this.items = items;
    }

    public double getTotal()
    {
        double total = 0.0;
        for(int i = 0; i < items.size(); i++)
        {
            total += items.get(i).getSubTotal();
        }
        return total;
    }
}
