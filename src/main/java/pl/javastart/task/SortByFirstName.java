package pl.javastart.task;

import java.util.Comparator;

public class SortByFirstName implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getFirstName().compareTo(p2.getFirstName());
    }
}
