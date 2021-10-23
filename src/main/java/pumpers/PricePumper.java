package pumpers;

import config.PumperConfig;
import entity.CsvSerializable;
import me.tongfei.progressbar.ProgressBar;

import java.util.List;

public class PricePumper extends AbstractPumper {
    @Override
    protected List<CsvSerializable> pump() {


        try (ProgressBar pb = new ProgressBar("Price generator", PumperConfig.INVOICE_AMOUNT)) { // name, initial max
            // Use ProgressBar("Test", 100, ProgressBarStyle.ASCII) if you want ASCII output style

            for (int run = 0; run < PumperConfig.INVOICE_AMOUNT; run++) {


                pb.step();
            }
        } // progress bar stops automatically after completion of try-with-resource block
        return null;
    }
}
