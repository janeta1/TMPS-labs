package items;

abstract public class Item {
    private String title;

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
