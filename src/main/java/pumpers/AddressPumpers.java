package pumpers;


import config.PumperConfig;
import entity.Address;
import entity.CsvSerializable;
import me.tongfei.progressbar.ProgressBar;

import java.util.List;

public class AddressPumpers extends AbstractPumper {


    public AddressPumpers() {
        super();
    }

    @Override
    protected List<CsvSerializable> pump() {
        var address = Address.builder().addressId(1).build();


        try (ProgressBar pb = new ProgressBar("Address generator", PumperConfig.INVOICE_AMOUNT)) { // name, initial max
            // Use ProgressBar("Test", 100, ProgressBarStyle.ASCII) if you want ASCII output style

            for (int run = 0; run < PumperConfig.INVOICE_AMOUNT; run++) {


                pb.step();
            }
        } // progress bar stops automatically after completion of try-with-resource block
        return null;
    }
}
