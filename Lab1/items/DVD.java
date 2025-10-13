package items;

// SRP: DVD only stores DVD data
public class DVD extends Item {
    private String director;
    private String genre;  
    private int duration; 

    public DVD(String title, String director, String genre, int duration) {
        super(title);
        this.director = director;
        this.genre = genre;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }
}
