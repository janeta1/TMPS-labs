package borrowables;

public interface Borrowable {
    public boolean borrowItem(String borrowerName);
    public boolean returnItem();
    public boolean isAvailable();
}
