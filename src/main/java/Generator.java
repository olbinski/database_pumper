import pumpers.*;


public class Generator {

    public static void main(String[] args) {

        runCompany();
        runProduct();
        runStoresProduct();
        runInvoice();
    }

    private static void runCompany() {
        CompanyAndStorePumper companyAndStorePumper = new CompanyAndStorePumper();
        companyAndStorePumper.pump();
    }

    private static void runProduct() {
        ProductAndCategoriesPumper productAndCategoriesPumper = new ProductAndCategoriesPumper();
        productAndCategoriesPumper.pump();
    }

    private static void runStoresProduct() {
        StoresProductPumper storesProductPumper = new StoresProductPumper();
        storesProductPumper.pump();
    }

    private static void runInvoice() {
        PriceListPumper priceListPumper = new PriceListPumper();
        priceListPumper.pump();
        InvoicePumper invoicePumper = new InvoicePumper(priceListPumper.getProductWithPrices());
        invoicePumper.pump();
    }
}
