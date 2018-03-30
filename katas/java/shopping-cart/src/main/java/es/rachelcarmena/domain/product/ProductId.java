package es.rachelcarmena.domain.product;

import java.util.Objects;

public class ProductId {
    private int id;

    private ProductId(int id) {
        this.id = id;
    }

    public static ProductId of(int id) {
        return new ProductId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return id == productId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
