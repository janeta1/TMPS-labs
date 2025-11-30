package domain.builder;
import java.util.List;
import domain.models.Pizza;

public interface IPizzaBuilder {
    public void setBase(Pizza pizza);
    public void setName(String name);
    public void setPrice(double price);
    public void setDough(Dough dough);
    public void setSauce(Sauce sauce);
    public void setToppings(List<Topping> toppings);
    public void setSize(Size size);
    public Pizza build();
    
    enum Size {
        SMALL, MEDIUM, LARGE
    }

    enum Dough {
        THIN, THICK, STUFFED
    }

    enum Sauce {
        TOMATO, ALFREDO, PESTO
    }

    enum Topping {
        CHEESE, PEPPERONI, MUSHROOMS, ONIONS, SAUSAGE, BACON, OLIVES, PEPPERS
    }
}
