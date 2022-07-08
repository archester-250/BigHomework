import java.util.ArrayList;

public class Sale {
    private ArrayList<SaleLineItem> items;

    public Sale(ArrayList<SaleLineItem> items) {
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
