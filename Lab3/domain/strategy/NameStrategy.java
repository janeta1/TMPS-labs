package domain.strategy;
import java.util.List;
import java.util.ArrayList;
import domain.composite.*;

public class NameStrategy implements Strategy {
    @Override
    public List<MenuComponent> sort(List<MenuComponent> items) {
        List<MenuComponent> sortedItems = new ArrayList<>(items);
        sortedItems.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        return sortedItems;
    }
}
