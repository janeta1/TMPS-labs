package domain.models;
import java.util.List;
import domain.builder.IPizzaBuilder.*;

public class CustomPizza extends Pizza {
    private Dough dough;
    private Sauce sauce;
    private List<Topping> toppings;
    private Size size;
    private String baseType; // "Cheese", "Pepperoni", or "Veggie"

    public CustomPizza(String name, double price, Dough dough, Sauce sauce, List<Topping> toppings, Size size, String baseType) {
        super(name, price);
        this.dough = dough;
        this.sauce = sauce;
        this.toppings = toppings;
        this.size = size;
        this.baseType = baseType;
    }

    public Dough getDough() {
        return dough;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public Size getSize() {
        return size;
    }

    public String getBaseType() {
        return baseType;
    }

    public void getDetails() {
        System.out.println(this.getName() + " ($" + String.format("%.2f", this.getPrice()) + ")");
        System.out.println("         Size: " + this.getSize());
        System.out.println("         Dough: " + this.getDough());
        System.out.println("         Sauce: " + this.getSauce());

        if (this.getToppings() != null && !this.getToppings().isEmpty()) {
            System.out.print("         Toppings: ");
            for (int j = 0; j < this.getToppings().size(); j++) {
                System.out.print(this.getToppings().get(j));
                if (j < this.getToppings().size() - 1) System.out.print(", ");
            }
            System.out.println();
        } else {
            System.out.println("         Toppings: None");
        }
    } 
}
