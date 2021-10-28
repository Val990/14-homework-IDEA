package ru.netology.manager;

import ru.netology.domain.TicketInfo;
import ru.netology.repository.Repository;

import java.util.Arrays;
import java.util.Comparator;

public class Manager {

    private Repository repository;

    public Manager(Repository repository) {
        this.repository = repository;
    }

    public Manager() {
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }


    public void add(TicketInfo ticket) {
        repository.save(ticket);
    }

    public TicketInfo[] searchBy(String from, String to, Comparator<TicketInfo> comparator) {
        TicketInfo[] result = new TicketInfo[0];
        for (TicketInfo ticket : repository.findAll()) {
            if (ticket.getFrom().equalsIgnoreCase(from) && ticket.getTo().equalsIgnoreCase(to)) {
                TicketInfo[] tmp = new TicketInfo[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }
}



