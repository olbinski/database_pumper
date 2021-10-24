package pumpers;

import com.github.javafaker.Faker;
import config.PumperConfig;
import entity.Category;
import entity.Product;
import me.tongfei.progressbar.ProgressBar;
import utils.Utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class ProductAndCategoriesPumper extends AbstractPumper {

    private final List<Integer> topLevelCategories = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    private final List<Integer> secondLevelCategories = Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
            19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
            29, 30, 31, 32, 33, 34, 35, 36, 37, 38);

    private final List<Integer> lastLevelCategories = Arrays.asList(39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
            49, 50, 51, 52, 53, 54, 55, 56, 57, 58,
            59, 60, 61, 62, 63, 64, 65, 66, 67, 68,
            69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
            79, 80, 81, 82, 83, 84, 85, 86, 87, 88,
            89, 90, 91, 92, 93, 94, 95, 96, 97, 98,
            99, 100);


    Random random = new Random();

    public List<Category> createCategories() {

        List<Category> categories = new ArrayList<>();
        for (Integer topLevelCategory : topLevelCategories) {
            Faker faker = new Faker();
            Category category = Category.builder()
                    .categoryId(topLevelCategory)
                    .name(faker.company().industry())
                    .build();

            categories.add(category);
        }

        List<Integer> topLevelWithPriority = new ArrayList<>(topLevelCategories);
        topLevelWithPriority.add(4);
        topLevelWithPriority.add(4);
        topLevelWithPriority.add(7);
        topLevelWithPriority.add(7);
        topLevelWithPriority.add(2);
        Collections.shuffle(topLevelWithPriority);

        for (Integer secondLevelCategory : secondLevelCategories) {
            Faker faker = new Faker();
            var category = Category.builder()
                    .name(faker.commerce().promotionCode(0) + " " + faker.commerce().material())
                    .categoryId(secondLevelCategory)
                    .parentCategoryId(topLevelWithPriority.get(random.nextInt(topLevelWithPriority.size())))
                    .build();

            categories.add(category);
        }


        List<Integer> secondLevelWithPriority = new ArrayList<>(topLevelCategories);
        secondLevelWithPriority.add(20);
        secondLevelWithPriority.add(20);
        secondLevelWithPriority.add(20);
        secondLevelWithPriority.add(13);
        secondLevelWithPriority.add(13);
        secondLevelWithPriority.add(15);
        secondLevelWithPriority.add(15);
        secondLevelWithPriority.add(15);
        secondLevelWithPriority.add(30);
        secondLevelWithPriority.add(30);
        secondLevelWithPriority.add(30);
        secondLevelWithPriority.add(30);


        for (Integer lastLevelCategory : lastLevelCategories) {
            Faker faker = new Faker();
            var category = Category.builder()
                    .name(faker.commerce().promotionCode(0) + " " + faker.commerce().material())
                    .categoryId(lastLevelCategory)
                    .parentCategoryId(secondLevelWithPriority.get(random.nextInt(secondLevelWithPriority.size())))
                    .build();
            categories.add(category);
        }
        return categories;
    }

    @Override
    public void pump() {

        List<Category> categories = createCategories();
        List<Product> products = createProducts();

        writer.write(categories);
        writer.write(products);

    }

    public List<Product> createProducts() {
        var products = new ArrayList<Product>();
        Random random = new Random();

        List<Integer> categoriesIdsWithPriorities = new ArrayList<>(lastLevelCategories);
        for (Integer lastLevelCategory : lastLevelCategories) {
            var times = lastLevelCategory % 5;
            for (int i = 0; i < times; i++) {
                categoriesIdsWithPriorities.add(lastLevelCategory);
            }
        }

        try (ProgressBar pb = new ProgressBar("Products", PumperConfig.PRODUCT_AMOUNT)) { // name, initial max
            for (int i = 0; i < PumperConfig.PRODUCT_AMOUNT; i++) {
                Faker faker = new Faker();
                String name = faker.commerce().productName();
                String barcode = faker.regexify("([0-9]){15}");
                Instant creationDate = Instant.ofEpochSecond(Utils.EPOCH_FROM + random.nextInt(Utils.DIFFERENCE));
                Instant modifiedDate = Instant.now();

                products.add(Product.builder()
                        .productId(i)
                        .categoryId(categoriesIdsWithPriorities.get(random.nextInt(categoriesIdsWithPriorities.size())))
                        .barcode(barcode)
                        .creationDate(Timestamp.from(creationDate))
                        .modifiedDate(Timestamp.from(modifiedDate))
                        .name(name)
                        .published(true)
                        .build()
                );
                pb.step();
            }
        }
        return products;
    }
}
