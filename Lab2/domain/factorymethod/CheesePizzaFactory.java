package domain.factorymethod;
import domain.models.Pizza;
import domain.models.CheesePizza;

public class CheesePizzaFactory extends PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new CheesePizza();
    }

}
