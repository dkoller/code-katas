package es.rachelcarmena.acceptance;

import es.rachelcarmena.TicketBuilder;
import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.product.Discount;
import es.rachelcarmena.domain.product.Product;
import es.rachelcarmena.util.Calculator;
import es.rachelcarmena.infrastructure.Clock;
import es.rachelcarmena.infrastructure.Console;
import es.rachelcarmena.domain.product.ProductId;
import es.rachelcarmena.repository.ProductsRepository;
import es.rachelcarmena.util.ShoppingCartPrinter;
import es.rachelcarmena.repository.ShoppingCartRepository;
import es.rachelcarmena.ShoppingCartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceFeature {

    public static final String CHOCOLATE_BAR = "Chocolate bar";
    public static final String BAGUETTE = "Baguette";
    public static final int CHOCOLATE_BAR_ID = 1;
    public static final int BAGUETTE_ID = 2;
    @Mock
    Console console;
    @Mock
    Clock clock;
    private ShoppingCartService shoppingCartService;
    private InOrder inOrder;

    @Before
    public void setUp() {
        ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();
        ShoppingCartPrinter shoppingCartPrinter = new ShoppingCartPrinter(console);

        ProductsRepository productsRepository = new ProductsRepository();
        Product chocolateBarProduct = Product.of(CHOCOLATE_BAR, Amount.valueOf(2.0), Discount.empty());
        Product baguetteProduct = Product.of(BAGUETTE, Amount.valueOf(0.8), Discount.of(3, 20));
        productsRepository.addProduct(ProductId.of(CHOCOLATE_BAR_ID), chocolateBarProduct);
        productsRepository.addProduct(ProductId.of(BAGUETTE_ID), baguetteProduct);

        Calculator calculator = new Calculator();
        TicketBuilder ticketBuilder = new TicketBuilder(productsRepository, calculator);

        shoppingCartService = new ShoppingCartService(shoppingCartRepository, clock, shoppingCartPrinter, ticketBuilder);
        inOrder = Mockito.inOrder(console);
    }

    @Test
    public void should_print_a_ticket_with_single_items_and_without_discount() {
        given(clock.now()).willReturn(LocalDateTime.of(2018, 1, 10, 14, 0, 0));
        shoppingCartService.addItem(ProductId.of(CHOCOLATE_BAR_ID), 1);
        shoppingCartService.addItem(ProductId.of(BAGUETTE_ID), 1);

        shoppingCartService.printTicket();

        inOrder.verify(console).print("10/01/2018 14:00:00");
        inOrder.verify(console).print("PRODUCTS:");
        inOrder.verify(console).print(" Chocolate bar       2.00");
        inOrder.verify(console).print(" Baguette            0.80");
        inOrder.verify(console).print("TOTAL: 2.80");
    }

    @Test
    public void should_print_a_ticket_with_multiple_items_and_without_discount() {
        given(clock.now()).willReturn(LocalDateTime.of(2018, 1, 10, 14, 0, 0));
        shoppingCartService.addItem(ProductId.of(CHOCOLATE_BAR_ID), 2);
        shoppingCartService.addItem(ProductId.of(BAGUETTE_ID), 1);

        shoppingCartService.printTicket();

        inOrder.verify(console).print("10/01/2018 14:00:00");
        inOrder.verify(console).print("PRODUCTS:");
        inOrder.verify(console).print(" Chocolate bar       4.00");
        inOrder.verify(console).print(" 2 x 2.00");
        inOrder.verify(console).print(" Baguette            0.80");
        inOrder.verify(console).print("TOTAL: 4.80");
    }

    @Test
    public void should_print_a_ticket_with_multiple_items_and_discounts() {
        given(clock.now()).willReturn(LocalDateTime.of(2018, 1, 10, 14, 0, 0));
        shoppingCartService.addItem(ProductId.of(CHOCOLATE_BAR_ID), 1);
        shoppingCartService.addItem(ProductId.of(BAGUETTE_ID), 5);

        shoppingCartService.printTicket();

        inOrder.verify(console).print("10/01/2018 14:00:00");
        inOrder.verify(console).print("PRODUCTS:");
        inOrder.verify(console).print(" Chocolate bar       2.00");
        inOrder.verify(console).print(" Baguette            4.00");
        inOrder.verify(console).print(" 5 x 0.80");
        inOrder.verify(console).print(" Baguette discount  -0.48");
        inOrder.verify(console).print("TOTAL: 5.52");
    }
}
