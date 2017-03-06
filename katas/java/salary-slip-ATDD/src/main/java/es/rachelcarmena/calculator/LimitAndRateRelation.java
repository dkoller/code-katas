package es.rachelcarmena.calculator;

import es.rachelcarmena.domain.Amount;

public class LimitAndRateRelation {

    protected Amount limit;
    protected int rate;

    public LimitAndRateRelation(Amount limit, int rate) {
        this.limit = limit;
        this.rate = rate;
    }
}
