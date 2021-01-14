package org.entretien.kata.amounts;

public enum Currency {
    EURO("€");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
