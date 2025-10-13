package borrowables;
import items.Item;

// OCP: I can create new borrowable items without changing this class
public class BorrowableItem implements Borrowable {
    private Item item;
    private String borrowerId;
    private boolean available;

    public BorrowableItem(Item item) {
        this.item = item;
        this.available = true;
    }

    @Override
    public boolean borrowItem(String borrowerId) {
        if (available) {
            this.borrowerId = borrowerId;
            this.available = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnItem() {
        if (!available) {
            this.borrowerId = null;
            this.available = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public Item getItem() {
        return item;
    }
}
