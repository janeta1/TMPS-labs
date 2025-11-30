package domain.command;
import domain.composite.MenuComponent;
import domain.models.Order;

public class RemoveItemCommand implements OrderCommand {
    private Order order;
    private MenuComponent removedItem;
    private int index;

    public RemoveItemCommand(Order order, int index) {
        this.order = order;
        this.index = index;
    }

    public void execute() {
        removedItem = order.removeItem(index);
    }

    public void undo() {
        if (removedItem != null) {
            order.addItem(removedItem);
        }
    }
}