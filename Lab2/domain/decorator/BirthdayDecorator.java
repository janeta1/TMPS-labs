package domain.decorator;
import domain.models.*;

public class BirthdayDecorator extends PriceDecorator {
    private static final double birthdayDiscount = 0.15; 

    public BirthdayDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() * (1 - birthdayDiscount);
    }
    
}
