package domain.facade;
import domain.builder.*;
import domain.composite.*;
import domain.models.*;
import domain.singleton.*;
import domain.adapter.*;
import domain.factorymethod.*;
import domain.decorator.*;

public class MenuFacade {
    private MenuManager menu;
    private PizzaDirector director;
    private IPizzaBuilder builder;
    
    public MenuFacade() {
        menu = MenuManager.getInstance();
        builder = new PizzaBuilder();
        director = new PizzaDirector(builder);
    }

    public void createBasicMenu() {
        Pizza cheese = new CheesePizzaFactory().createPizza();
        Pizza veggie = new VeggiePizzaFactory().createPizza();
        Pizza pepperoni = new PepperoniPizzaFactory().createPizza();

        menu.addMenuItem(new PizzaAdapter(cheese));
        menu.addMenuItem(new PizzaAdapter(veggie));
        menu.addMenuItem(new PizzaAdapter(pepperoni));
    }

    public void createSpecialMenu() {
        Pizza meatLovers = director.makeDeluxeMeatLovers();
        Pizza veggieSupreme = director.makeVeggieSupreme();
        Pizza bbq = director.makeBBQChicken();
        Pizza margherita = director.makeMargherita();

        menu.addMenuItem(new PizzaAdapter(meatLovers));
        menu.addMenuItem(new PizzaAdapter(veggieSupreme));
        menu.addMenuItem(new PizzaAdapter(bbq));
        menu.addMenuItem(new PizzaAdapter(margherita));
    }

    public void createEasterDeal(double discountRate) {
        Pizza veggie = director.makeVeggieSupreme();
        Pizza meatLovers = director.makeDeluxeMeatLovers();
        Drink lemonade = new Drink("Lemonade", 1.0);

        ComboDeal easter = new ComboDeal("Easter Special", discountRate);
        easter.addItem(new PizzaAdapter(veggie));
        easter.addItem(new PizzaAdapter(meatLovers));
        easter.addItem(lemonade);
        menu.addMenuItem(easter);
    }

    public void createChristmasDeal(double discountRate) {
        Pizza cheese = director.baseCheesePizza();
        Pizza margherita = director.makeMargherita();
        Drink coco = new Drink("Hot Coco", 1.5);

        ComboDeal christmas = new ComboDeal("Christmas Special", discountRate);
        christmas.addItem(new PizzaAdapter(cheese));
        christmas.addItem(new PizzaAdapter(margherita));
        christmas.addItem(coco);
        menu.addMenuItem(christmas);
    }

    public void createBirthdayDeal(double discountRate) {
        Pizza pepperoni = director.basePepperoniPizza();
        Pizza bbq = director.makeBBQChicken();
        Drink soda = new Drink("Soda", 1.0);

        ComboDeal birthday = new ComboDeal("Birthday Special", discountRate);
        birthday.addItem(new PizzaAdapter(pepperoni));
        birthday.addItem(new PizzaAdapter(bbq));
        birthday.addItem(soda);
        menu.addMenuItem(birthday);
    }

    public void createStudentHappyHourDeal() {
        Pizza veggie = new HappyHourDecorator(new StudentDiscountDecorator(director.makeDeluxeMeatLovers()));
        Pizza margherita = new HappyHourDecorator(new StudentDiscountDecorator(director.makeMargherita()));
        Drink icedTea = new Drink("Iced Tea", 1.0);

        ComboDeal student = new ComboDeal("Happy Student Special (from 13:00 to 15:00)", 0.1);
        student.addItem(new PizzaAdapter(veggie));
        student.addItem(new PizzaAdapter(margherita));
        student.addItem(icedTea);
        menu.addMenuItem(student);
    }

    public void createTripleCelebrationDeal(double discountRate) {
        Pizza cheese = new BirthdayDecorator(new HappyHourDecorator(new StudentDiscountDecorator(director.baseCheesePizza())));
        Pizza pepperoni = new BirthdayDecorator(new HappyHourDecorator(new StudentDiscountDecorator(director.basePepperoniPizza())));
        Drink punch = new Drink("Fruit Punch", 1.5);
        Drink soda = new Drink("Soda", 1.5);
        Drink juice = new Drink("Orange Juice", 1.5);

        ComboDeal triple = new ComboDeal("Triple Celebration Special (only from 13:00 to 15:00)", discountRate);
        triple.addItem(new PizzaAdapter(cheese));
        triple.addItem(new PizzaAdapter(pepperoni));
        triple.addItem(punch);
        triple.addItem(soda);
        triple.addItem(juice);
        menu.addMenuItem(triple);
    }

    public void createStudentBirthdayDeal(double discountRate) {
        Pizza veggie = new BirthdayDecorator(new StudentDiscountDecorator(director.makeVeggieSupreme()));
        Pizza bbq = new BirthdayDecorator(new StudentDiscountDecorator(director.makeBBQChicken()));
        Drink milkshake = new Drink("Milkshake", 2.0);

        ComboDeal studentBirthday = new ComboDeal("Student Birthday Special", discountRate);
        studentBirthday.addItem(new PizzaAdapter(veggie));
        studentBirthday.addItem(new PizzaAdapter(bbq));
        studentBirthday.addItem(milkshake);
        menu.addMenuItem(studentBirthday);
    }

    public void applyHappyHourDiscountToPizza(Pizza pizza) {
        Pizza discountedPizza = new HappyHourDecorator(pizza);
        menu.addMenuItem(new PizzaAdapter(discountedPizza));
    }

    public void applyStudentDiscountToPizza(Pizza pizza) {
        Pizza discountedPizza = new StudentDiscountDecorator(pizza);
        menu.addMenuItem(new PizzaAdapter(discountedPizza));
    }

    public void showMenu() {
        menu.displayMenu();
    }

}
