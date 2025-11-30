package domain.strategy;

import java.util.List;

import domain.composite.MenuComponent;

public interface Strategy {
    List<MenuComponent> sort(List<MenuComponent> items);
}
