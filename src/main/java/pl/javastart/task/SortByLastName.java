package pl.javastart.task;

import java.util.Comparator;

public class SortByLastName implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getLastName().compareTo(p2.getLastName());
    }
}
