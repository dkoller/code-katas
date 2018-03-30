package es.rachelcarmena.unit;

import es.rachelcarmena.ShoppingCartService;
import es.rachelcarmena.TicketBuilder;
import es.rachelcarmena.domain.shoppingCart.Item;
import es.rachelcarmena.domain.product.ProductId;
import es.rachelcarmena.domain.ticket.Ticket;
import es.rachelcarmena.infrastructure.Clock;
import es.rachelcarmena.repository.ShoppingCartRepository;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceShould {

    @Mock
    private Clock clock;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ShoppingCartPrinter shoppingCartPrinter;
    @Mock
    private TicketBuilder ticketBuilder;

    private InOrder inOrder;
    private ShoppingCartService shoppingCartService;

    @Before
    public void setUp() {
        shoppingCartService = new ShoppingCartService(shoppingCartRepository, clock, shoppingCartPrinter, ticketBuilder);
        inOrder = Mockito.inOrder(shoppingCartRepository);
    }

    @Test
    public void init_a_shopping_cart_and_adding_an_item_when_adding_one_item() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 10, 14, 0, 0);
        given(clock.now()).willReturn(localDateTime);
        given(shoppingCartRepository.isEmpty()).willReturn(true);

        shoppingCartService.addItem(ProductId.of(1), 1);

        verify(shoppingCartRepository).isEmpty();
        verify(shoppingCartRepository).initShoppingCart(localDateTime);
        verify(shoppingCartRepository).addItem(Item.of(ProductId.of(1), 1));
        verifyNoMoreInteractions(shoppingCartRepository);
    }

    @Test
    public void init_a_shopping_cart_once_when_adding_two_items() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 10, 14, 0, 0);
        given(clock.now()).willReturn(localDateTime);
        given(shoppingCartRepository.isEmpty()).willReturn(true, false);

        shoppingCartService.addItem(ProductId.of(1), 1);
        shoppingCartService.addItem(ProductId.of(2), 1);

        inOrder.verify(shoppingCartRepository).isEmpty();
        inOrder.verify(shoppingCartRepository).initShoppingCart(localDateTime);
        inOrder.verify(shoppingCartRepository).addItem(Item.of(ProductId.of(1), 1));
        inOrder.verify(shoppingCartRepository).isEmpty();
        inOrder.verify(shoppingCartRepository).addItem(Item.of(ProductId.of(2), 1));
        verifyNoMoreInteractions(shoppingCartRepository);
    }

    @Test
    public void print_a_ticket() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 10, 14, 0, 0);
        List<Item> items = new ArrayList<>();
        items.add(Item.of(ProductId.of(1), 1));
        items.add(Item.of(ProductId.of(2), 1));
        given(shoppingCartRepository.getDate()).willReturn(localDateTime);
        given(shoppingCartRepository.getItems()).willReturn(items);
        Ticket ticket = new Ticket(localDateTime);

        given(ticketBuilder.withDate(localDateTime)).willReturn(ticketBuilder);
        given(ticketBuilder.withItems(items)).willReturn(ticketBuilder);
        given(ticketBuilder.build()).willReturn(ticket);

        shoppingCartService.printTicket();

        verify(shoppingCartRepository).getDate();
        verify(shoppingCartRepository).getItems();
        verify(shoppingCartPrinter).print(ticket);
        verifyNoMoreInteractions(shoppingCartRepository);
        verifyNoMoreInteractions(shoppingCartPrinter);
    }
}
