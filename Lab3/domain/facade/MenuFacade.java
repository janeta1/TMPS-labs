package domain.facade;
import domain.builder.*;
import domain.composite.*;
import domain.models.*;
import domain.observer.AdminLogger;
import domain.singleton.*;
import domain.strategy.NameStrategy;
import domain.strategy.PriceStrategy;
import domain.strategy.Strategy;
import domain.adapter.*;
import domain.factorymethod.*;
import domain.decorator.*;
import domain.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuFacade {
    private MenuManager menu;
    private PizzaDirector director;
    private IPizzaBuilder builder;
    private Strategy strategy;
    private List<MenuComponent> sortedMenuItems;
    private AdminLogger adminLogger = new AdminLogger();
    private Order order;
    private final CommandInvoker commandInvoker = new CommandInvoker();
    private final Scanner input = new Scanner(System.in);
    
    public MenuFacade() {
        menu = MenuManager.getInstance();
        builder = new PizzaBuilder();
        director = new PizzaDirector(builder);
        menu.subscribe(adminLogger);
    }

    public void startMainMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- PIZZA RESTAURANT MAIN MENU ---");
            System.out.println("1. Customer Mode");
            System.out.println("2. Admin Mode");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    startCustomerMode();
                    break;
                case 2:
                    startAdminMode();
                    break;
                case 0:
                    System.out.println("\nExiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void startCustomerMode() {
        System.out.println("\nEntering Customer Mode...");

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== CUSTOMER MODE ===");
            System.out.println("1. Show menu");
            System.out.println("2. Add item to order");
            System.out.println("3. Remove item from order");
            System.out.println("4. Undo last action");
            System.out.println("5. Confirm order");
            System.out.println("6. Show my order");
            System.out.println("7. Sort menu");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch (choice) {
                case 1:
                    showMenu();
                    break;
                case 2:
                    addItemToOrder();
                    break;
                case 3:
                    removeItemFromOrder();
                    break;
                case 4:
                    undoLastAction();
                    break;
                case 5:
                    confirmOrder();
                    break;
                case 6:
                    showOrder();
                    break;
                case 7:
                    System.out.println("Sorting menu...");
                    sortMenuUI();
                    break;
                case 0:
                    System.out.println("\nExiting Customer Mode...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void startAdminMode() {
        System.out.println("\nEntering Admin Mode...");

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== ADMIN MODE ===");
            System.out.println("1. Show menu");
            System.out.println("2. Add menu item");
            System.out.println("3. Remove menu item");
            System.out.println("4. Sort menu");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    showMenu();
                    break;
                case 2:
                    addAdminMenuItem();
                    break;
                case 3:
                    removeAdminMenuItem();
                    break;
                case 4:
                    sortMenuUI();
                    break;
                case 0:
                    System.out.println("\nExiting Admin Mode...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // helpers for customer
    public void createOrder() {
        if (order == null) {
            order = new Order();
        }
    }

    public void showOrder() {
        if (order != null) {
            order.displayOrder();
        } else {
            System.out.println("No current order.");
        }
    }

    public void addItemToOrder() {
        System.out.println("Enter item number to add: ");
        createOrder();
        try {
            int index = input.nextInt();
            System.out.println("Adding item " + index + " to order.");
            MenuComponent item = menu.getItem(index - 1);
            commandInvoker.executeCommand(new AddItemCommand(order, item));
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid item number.");
            input.nextLine();
        }
    }

    public void removeItemFromOrder() {
        createOrder();
        System.out.println("Enter item number to remove: ");
        try {
            int index = input.nextInt();
            commandInvoker.executeCommand(new RemoveItemCommand(order, index - 1));
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid item number.");
            input.nextLine();
        }
    }

    public void undoLastAction() {
        commandInvoker.undoLastCommand();
    }

    public void confirmOrder() {
        if (order == null || order.isEmpty()) {
            System.out.println("No items to confirm.");
            return;
        }

        commandInvoker.executeCommand(new ConfirmOrderCommand(order));
    }


    // helpers for admin
    public void removeAdminMenuItem() {
        System.out.println("Enter item number to remove: ");
        try {
            int index = input.nextInt();
            MenuComponent item = menu.getItem(index - 1);
            menu.removeMenuItem(item);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid item number.");
            input.nextLine();
        }
    }

    public void addAdminMenuItem() {
        System.out.println("\n=== ADD MENU ITEM ===");
        System.out.println("1. Basic Pizza");
        System.out.println("2. Special Pizza");
        System.out.println("3. Drink");
        System.out.println("4. Seasonal Deal");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                addBasicPizza();
                break;
            case 2:
                addSpecialPizza();
                break;
            case 3:
                addDrink();
                break;
            case 4:
                addSeasonalDeal();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void addBasicPizza() {
        System.out.println("\n=== ADD BASIC PIZZA ===");
        System.out.println("1. Cheese Pizza");
        System.out.println("2. Veggie Pizza");
        System.out.println("3. Pepperoni Pizza");
        System.out.print("Enter your choice: ");

        int choice = input.nextInt();

        Pizza pizza = null;
        switch (choice) {
            case 1:
                pizza = new CheesePizzaFactory().createPizza();
                break;
            case 2:
                pizza = new VeggiePizzaFactory().createPizza();
                break;
            case 3:
                pizza = new PepperoniPizzaFactory().createPizza();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        if (menuContains(pizza.getName())) {
            System.out.println("Menu already contains an item with this name.");
            return;
        }
        menu.addMenuItem(new PizzaAdapter(pizza));
    }

    public void addSpecialPizza() {
        System.out.println("\n=== ADD SPECIAL PIZZA ===");
        System.out.println("1. Deluxe Meat Lovers");
        System.out.println("2. Veggie Supreme");
        System.out.println("3. BBQ Chicken");
        System.out.println("4. Margherita");
        System.out.print("Enter your choice: ");

        int choice = input.nextInt();

        Pizza pizza = null;
        switch (choice) {
            case 1:
                pizza = director.makeDeluxeMeatLovers();
                break;
            case 2:
                pizza = director.makeVeggieSupreme();
                break;
            case 3:
                pizza = director.makeBBQChicken();
                break;
            case 4:
                pizza = director.makeMargherita();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        if (menuContains(pizza.getName())) {
            System.out.println("Menu already contains an item with this name.");
            return;
        }
        menu.addMenuItem(new PizzaAdapter(pizza));
    }

    public void addDrink() {
        System.out.println("\n=== ADD DRINK ===");
        System.out.print("Enter drink name: ");
        String name = input.next();
        System.out.print("Enter drink price: ");
        double price = input.nextDouble();

        Drink drink = new Drink(name, price);
        if (menuContains(drink.getName())) {
            System.out.println("Menu already contains an item with this name.");
            return;
        }
        menu.addMenuItem(drink);
    }

    public void addSeasonalDeal() {
        System.out.println("\n=== ADD SEASONAL DEAL ===");
        System.out.println("1. Easter Deal");
        System.out.println("2. Christmas Deal");
        System.out.println("3. Birthday Deal");
        System.out.println("4. Student Birthday Deal");
        System.out.println("5. Happy Hour Deal");
        System.out.println("6. Triple Celebration Deal");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                if (menuContains("Easter Deal")) {
                    System.out.println("Menu already contains an item with this name.");
                    return;
                }
                createEasterDeal(0.3);
                break;
            case 2:
                if (menuContains("Christmas Deal")) {
                    System.out.println("Menu already contains an item with this name.");
                    return;
                }
                createChristmasDeal(0.4);
                break;
            case 3:
                if (menuContains("Birthday Deal")) {
                    System.out.println("Menu already contains an item with this name.");
                    return;
                }
                createBirthdayDeal(0.25);
                break;
            case 4:
                if (menuContains("Student Birthday Deal")) {
                    System.out.println("Menu already contains an item with this name.");
                    return;
                }
                createStudentBirthdayDeal(0.1);
                break;
            case 5:
                if (menuContains("Happy Hour Deal")) {
                    System.out.println("Menu already contains an item with this name.");
                    return;
                }
                createStudentHappyHourDeal();
                break;
            case 6:
                if (menuContains("Triple Celebration Deal")) {
                    System.out.println("Menu already contains an item with this name.");
                    return;
                }
                createTripleCelebrationDeal(0.1);
                break;
            default:
                System.out.println("Invalid choice!");
        }   
    }

    private boolean menuContains(String name) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    // sorting
    public void sortMenuUI() {
        System.out.println("\n=== SORT MENU ===");
        System.out.println("Sort by:");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.print("Enter your choice: ");
        int sortChoice = input.nextInt();

        if (sortChoice == 1) {
            setStrategy(new NameStrategy());
        } else if (sortChoice == 2) {
            setStrategy(new PriceStrategy());
        } else {
            System.out.println("Invalid sorting option!");
            return;
        }

        sortMenu();
        showSortedMenu();
    }


    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void sortMenu() {
        if (strategy == null) {
            System.out.println("Strategy not set. Cannot sort menu.");
            return;
        }

        List<MenuComponent> original = new ArrayList<>(menu.getMenuItems());
        sortedMenuItems = strategy.sort(original);
        System.out.println("Menu sorted using " + strategy.getClass().getSimpleName());
    }

    public void showSortedMenu() {
        if (sortedMenuItems == null) {
            System.out.println("Menu not sorted yet.");
            return;
        }

        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                  SORTED PIZZA RESTAURANT MENU             |");
        System.out.println("+------------------------------------------------------------+");

        int idx = 1;
        for (MenuComponent item : sortedMenuItems) {
            System.out.printf("\n%2d.   ", idx++);
            item.display();
        }

        System.out.println("+------------------------------------------------------------+");
        System.out.println("|       Thank you! Come back for more delicious pizza!       |");
        System.out.println("+------------------------------------------------------------+");
    }

    public void createBasicMenu() {
        Pizza cheese = new CheesePizzaFactory().createPizza();
        Pizza veggie = new VeggiePizzaFactory().createPizza();
        Pizza pepperoni = new PepperoniPizzaFactory().createPizza();

        menu.addMenuItem(new PizzaAdapter(cheese));
        menu.addMenuItem(new PizzaAdapter(veggie));
        menu.addMenuItem(new PizzaAdapter(pepperoni));
    }

    public void createSpecialMenu() {
        Pizza meatLovers = director.makeDeluxeMeatLovers();
        Pizza veggieSupreme = director.makeVeggieSupreme();
        Pizza bbq = director.makeBBQChicken();
        Pizza margherita = director.makeMargherita();

        menu.addMenuItem(new PizzaAdapter(meatLovers));
        menu.addMenuItem(new PizzaAdapter(veggieSupreme));
        menu.addMenuItem(new PizzaAdapter(bbq));
        menu.addMenuItem(new PizzaAdapter(margherita));
    }

    public void createEasterDeal(double discountRate) {
        Pizza veggie = director.makeVeggieSupreme();
        Pizza meatLovers = director.makeDeluxeMeatLovers();
        Drink lemonade = new Drink("Lemonade", 1.0);

        ComboDeal easter = new ComboDeal("Easter Special", discountRate);
        easter.addItem(new PizzaAdapter(veggie));
        easter.addItem(new PizzaAdapter(meatLovers));
        easter.addItem(lemonade);
        menu.addMenuItem(easter);
    }

    public void createChristmasDeal(double discountRate) {
        Pizza cheese = director.baseCheesePizza();
        Pizza margherita = director.makeMargherita();
        Drink coco = new Drink("Hot Coco", 1.5);

        ComboDeal christmas = new ComboDeal("Christmas Special", discountRate);
        christmas.addItem(new PizzaAdapter(cheese));
        christmas.addItem(new PizzaAdapter(margherita));
        christmas.addItem(coco);
        menu.addMenuItem(christmas);
    }

    public void createBirthdayDeal(double discountRate) {
        Pizza pepperoni = director.basePepperoniPizza();
        Pizza bbq = director.makeBBQChicken();
        Drink soda = new Drink("Soda", 1.0);

        ComboDeal birthday = new ComboDeal("Birthday Special", discountRate);
        birthday.addItem(new PizzaAdapter(pepperoni));
        birthday.addItem(new PizzaAdapter(bbq));
        birthday.addItem(soda);
        menu.addMenuItem(birthday);
    }

    public void createStudentHappyHourDeal() {
        Pizza veggie = new HappyHourDecorator(new StudentDiscountDecorator(director.makeDeluxeMeatLovers()));
        Pizza margherita = new HappyHourDecorator(new StudentDiscountDecorator(director.makeMargherita()));
        Drink icedTea = new Drink("Iced Tea", 1.0);

        ComboDeal student = new ComboDeal("Happy Student Special (from 13:00 to 15:00)", 0.1);
        student.addItem(new PizzaAdapter(veggie));
        student.addItem(new PizzaAdapter(margherita));
        student.addItem(icedTea);
        menu.addMenuItem(student);
    }

    public void createTripleCelebrationDeal(double discountRate) {
        Pizza cheese = new BirthdayDecorator(new HappyHourDecorator(new StudentDiscountDecorator(director.baseCheesePizza())));
        Pizza pepperoni = new BirthdayDecorator(new HappyHourDecorator(new StudentDiscountDecorator(director.basePepperoniPizza())));
        Drink punch = new Drink("Fruit Punch", 1.5);
        Drink soda = new Drink("Soda", 1.5);
        Drink juice = new Drink("Orange Juice", 1.5);

        ComboDeal triple = new ComboDeal("Triple Celebration Special (only from 13:00 to 15:00)", discountRate);
        triple.addItem(new PizzaAdapter(cheese));
        triple.addItem(new PizzaAdapter(pepperoni));
        triple.addItem(punch);
        triple.addItem(soda);
        triple.addItem(juice);
        menu.addMenuItem(triple);
    }

    public void createStudentBirthdayDeal(double discountRate) {
        Pizza veggie = new BirthdayDecorator(new StudentDiscountDecorator(director.makeVeggieSupreme()));
        Pizza bbq = new BirthdayDecorator(new StudentDiscountDecorator(director.makeBBQChicken()));
        Drink milkshake = new Drink("Milkshake", 2.0);

        ComboDeal studentBirthday = new ComboDeal("Student Birthday Special", discountRate);
        studentBirthday.addItem(new PizzaAdapter(veggie));
        studentBirthday.addItem(new PizzaAdapter(bbq));
        studentBirthday.addItem(milkshake);
        menu.addMenuItem(studentBirthday);
    }

    public void applyHappyHourDiscountToPizza(Pizza pizza) {
        Pizza discountedPizza = new HappyHourDecorator(pizza);
        menu.addMenuItem(new PizzaAdapter(discountedPizza));
    }

    public void applyStudentDiscountToPizza(Pizza pizza) {
        Pizza discountedPizza = new StudentDiscountDecorator(pizza);
        menu.addMenuItem(new PizzaAdapter(discountedPizza));
    }

    public void showMenu() {
        menu.displayMenu();
    }

    public MenuComponent getItem(int index) {
        if (index < 1 || index > menu.size()) {
            throw new IndexOutOfBoundsException("Invalid menu index: " + index);
        }
        return menu.getItem(index - 1);
    }

}
