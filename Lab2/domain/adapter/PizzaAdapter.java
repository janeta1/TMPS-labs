package domain.adapter;
import domain.models.*;
import domain.composite.*;

public class PizzaAdapter implements MenuComponent {
    private Pizza pizza;

    public PizzaAdapter(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getName() {
        return pizza.getName();
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }

    public Pizza getPizza() {
        return pizza;
    }

    @Override
    public void display() {
        pizza.getDetails();
    }
}