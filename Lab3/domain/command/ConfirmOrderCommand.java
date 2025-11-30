package domain.command;
import domain.models.Order;

public class ConfirmOrderCommand implements OrderCommand {
    private Order order;
    private boolean confirmed = false;

    public ConfirmOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        if (order.isEmpty()) {
            System.out.println("Order is empty. Cannot confirm.");
        } else {
            confirmed = true;
            System.out.println("Order confirmed! Enjoy your pizza!");
        }
    }

    @Override
    public void undo() {
        if (confirmed) {
            order.unconfirm();
            System.out.println("Order confirmation undone! Back to editing.");
            confirmed = false;
        }
    }
}
