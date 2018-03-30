package es.rachelcarmena.unit;

import es.rachelcarmena.domain.shoppingCart.Item;
import es.rachelcarmena.domain.product.ProductId;
import es.rachelcarmena.repository.ShoppingCartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static es.rachelcarmena.acceptance.ShoppingCartServiceFeature.BAGUETTE_ID;
import static es.rachelcarmena.acceptance.ShoppingCartServiceFeature.CHOCOLATE_BAR_ID;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartRepositoryShould {

    private ShoppingCartRepository shoppingCartRepository;

    @Before
    public void setUp() {
        shoppingCartRepository = new ShoppingCartRepository();
    }

    @Test
    public void return_is_empty_when_no_products_in_shopping_cart() {
        assertTrue(shoppingCartRepository.isEmpty());
    }

    @Test
    public void return_is_not_empty_when_products_in_shopping_cart() {
        shoppingCartRepository.addItem(Item.of(ProductId.of(CHOCOLATE_BAR_ID), 1));

        assertFalse(shoppingCartRepository.isEmpty());
    }

    @Test
    public void init_shopping_cart_with_date() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 3, 2, 12, 3,4);
        shoppingCartRepository.initShoppingCart(localDateTime);

        assertThat(shoppingCartRepository.getDate(), is(localDateTime));
    }

    @Test
    public void return_the_list_of_items() {
        Item chocolateBarItem = Item.of(ProductId.of(CHOCOLATE_BAR_ID), 4);
        Item baguetteItem = Item.of(ProductId.of(BAGUETTE_ID), 5);
        List<Item> items = new ArrayList<>();
        items.add(chocolateBarItem);
        items.add(baguetteItem);

        shoppingCartRepository.addItem(chocolateBarItem);
        shoppingCartRepository.addItem(baguetteItem);

        assertTrue(reflectionEquals(shoppingCartRepository.getItems(), items));
    }
}
