package pumpers;

import com.github.javafaker.Faker;
import entity.CsvSerializable;
import entity.Product;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ProductPumper extends AbstractPumper {

    private static final int AMOUNT = 100000;
    private static final int CATEGORY_AMOUNT = 100;

    @Override
    protected List<CsvSerializable> pump() {
        long start = new Date().getTime();
        var list = new ArrayList<CsvSerializable>();
        Random random = new Random();

        for (int i = 0; i < AMOUNT; i++) {
            Faker faker = new Faker();
            String name = faker.commerce().productName();
            String barcode = faker.commerce().promotionCode(15);
            Instant creationDate = Instant.now();
            Instant modifiedDate = Instant.now();

            list.add(Product.builder()
                    .productId(i)
                    .categoryId(random.nextInt(CATEGORY_AMOUNT))
                    .barcode(barcode)
                    .creationDate(Timestamp.from(creationDate))
                    .modifiedDate(Timestamp.from(modifiedDate))
                    .name(name)
                    .published(true)
                    .build()
            );

        }


        long end = new Date().getTime();
        System.out.println("ProductPumper took: " + ((end - start) / 1000) + " seconds");
        return list;
    }
}
