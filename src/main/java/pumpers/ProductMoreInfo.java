package pumpers;

import com.github.javafaker.Faker;
import config.PumperConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ProductMoreInfo {

    public static void main(String[] args) {
        List<String> colors = List.of("WHITE", "GREEN", "BLACK", "RED", "BLUE", "YELLOW", "GRAY");

        try {

            var random = new Random();

            File file = new File("data/update_files.sql");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (int i = 0; i < PumperConfig.PRODUCT_AMOUNT; i++) {

                var color = colors.get(random.nextInt(colors.size()));
                var material = new Faker().commerce().material();
                var weight = random.nextDouble() * 10;

                var sql = String.format(java.util.Locale.US, "update PRODUCTS set COLOR='%s', MATERIAL='%s', WEIGHT=%.2f where PRODUCT_ID=%s;\n", color, material.toUpperCase(Locale.ROOT), weight, i);

                bufferedWriter.write(sql);
            }

            bufferedWriter.flush();
            bufferedWriter.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
