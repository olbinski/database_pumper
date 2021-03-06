package pumpers;

import config.PumperConfig;
import entity.StoresProduct;
import me.tongfei.progressbar.ProgressBar;
import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StoresProductPumper extends AbstractPumper {

    Random random = new Random();

    @Override
    public void pump() {
        ArrayList<StoresProduct> storeProducts = generateStoresProducts();

        Collections.shuffle(storeProducts);

        for (int i = 0; i < storeProducts.size(); i++) {
            storeProducts.get(i).setStoresProductId(i);
        }

        writer.write(storeProducts);
    }

    private ArrayList<StoresProduct> generateStoresProducts() {
        var storeProducts = new ArrayList<StoresProduct>();

        try (ProgressBar pb = new ProgressBar("Store products", PumperConfig.PRODUCT_AMOUNT)) { // name, initial max

            for (int productId = 0; productId < PumperConfig.PRODUCT_AMOUNT; productId++) {
                for (int storeId = 0; storeId < PumperConfig.STORES_AMOUNT; storeId++) {

                    var minQuantity = random.nextInt(10) + 2;
                    var maxQuantity = random.nextInt(100) + minQuantity;
                    var avaliable = minQuantity + random.nextInt(maxQuantity = minQuantity);


                    StoresProduct storeProduct = StoresProduct.builder()
                            .storeId(storeId)
                            .productId(productId)
                            .available(avaliable)
                            .minQuantity(minQuantity)
                            .maxQuantity(maxQuantity)
                            .lastReplenishmentDate(Utils.getRandomTimestampFromSecondHalf())
                            .build();
                    storeProducts.add(storeProduct);
                }
                pb.step();
            }
        }
        return storeProducts;
    }
}
