package utils;

import entity.CsvSerializable;
import me.tongfei.progressbar.ProgressBar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CscWriter {


    public void write(List<CsvSerializable> rows) throws IOException {

        String classSimpleName = rows.get(0).getClass().getSimpleName();
        File file = new File("data/" + classSimpleName + ".csv");

        if(!file.exists()) {
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);

        writer.write(rows.get(0).csvHeader());

        for (CsvSerializable row : ProgressBar.wrap(rows, "Writing: " + classSimpleName)) {
            writer.write(row.csvRow());
        }

        writer.close();
    }
}
