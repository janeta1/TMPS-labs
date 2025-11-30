package domain.strategy;
import java.util.ArrayList;
import java.util.List;
import domain.composite.MenuComponent;

public class PriceStrategy implements Strategy {
    @Override
    public List<MenuComponent> sort(List<MenuComponent> items) {
        List<MenuComponent> sortedItems = new ArrayList<>(items);
        sortedItems.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        return sortedItems;
    }

}
