package es.rachelcarmena.unit;

import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.ticket.DiscountLine;
import es.rachelcarmena.domain.ticket.Line;
import es.rachelcarmena.domain.ticket.Ticket;
import es.rachelcarmena.infrastructure.Console;
import es.rachelcarmena.util.ShoppingCartPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static es.rachelcarmena.acceptance.ShoppingCartServiceFeature.BAGUETTE;
import static es.rachelcarmena.acceptance.ShoppingCartServiceFeature.CHOCOLATE_BAR;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartPrinterShould {

    private ShoppingCartPrinter shoppingCartPrinter;
    @Mock
    private Console console;
    InOrder inOrder;

    @Before
    public void setUp() {
        shoppingCartPrinter = new ShoppingCartPrinter(console);
        inOrder = Mockito.inOrder(console);
    }

    @Test
    public void print_a_ticket_without_discounts() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 10, 14, 0, 5);
        Ticket ticket = new Ticket(localDateTime);

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(CHOCOLATE_BAR, Amount.valueOf(2), 1, Amount.valueOf(2)));
        lines.add(new Line(BAGUETTE, Amount.valueOf(0.8), 1, Amount.valueOf(0.8)));
        ticket.setLines(lines);

        ticket.setDiscountLines(new ArrayList<>());

        shoppingCartPrinter.print(ticket);

        inOrder.verify(console).print("10/01/2018 14:00:05");
        inOrder.verify(console).print("PRODUCTS:");
        inOrder.verify(console).print(" Chocolate bar       2.00");
        inOrder.verify(console).print(" Baguette            0.80");
        inOrder.verify(console).print("TOTAL: 2.80");
    }

    @Test
    public void print_a_ticket_with_discount() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 10, 14, 0, 5);
        Ticket ticket = new Ticket(localDateTime);

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(CHOCOLATE_BAR, Amount.valueOf(2), 1, Amount.valueOf(2)));
        lines.add(new Line(BAGUETTE, Amount.valueOf(4), 5, Amount.valueOf(0.8)));
        ticket.setLines(lines);

        List<DiscountLine> discountLines = new ArrayList<>();
        discountLines.add(new DiscountLine(BAGUETTE, Amount.valueOf(0.48)));
        ticket.setDiscountLines(discountLines);

        shoppingCartPrinter.print(ticket);

        inOrder.verify(console).print("PRODUCTS:");
        inOrder.verify(console).print(" Chocolate bar       2.00");
        inOrder.verify(console).print(" Baguette            4.00");
        inOrder.verify(console).print(" 5 x 0.80");
        inOrder.verify(console).print(" Baguette discount  -0.48");
        inOrder.verify(console).print("TOTAL: 5.52");
    }
}
