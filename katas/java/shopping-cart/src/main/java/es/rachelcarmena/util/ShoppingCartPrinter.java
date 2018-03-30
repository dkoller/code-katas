package es.rachelcarmena.util;

import es.rachelcarmena.domain.ticket.DiscountLine;
import es.rachelcarmena.domain.ticket.Line;
import es.rachelcarmena.domain.ticket.Ticket;
import es.rachelcarmena.infrastructure.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShoppingCartPrinter {

    private static final String LINE_FORMAT = " %-19s %s";
    private static final String DETAIL_FORMAT = " %d x %s";
    private static final String DISCOUNT_FORMAT = " %-18s -%s";
    private static final String TOTAL_FORMAT = "TOTAL: %s";
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private final Console console;

    public ShoppingCartPrinter(Console console) {
        this.console = console;
    }

    public void print(Ticket ticket) {
        console.print(format(ticket.getDateTime()));
        console.print("PRODUCTS:");

        for(Line line: ticket.getLines()) {
            console.print(String.format(LINE_FORMAT, line.getProductName(), line.getTotalPrice().print()));
            if (line.getNumberOfItems() > 1)
                console.print(String.format(DETAIL_FORMAT, line.getNumberOfItems(), line.getPricePerUnit().print()));
        }

        for (DiscountLine discountLine: ticket.getDiscountLines()) {
            console.print(String.format(DISCOUNT_FORMAT, discountLine.getProductName() + " discount", discountLine.getAmount().print()));
        }

        console.print(String.format(TOTAL_FORMAT, ticket.getTotal().print()));
    }

    private String format(LocalDateTime dateTime) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dateTime.format(dateTimeFormatter);
    }
}
