import pumpers.*;

import java.util.Arrays;
import java.util.List;


public class Generator {

    public static void main(String[] args) {

        List<AbstractPumper> pumpers = Arrays.asList(
                new AddressPumpers(),
                new CategoryPumper(),
                new CompanyPumper(),
                new InvoicePumper(),
                new InvoiceItemPumper(),
                new PricePumper(),
                new ProductPumper(),
                new StorePumper(),
                new StoresProductPumper()
        );

        for (AbstractPumper pumper : pumpers) {
//            pumper.run();
        }

        var pumper = new InvoicePumper();

        pumper.run();
    }
}
