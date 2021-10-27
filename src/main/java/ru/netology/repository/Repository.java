package ru.netology.repository;

import ru.netology.domain.NegativeIdException;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.TicketInfo;

public class Repository {
    private TicketInfo[] tickets = new TicketInfo[0];

    public void save(TicketInfo ticket) {
        int length = tickets.length + 1;
        TicketInfo[] tmp = new TicketInfo[length];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = ticket;
        tickets = tmp;
    }

    public TicketInfo[] findAll() {
        return tickets;
    }

    public TicketInfo findById(int ticketId) {
        for (TicketInfo ticket : tickets) {
            if (ticket.getTicketId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }

    public void removeById(int ticketId) {
        if(ticketId < 0) {
            throw new NegativeIdException("Can not use negative id: " + ticketId);
        }
        if (findById(ticketId) == null) {
            throw new NotFoundException("Element with id: " + ticketId + " not found");
        }
        int length = tickets.length - 1;
        TicketInfo[] tmp = new TicketInfo[length];
        int index = 0;
        for (TicketInfo ticket : tickets) {
            if (ticket.getTicketId() != ticketId) {
                tmp[index] = ticket;
                index++;
            }
        }
        tickets = tmp;
    }
}
