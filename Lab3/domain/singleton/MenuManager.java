package domain.singleton;
import java.util.ArrayList;
import java.util.List;
import domain.composite.*;
import domain.observer.Observer;

public class MenuManager {
    private static MenuManager instance;
    private List<MenuComponent> menuItems;
    private List<Observer> observers = new ArrayList<>();

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
        notifyObservers("New menu item added: " + item.getName());
    }

    public int size() {
        return menuItems.size();
    }

    public MenuComponent getItem(int index) {
        return menuItems.get(index);
    }

    public void removeMenuItem(MenuComponent item) {
        menuItems.remove(item);
        notifyObservers("Menu item removed: " + item.getName());
    }

    public List<MenuComponent> getMenuItems() {
        return menuItems;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
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


