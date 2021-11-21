package config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PumperConfig {

    public static final int PRODUCT_AMOUNT = 59700;
    public static final int COMPANIES_AMOUNT = 100000;
    public static final int STORE_COMPANIES_AMOUNT = 20;
    public static final int STORES_AMOUNT = 151;
    public static final int ADDRESSES_AMOUNT = COMPANIES_AMOUNT + STORES_AMOUNT;
    public static final int INVOICE_AMOUNT = 2000000;
    public static final int MAX_ITEMS_PER_INVOICE_AMOUNT = 20;
    public static final double DISCOUNT_CHANCE = 0.1;
    public static final List<BigDecimal> DISCOUNT_POSSIBILITIES = List.of(new BigDecimal("0.1"),
            new BigDecimal("0.15"),
            new BigDecimal("0.05"),
            new BigDecimal("0.2"));

    public static final List<Integer> productIdsWithPriorities = getProductPrioritiesOnInvoices();
    public static final List<Integer> clientPrioritiesOnInvoices = getCustomerPrioritiesOnInvoices();
    public static final List<Integer> supplierPrioritiesOnInvoices = getStoresPrioritiesOnInvoices();


    private static List<Integer> getProductPrioritiesOnInvoices() {
        return getIdsWithPopularity(PRODUCT_AMOUNT, 0.1, 0.4);
    }


    private static List<Integer> getCustomerPrioritiesOnInvoices() {
        return getIdsWithPopularity(COMPANIES_AMOUNT, 0.05, 0.3);
    }

    private static List<Integer> getStoresPrioritiesOnInvoices() {
        return getIdsWithPopularity(STORES_AMOUNT, 0.1, 0.2);
    }

    private static List<Integer> getIdsWithPopularity(int companiesAmount, double most, double middle) {
        Random random = new Random();

        int mostPopularCompaniesCount = (int) (companiesAmount * most);
        int middlePopularCompaniesCount = (int) (companiesAmount * middle);

        List<Integer> companiesIdsWithPopularity = new ArrayList<>();
        for (int i = 0; i < companiesAmount; i++) {
            companiesIdsWithPopularity.add(i);
        }

        Collections.shuffle(companiesIdsWithPopularity);

        for (int i = 0; i < mostPopularCompaniesCount; i++) {
            for (int j = 0; j < 30 + random.nextInt(10); j++) {
                companiesIdsWithPopularity.add(companiesIdsWithPopularity.get(i));
            }
        }

        for (int i = mostPopularCompaniesCount; i < middlePopularCompaniesCount; i++) {
            for (int j = 0; j < 10 + random.nextInt(5); j++) {
                companiesIdsWithPopularity.add(companiesIdsWithPopularity.get(i));
            }
        }

        Collections.shuffle(companiesIdsWithPopularity);
        return companiesIdsWithPopularity;
    }
}

