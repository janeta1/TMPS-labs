package domain.builder;
import java.util.List;
import domain.models.*;
import domain.builder.IPizzaBuilder.*;
import domain.factorymethod.*;

public class PizzaDirector {
    private IPizzaBuilder builder;

    public PizzaDirector(IPizzaBuilder builder) {
        this.builder = builder;
    }

    // Menu Item 2: Deluxe Meat Lovers
    public Pizza makeDeluxeMeatLovers() {
        Pizza base = new PepperoniPizzaFactory().createPizza();
        builder.setBase(base);
        builder.setName("Deluxe Meat Lovers");
        builder.setSize(Size.LARGE);
        builder.setDough(Dough.STUFFED);
        builder.setSauce(Sauce.TOMATO);
        builder.setToppings(List.of(
            Topping.PEPPERONI, 
            Topping.SAUSAGE, 
            Topping.BACON
        ));
        builder.setPrice(base.getPrice());
        return builder.build();
    }

    // Menu Item 3: Veggie Supreme
    public Pizza makeVeggieSupreme() {
        Pizza base = new VeggiePizzaFactory().createPizza();
        builder.setBase(base);
        builder.setName("Veggie Supreme");
        builder.setSize(Size.MEDIUM);
        builder.setDough(Dough.THICK);
        builder.setSauce(Sauce.PESTO);
        builder.setToppings(List.of(
            Topping.MUSHROOMS, 
            Topping.ONIONS, 
            Topping.OLIVES, 
            Topping.PEPPERS
        ));
        builder.setPrice(base.getPrice());
        return builder.build();
    }

    // Menu Item 4: BBQ Chicken Special
    public Pizza makeBBQChicken() {
        Pizza base = new CheesePizzaFactory().createPizza();
        builder.setBase(base);
        builder.setName("BBQ Chicken Special");
        builder.setSize(Size.LARGE);
        builder.setDough(Dough.THIN);
        builder.setSauce(Sauce.ALFREDO);
        builder.setToppings(List.of(
            Topping.BACON, 
            Topping.ONIONS
        ));
        builder.setPrice(base.getPrice());
        return builder.build();
    }

    // Menu Item 5: Margherita (Simple & Classic)
    public Pizza makeMargherita() {
        Pizza base = new CheesePizzaFactory().createPizza();
        builder.setBase(base);
        builder.setName("Margherita");
        builder.setSize(Size.SMALL);
        builder.setDough(Dough.THIN);
        builder.setSauce(Sauce.TOMATO);
        builder.setToppings(List.of());
        builder.setPrice(base.getPrice());
        return builder.build();
    }
}
