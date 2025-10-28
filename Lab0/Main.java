import borrowables.BorrowableItem;
import items.Book;
import items.DVD;
import items.Item;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Item book1 = new Book("1984", "George Orwell", "12345", "Dystopian");
        Item book2 = new Book("The Hobbit", "J.R.R. Tolkien", "67890", "Fantasy");
        Item dvd1 = new DVD("Inception", "Christopher Nolan", "Sci-Fi", 148);
        Item dvd2 = new DVD("The Matrix", "Wachowski Brothers", "Sci-Fi", 136);

        BorrowableItem libBook1 = new BorrowableItem(book1);
        BorrowableItem libBook2 = new BorrowableItem(book2);
        BorrowableItem libDVD1 = new BorrowableItem(dvd1);
        BorrowableItem libDVD2 = new BorrowableItem(dvd2);

        library.addItem(libBook1);
        library.addItem(libBook2);
        library.addItem(libDVD1);
        library.addItem(libDVD2);

        Member member = new Member("Alice", "1001");

        library.showAvailableItems();

        System.out.println("\n->" + member.getName() + " borrows 1984 and Inception:");
        library.borrowItem(libBook1, member.getMemberId());
        library.borrowItem(libDVD1, member.getMemberId());

        library.showAvailableItems();

        System.out.println("\n->" + member.getName() + " returns 1984:");
        library.returnItem(libBook1);

        library.showAvailableItems();
    }
}
