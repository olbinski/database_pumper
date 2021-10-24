import pumpers.*;

import java.util.Arrays;
import java.util.List;


public class Generator {

    public static void main(String[] args) {
        CompanyAndStorePumper companyAndStorePumper = new CompanyAndStorePumper();
        ProductAndCategoriesPumper productAndCategoriesPumper = new ProductAndCategoriesPumper();
        PriceListPumper priceListPumper = new PriceListPumper();
        InvoicePumper invoicePumper = new InvoicePumper(priceListPumper.getProductWithPrices());

        List<AbstractPumper> pumpers = Arrays.asList(
                companyAndStorePumper,
                productAndCategoriesPumper,
                priceListPumper
//                new InvoicePumper()
        );

        for (AbstractPumper pumper : pumpers) {
            pumper.pump();
        }

//        var pumper = new ProductAndCategoriesPumper();
//
//        pumper.pump();
    }
}
