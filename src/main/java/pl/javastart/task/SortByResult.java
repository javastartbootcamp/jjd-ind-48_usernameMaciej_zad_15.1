package pl.javastart.task;

import java.util.Comparator;

public class SortByResult implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return Integer.compare(p1.getResult(), p2.getResult());
    }
}
