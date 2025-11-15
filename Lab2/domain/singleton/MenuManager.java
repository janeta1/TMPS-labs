package domain.singleton;
import java.util.ArrayList;
import java.util.List;
import domain.composite.*;

public class MenuManager {
    private static MenuManager instance;
    private List<MenuComponent> menuItems;

    private MenuManager() {
        this.menuItems = new ArrayList<>();
    }
    
    public static MenuManager getInstance() {
        if (MenuManager.instance == null) {
            instance = new MenuManager();
        }
        return MenuManager.instance;
    }
    
    public void addMenuItem(MenuComponent item) {
        menuItems.add(item);
    }

    public void removeMenuItem(MenuComponent item) {
        menuItems.remove(item);
    }
    
    public void displayMenu() {
        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                  PIZZA RESTAURANT MENU                    |");
        System.out.println("+------------------------------------------------------------+");

        int idx = 1;
        for (MenuComponent item : menuItems) {
            System.out.printf("\n%2d.   ", idx++);
            item.display();
        }

        System.out.println("+------------------------------------------------------------+");
        System.out.println("|       Thank you! Come back for more delicious pizza!       |");
        System.out.println("+------------------------------------------------------------+");
    }
}


