package domain.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.composite.MenuComponent;

public class Order {
    private List<MenuComponent> items;
    private int orderId;
    private static Random random = new Random();
    private boolean confirmed;

    public Order() {
        this.items = new ArrayList<>();
        this.orderId = random.nextInt(1000);
        System.out.println("New order created with ID: " + this.orderId);
    }

    public int addItem(MenuComponent item) {
        items.add(item);
        System.out.println("Added to order: " + item.getName() + " - $" + item.getPrice());
        return items.size() - 1;
    }

    public MenuComponent removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            System.out.println("Invalid item index.");
            return null;
        }
        MenuComponent removedItem = items.remove(index);
        System.out.println("Removed from order: " + removedItem.getName() + " - $" + removedItem.getPrice());
        return removedItem;
    }

    public MenuComponent removeLastItem() {
        if (items.isEmpty()) {
            System.out.println("Order is empty. No items to remove.");
            return null;
        }
        MenuComponent removedItem = items.remove(items.size() - 1);
        System.out.println("Removed from order: " + removedItem.getName() + " - $" + removedItem.getPrice());
        return removedItem;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (MenuComponent item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void confirm() {
        this.confirmed = true;
    }

    public void unconfirm() {
        this.confirmed = false;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        if (items.isEmpty()) {
            System.out.println("Order is empty.");
            return;
        }
        System.out.println("Items in Order:");
        for (int i = 0; i < items.size(); i++) {
            MenuComponent item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
        System.out.printf("Total Price: $%.2f%n", getTotalPrice());
    }
}
