package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NegativeIdException;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.TicketByTimeAscComparator;
import ru.netology.domain.TicketInfo;
import ru.netology.repository.Repository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Repository repository = new Repository();
    Manager manager = new Manager(repository);
    TicketByTimeAscComparator comparator = new TicketByTimeAscComparator();

    private TicketInfo first = new TicketInfo(1, 2_000, "SVO", "LED", 3);
    private TicketInfo second = new TicketInfo(2, 4_000, "LED", "CEK", 4);
    private TicketInfo third = new TicketInfo(3, 3_000, "SVO", "LED", 2);
    private TicketInfo forth = new TicketInfo(4, 30_000, "DME", "JFK", 10);
    private TicketInfo fifth = new TicketInfo(5, 20_000, "LED", "CDG", 5);

    @BeforeEach
    public void add() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
    }

    @Test
    public void shouldSortByFlightTime() {

        TicketInfo[] expected = new TicketInfo[]{third, first, second, fifth, forth};
        TicketInfo[] actual = new TicketInfo[]{first, second, third, forth, fifth};

        Arrays.sort(actual, comparator);

        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSortOneTicketByFlightTime() {

        TicketInfo[] expected = new TicketInfo[]{fifth};
        TicketInfo[] actual = manager.searchBy("LED", "CDG", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSortTwoTicketByFlightTime() {

        TicketInfo[] expected = new TicketInfo[]{third, first};
        TicketInfo[] actual = manager.searchBy("SVO", "LED", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSortFlightNotExist() {

        TicketInfo[] expected = new TicketInfo[]{};
        TicketInfo[] actual = manager.searchBy("SHJ", "LED", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {

        repository.removeById(3);

        TicketInfo[] expected = new TicketInfo[]{first, second, forth, fifth};
        TicketInfo[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByNegativeId() {

        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        assertThrows(NegativeIdException.class, () -> {
            repository.removeById(-10);
        });
    }

    @Test
    void shouldRemoveByNotExistElement() {

        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(70);
        });
    }
}