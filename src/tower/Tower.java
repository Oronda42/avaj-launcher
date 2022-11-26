package src.tower;

import java.util.ArrayList;

import src.Flyable;
import src.Logger;
import src.aircraft.Aircraft;

public class Tower {

    public ArrayList<Flyable> observers = new ArrayList<>();
     
    public void register(Flyable flyable) {
        observers.add(flyable);
        String str = String.format("Tower says: %s#%s(%d) registered to weather tower",
        flyable.getClass().getSimpleName(),
        ((Aircraft)flyable).getName(),
        ((Aircraft)flyable).getId());
        Logger.getInstance().log(str);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
