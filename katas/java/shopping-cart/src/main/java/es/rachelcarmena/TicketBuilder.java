package es.rachelcarmena;

import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.product.Product;
import es.rachelcarmena.domain.ticket.DiscountLine;
import es.rachelcarmena.domain.shoppingCart.Item;
import es.rachelcarmena.domain.ticket.Line;
import es.rachelcarmena.domain.ticket.Ticket;
import es.rachelcarmena.repository.ProductsRepository;
import es.rachelcarmena.util.Calculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketBuilder {
    private final ProductsRepository productsRepository;
    private final Calculator calculator;

    private LocalDateTime dateTime;
    private List<DiscountLine> discountLines = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();

    public TicketBuilder(ProductsRepository productsRepository, Calculator calculator) {
        this.productsRepository = productsRepository;
        this.calculator = calculator;
    }

    public TicketBuilder withDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public TicketBuilder withItems(List<Item> items) {
        for (Item item : items) {
            Product product = productsRepository.getProduct(item.getProductId());

            Amount price = calculator.getPrice(item.getQuantity(), product.getPrice());
            Line line = new Line(product.getName(), price, item.getQuantity(), product.getPrice());
            lines.add(line);

            if (!product.hasDiscount()) continue;

            Amount discount = calculator.getDiscount(item.getQuantity(), product.getPrice(), product.getDiscount());
            DiscountLine discountLine = new DiscountLine(product.getName(), discount);
            discountLines.add(discountLine);
        }
        return this;
    }

    public Ticket build() {
        Ticket ticket = new Ticket(dateTime);
        ticket.setLines(lines);
        ticket.setDiscountLines(discountLines);
        return ticket;
    }
}
