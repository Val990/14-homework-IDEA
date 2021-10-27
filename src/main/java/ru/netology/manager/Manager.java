package ru.netology.manager;

import ru.netology.domain.TicketInfo;
import ru.netology.repository.Repository;

import java.util.Arrays;

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


    public void add(TicketInfo item) {
        repository.save(item);
    }

    public TicketInfo[] searchBy(String from, String to) {
        TicketInfo[] result = new TicketInfo[0];
        for (TicketInfo item : repository.findAll()) {
            if (item.getFrom().equalsIgnoreCase(from) && item.getTo().equalsIgnoreCase(to)) {
                TicketInfo[] tmp = new TicketInfo[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}



