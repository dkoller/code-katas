package es.rachelcarmena.domain.ticket;

import es.rachelcarmena.domain.Amount;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket {
    private LocalDateTime datetime;
    private List<Line> lines;
    private List<DiscountLine> discountLines;

    public Ticket(LocalDateTime dateTime) {
        this.datetime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return datetime;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<DiscountLine> getDiscountLines() {
        return discountLines;
    }

    public Amount getTotal() {
        Amount totalPrice = lines.stream().map(Line::getTotalPrice).reduce(Amount::add).get();
        if (discountLines.isEmpty()) return totalPrice;

        Amount totalDiscount = discountLines.stream().map(DiscountLine::getAmount).reduce(Amount::add).get();
        return totalPrice.subtract(totalDiscount);
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void setDiscountLines(List<DiscountLine> discountLines) {
        this.discountLines = discountLines;
    }
}
