package ru.netology.domain;

public class TicketInfo implements Comparable<TicketInfo> {
    private int ticketId;
    private int ticketPrice;
    private String from;
    private String to;
    private int flightTimeHours;

    public TicketInfo(int ticketId, int ticketPrice, String from, String to, int flightTimeHours) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.from = from;
        this.to = to;
        this.flightTimeHours = flightTimeHours;
    }

    public TicketInfo() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getFlightTimeHours() {
        return flightTimeHours;
    }

    public void setFlightTimeHours(int flightTimeHours) {
        this.flightTimeHours = flightTimeHours;
    }

    @Override
    public int compareTo(TicketInfo o) {
        return ticketPrice - o.ticketPrice;
    }
}
