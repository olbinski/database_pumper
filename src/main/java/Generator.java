import pumpers.AbstractPumper;
import pumpers.CompanyAndStorePumper;
import pumpers.PriceListPumper;
import pumpers.ProductAndCategoriesPumper;

import java.util.Arrays;
import java.util.List;


public class Generator {

    public static void main(String[] args) {

        List<AbstractPumper> pumpers = Arrays.asList(
                new CompanyAndStorePumper(),
                new ProductAndCategoriesPumper(),
                new PriceListPumper()
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
