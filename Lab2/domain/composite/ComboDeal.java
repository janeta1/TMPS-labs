package domain.composite;

import java.util.ArrayList;
import java.util.List;

public class ComboDeal implements MenuComponent {
    private String name;
    private double discount;
    private List<MenuComponent> items = new ArrayList<>();
    
    public ComboDeal(String name, double discount) {
        this.name = name;
        this.discount = discount;
    }

    public void addItem(MenuComponent item) {
        items.add(item);
    }

    public void removeItem(MenuComponent item) {
        items.remove(item);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (MenuComponent item : items) {
            total += item.getPrice();
        }
        return total * (1 - discount);
    }

    @Override
    public void display() {
        System.out.println("=== COMBO: " + name + "  (Discount: " + (int)(discount * 100) + "%) ===");
        for (MenuComponent item : items) {
            System.out.print("    - ");
            item.display();
        }
        System.out.println("   -> Combo Price: $" + String.format("%.2f", getPrice()));
    }

}
