package model.concert;

import java.util.Date;

public class Ticket
{
    private final String concertName;
    private final String concertLocation;
    private final Date concertDate;
    private final Date purchaseDate;
    private final Float ticketPrice;

    private Integer ticketCount;

    public Ticket(String concertName, String concertLocation, Date concertDate, Date purchaseDate, Float ticketPrice)
    {
        this.concertName = concertName;
        this.concertLocation = concertLocation;
        this.concertDate = concertDate;
        this.purchaseDate = purchaseDate;
        this.ticketPrice = ticketPrice;
        this.ticketCount = 0;
    }

    public Integer getTicketCount()
    {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount)
    {
        this.ticketCount = ticketCount;
    }

//    @Override
//    public String toString()
//    {
//        return "Ticket for \"" + concertName + "\":\nLocation: " + concertLocation + "\nDate: " + concertDate + "\nDate of Purchase: " + purchaseDate + "\nTicket Price: " + ticketPrice + "\nNumber of Purchased Tickets: " + ticketCount + "\n";
//    }
}
