package domain.decorator;
import domain.models.*;

public class PriceDecorator extends Pizza {
    protected Pizza pizza;

    public PriceDecorator(Pizza pizza) {
        super(pizza.getName(), pizza.getPrice());
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
}
