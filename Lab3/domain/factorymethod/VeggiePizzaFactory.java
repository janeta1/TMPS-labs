package domain.factorymethod;
import domain.models.Pizza;
import domain.models.VeggiePizza;

public class VeggiePizzaFactory extends PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new VeggiePizza();
    }

}
