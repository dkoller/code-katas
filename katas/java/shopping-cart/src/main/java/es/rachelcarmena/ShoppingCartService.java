package es.rachelcarmena;

import es.rachelcarmena.domain.product.ProductId;
import es.rachelcarmena.domain.shoppingCart.Item;
import es.rachelcarmena.domain.ticket.Ticket;
import es.rachelcarmena.infrastructure.Clock;
import es.rachelcarmena.repository.ShoppingCartRepository;
import es.rachelcarmena.util.ShoppingCartPrinter;

public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final Clock clock;
    private final ShoppingCartPrinter shoppingCartPrinter;
    private final TicketBuilder ticketBuilder;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, Clock clock,
                               ShoppingCartPrinter shoppingCartPrinter, TicketBuilder ticketBuilder) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.clock = clock;
        this.shoppingCartPrinter = shoppingCartPrinter;
        this.ticketBuilder = ticketBuilder;
    }

    public void addItem(ProductId productId, int quantity) {
        if (shoppingCartRepository.isEmpty())
            shoppingCartRepository.initShoppingCart(clock.now());
        shoppingCartRepository.addItem(Item.of(productId, quantity));
    }

    public void printTicket() {
        Ticket ticket = ticketBuilder
                .withDate(shoppingCartRepository.getDate())
                .withItems(shoppingCartRepository.getItems())
                .build();

        shoppingCartPrinter.print(ticket);
    }
}
