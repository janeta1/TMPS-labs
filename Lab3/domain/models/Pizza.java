package domain.models;

public class Pizza {
    private String name;
    private double price;

    public Pizza(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void getDetails() {
        System.out.println(this.getName() + " - $" + String.format("%.2f", this.getPrice()));
    }
}
