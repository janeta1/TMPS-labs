package domain.singleton;
import java.util.ArrayList;
import java.util.List;
import domain.models.*;

public class MenuManager {
    private static MenuManager instance;
    private List<Pizza> menuItems;

    private MenuManager() {
        this.menuItems = new ArrayList<>();
    }
    
    public static MenuManager getInstance() {
        if (MenuManager.instance == null) {
            instance = new MenuManager();
        }
        return MenuManager.instance;
    }
    
    public void addMenuItem(Pizza pizza) {
        menuItems.add(pizza);
    }

    public void removeMenuItem(Pizza pizza) {
        menuItems.remove(pizza);
    }
    
    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  PIZZA RESTAURANT MENU                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        for (int i = 0; i < menuItems.size(); i++) {
            Pizza pizza = menuItems.get(i);
            if (pizza instanceof CustomPizza) {
                CustomPizza customPizza = (CustomPizza) pizza;
                System.out.println("\n" + (i + 1) + ". " + customPizza.getName() + " ($" + String.format("%.2f", customPizza.getPrice()) + ")");
                System.out.println("   Size: " + customPizza.getSize());
                System.out.println("   Dough: " + customPizza.getDough());
                System.out.println("   Sauce: " + customPizza.getSauce());

                if (customPizza.getToppings() != null && !customPizza.getToppings().isEmpty()) {
                    System.out.print("   Toppings: ");
                    for (int j = 0; j < customPizza.getToppings().size(); j++) {
                        System.out.print(customPizza.getToppings().get(j));
                        if (j < customPizza.getToppings().size() - 1) System.out.print(", ");
                    }
                    System.out.println();
                } else {
                    System.out.println("   Toppings: None");
                }
            } else {
                System.out.println("\n" + (i + 1) + ". " + pizza.getName() + " ($" + String.format("%.2f", pizza.getPrice()) + ")");
            }
        }
    }
}
