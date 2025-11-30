package domain.decorator;
import domain.models.*;

public class StudentDiscountDecorator extends PriceDecorator {
    private final double studentDiscount = 0.15;

    public StudentDiscountDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getName() {
        return pizza.getName() + " (Student Discount " + (int)(studentDiscount * 100) + "%)";
    }

    @Override
    public double getPrice() {
        return Math.min(pizza.getPrice(), pizza.getPrice() * (1 - studentDiscount));
    }
}