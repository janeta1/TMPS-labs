package client;

import domain.facade.MenuFacade;

public class Main {
    public static void main(String[] args) {
        MenuFacade menu = new MenuFacade();
        menu.createBasicMenu();
        menu.createSpecialMenu();
        menu.createChristmasDeal(0.4);
        menu.createBirthdayDeal(0.25);
        menu.createEasterDeal(0.3);
        menu.createStudentBirthdayDeal(0.1);
        menu.createStudentHappyHourDeal();
        menu.createTripleCelebrationDeal(0.1);
        
        menu.showMenu();
    }
}