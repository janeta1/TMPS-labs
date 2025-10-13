package items;

// SRP: book only stores book data
public class Book extends Item {
    private String author; 
    private String isbn;
    private String genre;

    public Book(String title, String author, String isbn, String genre) {
        super(title);
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }
}
