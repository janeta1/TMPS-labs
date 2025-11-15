package domain.builder;
import java.util.List;
import domain.models.CustomPizza;
import domain.models.Pizza;

public class PizzaBuilder implements IPizzaBuilder {
    private Pizza basePizza;
    private String newName;
    private double price;
    private Dough dough;
    private Sauce sauce;
    private List<Topping> toppings;
    private Size size;

    @Override
    public void setBase(Pizza basePizza) {
        this.basePizza = basePizza;
        this.price = basePizza.getPrice();
    }

    @Override
    public void setName(String newName) {
        this.newName = newName;
    }

    @Override
    public void setPrice(double price) {
        double finalPrice = price;
        if (size == Size.MEDIUM) finalPrice += 2.0;
        if (size == Size.LARGE) finalPrice += 4.0;
        if (dough == Dough.STUFFED) finalPrice += 3.0;
        if (toppings != null) finalPrice += toppings.size() * 1.5;
        this.price = finalPrice;
    }

    @Override
    public void setDough(Dough dough) {
        this.dough = dough;
    }

    @Override
    public void setSauce(Sauce sauce) { 
        this.sauce = sauce;
    }

    @Override
    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }   

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public Pizza build() {
        return new CustomPizza(newName, price, dough, sauce, toppings, size, basePizza.getName());
    }

}
