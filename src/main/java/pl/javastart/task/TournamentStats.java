package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {
    private static final int SORT_ASCENDING = 1;
    private static final int SORT_DESCENDING = 2;
    private static final int SORT_BY_FIRSTNAME = 1;
    private static final int SORT_BY_LASTNAME = 2;
    private static final int SORT_BY_RESULT = 3;
    private List<Player> players = new LinkedList<>();

    void run(Scanner scanner) {
        String userOption = null;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub STOP):");
            userOption = scanner.nextLine();
            players.addAll(getPlayers(userOption));
        } while (!userOption.equals("STOP"));
        Comparator<Player> comparator = sortByParameter(scanner);

        Comparator<Player> comparator1 = sortAscendingOrDescending(scanner, comparator);

        players.sort(comparator1);

        try {
            savePlayersToFile("stats.csv");
        } catch (IOException e) {
            System.err.println("Nie udalo sie zapisac danych do pliku.");
        }
    }

    private static Comparator<Player> sortAscendingOrDescending(Scanner scanner, Comparator<Player> comparator) {
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        int sortingOption = scanner.nextInt();
        if (sortingOption == SORT_DESCENDING) {
            return comparator = comparator.reversed();
        } else if (sortingOption == SORT_ASCENDING) {
            return comparator;
        }
        return comparator;
    }

    private void savePlayersToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Player player : players) {
                writer.write(player.toCsv());
                writer.newLine();
            }
            System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
        }
    }

    private static Comparator<Player> sortByParameter(Scanner scanner) {
        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        int sortOption = scanner.nextInt();
        Comparator<Player> comparator = null;
        if (sortOption == SORT_BY_FIRSTNAME) {
            comparator = new SortByFirstName();
        } else if (sortOption == SORT_BY_LASTNAME) {
            comparator = new SortByLastName();
        } else if (sortOption == SORT_BY_RESULT) {
            comparator = new SortByResult();
        }
        return comparator;
    }

    private static List<Player> getPlayers(String userOption) {
        List<Player> players = new ArrayList<>();
        if (!userOption.equals("STOP")) {
            String[] split = userOption.split(" ");
            String firstName = split[0];
            String lastName = split[1];
            int result = Integer.parseInt(split[2]);
            players.add(new Player(firstName, lastName, result));
        }
        return players;
    }

}
