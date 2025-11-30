package domain.command;
import domain.composite.MenuComponent;
import domain.models.*;

public class AddItemCommand implements OrderCommand {
    private Order order;
    private MenuComponent item;
    private int addedIndex = -1;

    public AddItemCommand(Order order, MenuComponent item) {
        this.order = order;
        this.item = item;
    }

    @Override
    public void execute() {
        addedIndex = order.addItem(item);
    }

    @Override
    public void undo() {
        if (addedIndex >= 0) {
            order.removeItem(addedIndex);
        } else {
            order.removeLastItem();
        }
    }

}
