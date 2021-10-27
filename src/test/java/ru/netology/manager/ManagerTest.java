package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NegativeIdException;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.TicketInfo;
import ru.netology.repository.Repository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Repository repository = new Repository();
    Manager manager = new Manager(repository);

    private TicketInfo first = new TicketInfo(1, 2_000, "SVO", "LED", 2);
    private TicketInfo second = new TicketInfo(2, 4_000, "LED", "CEK", 3);
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
    void shouldFindAll() {

        TicketInfo[] expected = new TicketInfo[]{first, second, third, forth, fifth};
        TicketInfo[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchOneFlight() {

        TicketInfo[] expected = new TicketInfo[]{forth};
        TicketInfo[] actual = manager.searchBy("DME", "JFK");

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchTwoSameFlight() {

        TicketInfo[] expected = new TicketInfo[]{first, third};
        TicketInfo[] actual = manager.searchBy("SVO", "LED");

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchByNotExistFlight() {

        TicketInfo[] expected = new TicketInfo[]{};
        TicketInfo[] actual = manager.searchBy("SHJ", "SHJ");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSortByPrice() {

        TicketInfo[] expected = new TicketInfo[]{first, third, second, fifth, forth};
        TicketInfo[] actual = new TicketInfo[]{first, second, third, forth, fifth};

        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
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
    void shouldRemoveByNullId() {

        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(70);
        });
    }

    @Test
    void shouldFindRepo() {

        Manager manager = new Manager();

        manager.setRepository(repository);

        assertEquals(repository, manager.getRepository());

    }

    @Test
    public void shouldUseNoArgsConstructor() {
    }

    @Test
    public void shouldUseAllArgsConstructor() {

        TicketInfo ticketInfo = new TicketInfo();

        ticketInfo.setTicketId(3);
        ticketInfo.setTicketPrice(3_000);
        ticketInfo.setFrom("SVO");
        ticketInfo.setTo("LED");
        ticketInfo.setFlightTimeHours(2);

        assertEquals(3, ticketInfo.getTicketId());
        assertEquals(3_000, ticketInfo.getTicketPrice());
        assertEquals("SVO", ticketInfo.getFrom());
        assertEquals("LED", ticketInfo.getTo());
        assertEquals(2, ticketInfo.getFlightTimeHours());
    }
}