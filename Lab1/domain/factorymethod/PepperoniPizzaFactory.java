package domain.factorymethod;
import domain.models.Pizza;
import domain.models.PepperoniPizza;

public class PepperoniPizzaFactory extends PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new PepperoniPizza();
    }

}
