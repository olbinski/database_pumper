package utils;

import entity.CsvSerializable;
import me.tongfei.progressbar.ProgressBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CscWriter {


    public void write(List<? extends CsvSerializable> rows) {
        try {

            String classSimpleName = rows.get(0).getClass().getSimpleName();
            File file = new File("data/" + classSimpleName + ".csv");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            writer.write(rows.get(0).csvHeader());

            try (ProgressBar pb = new ProgressBar("Stores generator", rows.size())) { // name, initial max

                for (int i = 0; i < rows.size(); i++) {
                    var row = rows.get(i);
                    rows.set(i, null);
                    bufferedWriter.write(row.csvRow());

                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
