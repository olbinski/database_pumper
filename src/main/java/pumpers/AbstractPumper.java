package pumpers;

import entity.CsvSerializable;
import utils.CscWriter;

import java.io.IOException;
import java.util.List;

public abstract class AbstractPumper {

    CscWriter writer = new CscWriter();


    public AbstractPumper() {
    }


    public abstract void pump();
}
