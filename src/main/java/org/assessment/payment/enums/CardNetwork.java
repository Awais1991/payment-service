package org.assessment.payment.enums;


public enum CardNetwork {
    VISA("^4[0-9]{12}(?:[0-9]{3})?$"),
    MASTERCARD("^5[1-5][0-9]{14}$"),
    AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
    DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$"),
    JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),
    UNKNOWN(".*");

    private final String regex;

    CardNetwork(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}

