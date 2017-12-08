package es.rachelcarmena.catalog;

import es.rachelcarmena.offers.AmountOffer;
import es.rachelcarmena.offers.Offer;
import es.rachelcarmena.offers.PackOffer;
import es.rachelcarmena.offers.ProductOffer;

import java.util.ArrayList;

public class Offers {

    private final ArrayList<Offer> offers;

    public Offers() {
        this.offers = new ArrayList<>();

        offers.add(new PackOffer(3, 45, 'Z', 'S', 'T', 'Y', 'X'));

        offers.add(new ProductOffer('E', 2, 'B'));
        offers.add(new ProductOffer('F', 2, 'F'));
        offers.add(new ProductOffer('N', 3, 'M'));
        offers.add(new ProductOffer('R', 3, 'Q'));
        offers.add(new ProductOffer('U', 3, 'U'));

        offers.add(new AmountOffer('A', 5, 200));
        offers.add(new AmountOffer('A', 3, 130));
        offers.add(new AmountOffer('B',2, 45));
        offers.add(new AmountOffer('H',10, 80));
        offers.add(new AmountOffer('H',5, 45));
        offers.add(new AmountOffer('K', 2, 120));
        offers.add(new AmountOffer('P', 5, 200));
        offers.add(new AmountOffer('Q', 3, 80));
        offers.add(new AmountOffer('V', 3, 130));
        offers.add(new AmountOffer('V', 2, 90));
    }

    public ArrayList<Offer> getList() {
        return offers;
    }
}
