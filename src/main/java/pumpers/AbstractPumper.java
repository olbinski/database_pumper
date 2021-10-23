package pumpers;

import entity.CsvSerializable;
import utils.CscWriter;

import java.io.IOException;
import java.util.List;

public abstract class AbstractPumper {

    CscWriter writer = new CscWriter();


    public AbstractPumper() {
    }

    public void run() {
        List<CsvSerializable> pump = pump();
        try {
            writer.write(pump);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract List<CsvSerializable> pump();
}
