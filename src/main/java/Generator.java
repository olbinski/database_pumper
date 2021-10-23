import pumpers.*;

import java.util.Arrays;
import java.util.List;


public class Generator {

    public static void main(String[] args) {

        List<AbstractPumper> pumpers = Arrays.asList(
                new CompanyAndStorePumper(),
                new InvoicePumper()
        );

        for (AbstractPumper pumper : pumpers) {
//            pumper.pump();
        }

        var pumper = new CompanyAndStorePumper();

        pumper.pump();
    }
}
