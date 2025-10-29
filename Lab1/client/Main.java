package client;

import domain.builder.*;
import domain.factorymethod.*;
import domain.models.Pizza;
import domain.singleton.MenuManager;

public class Main {
    public static void main(String[] args) {
        MenuManager menuManager = MenuManager.getInstance();

        // Using Factory Method to create pizzas
        PizzaFactory cheeseFactory = new CheesePizzaFactory();
        PizzaFactory pepperoniFactory = new PepperoniPizzaFactory();

        Pizza cheesePizza = cheeseFactory.createPizza();
        Pizza pepperoniPizza = pepperoniFactory.createPizza();

        // Using Builder to create a custom pizza
        PizzaBuilder builder = new PizzaBuilder();
        PizzaDirector director = new PizzaDirector(builder);

        menuManager.addMenuItem(pepperoniPizza);
        menuManager.addMenuItem(cheesePizza);
        menuManager.addMenuItem(director.makeDeluxeMeatLovers());
        menuManager.addMenuItem(director.makeMargherita());
        menuManager.addMenuItem(director.makeVeggieSupreme());
        menuManager.addMenuItem(director.makeBBQChicken());

        menuManager.displayMenu();
    }
}