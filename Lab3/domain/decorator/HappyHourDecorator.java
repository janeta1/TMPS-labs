package domain.decorator;
import domain.models.*;

public class HappyHourDecorator extends PriceDecorator {
    private final double happyHourDiscount = 0.25;

    public HappyHourDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getName() {
        return pizza.getName() + " (Happy Hour Discount " + (int)(happyHourDiscount * 100) + "%)";
    }

    @Override
    public double getPrice() {
        return Math.min(pizza.getPrice(), pizza.getPrice() * (1 - happyHourDiscount));
    }
}
