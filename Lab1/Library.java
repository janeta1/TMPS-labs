
import java.util.List;
import borrowables.Borrowable;
import borrowables.BorrowableItem;
import items.Book;
import items.DVD;
import java.util.ArrayList;

// DIP: Library depends only on the Borrowable interface
public class Library {
    private List<Borrowable> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(Borrowable item) {
        items.add(item);
    }

    public boolean borrowItem(Borrowable item, String borrowerName) {
        return item.borrowItem(borrowerName);
    }

    public boolean returnItem(Borrowable item) {
        return item.returnItem();
    }

    public void showAvailableItems() {
        System.out.println("Available Library Items");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-25s | %-20s | %-5s\n", "Title", "Author/Director", "Type");
        System.out.println("-------------------------------------------------------------");
        for (Borrowable item : items) {
            if (item.isAvailable()) {
                String title = "";
                String author = "";
                String type = "";
                
                if (item instanceof BorrowableItem bookItem && bookItem.getItem() instanceof Book book) {
                    title = book.getTitle();
                    author = book.getAuthor();
                    type = "Book";
                } else if (item instanceof BorrowableItem dvdItem && dvdItem.getItem() instanceof DVD dvd) {
                    title = dvd.getTitle();
                    author = dvd.getDirector();
                    type = "DVD";
                }

                System.out.printf("%-25s | %-20s | %-5s\n", title, author, type);
            }
        }
        System.out.println("-------------------------------------------------------------\n");
    }

}
