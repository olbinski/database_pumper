package config;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PumperConfig {

    public static final int PRODUCT_AMOUNT = 997;
    public static final int COMPANIES_AMOUNT = 10000;
    public static final int STORE_COMPANIES_AMOUNT = 10;
    public static final int STORES_AMOUNT = 50;
    public static final int ADDRESSES_AMOUNT = COMPANIES_AMOUNT + STORES_AMOUNT;
    public static final int INVOICE_AMOUNT = 5000000;
    public static final int MAX_ITEMS_PER_INVOICE_AMOUNT = 20;
    public static final double DISCOUNT_CHANCE = 0.1;
    public static final List<BigDecimal> DISCOUNT_POSSIBILITIES = Arrays.asList(new BigDecimal("0.1"),
            new BigDecimal("0.15"),
            new BigDecimal("0.05"),
            new BigDecimal("0.2"));

}

