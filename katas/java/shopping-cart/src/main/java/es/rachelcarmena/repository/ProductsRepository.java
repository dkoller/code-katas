package es.rachelcarmena.repository;

import es.rachelcarmena.domain.product.Product;
import es.rachelcarmena.domain.product.ProductId;

import java.util.HashMap;
import java.util.Map;

public class ProductsRepository {

    Map<ProductId, Product> products = new HashMap<>();

    public Product getProduct(ProductId productId) {
        return products.get(productId);
    }

    public void addProduct(ProductId productId, Product product) {
        products.put(productId, product);
    }
}
