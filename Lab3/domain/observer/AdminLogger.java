package domain.observer;

public class AdminLogger implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[ADMIN LOG]: " + message);
    }

}
