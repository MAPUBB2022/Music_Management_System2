package model.concert;

import model.users.User;

import java.util.Date;

public class Ticket
{
    private String concertName;
    private String concertLocation;
    private Date concertDate;
    private Date purchaseDate;
    private Float ticketPrice;

    private Integer ticketCount;

    private User user;

    public Ticket(){}

    public Ticket(String concertName, String concertLocation, Date concertDate, Date purchaseDate, Float ticketPrice, User user)
    {
        this.concertName = concertName;
        this.concertLocation = concertLocation;
        this.concertDate = concertDate;
        this.purchaseDate = purchaseDate;
        this.ticketPrice = ticketPrice;
        this.ticketCount = 0;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public String getConcertLocation() {
        return concertLocation;
    }

    public void setConcertLocation(String concertLocation) {
        this.concertLocation = concertLocation;
    }

    public Date getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(Date concertDate) {
        this.concertDate = concertDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
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
