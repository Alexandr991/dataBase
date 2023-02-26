package app;

public enum Currency {
    EURO("EUR"),
    DOLLAR("USD"),
    RUSSIAN_RUBLE("RUB"),
    BELARUSIAN_RUBLE("BYN");
    private String currencyType;

    Currency(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public static Currency  currencySelection(int select) {
        Currency type = null;
        switch (select) {
            case 1:
                type = EURO;
                break;
            case 2:
                type = DOLLAR;
                break;
            case 3:
                type = RUSSIAN_RUBLE;
                break;
            case 4:
                type = BELARUSIAN_RUBLE;
                break;


        }
     return type;

    }

}
