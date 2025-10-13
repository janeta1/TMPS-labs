package borrowables;

public interface Borrowable {
    boolean borrowItem(String borrowerName);
    boolean returnItem();
    boolean isAvailable();
}
