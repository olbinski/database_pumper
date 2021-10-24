package pumpers;

import config.PumperConfig;
import entity.Price;
import me.tongfei.progressbar.ProgressBar;
import utils.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PriceListPumper extends AbstractPumper {

    private final Random random = new Random();

    List<BigDecimal> vats = Arrays.asList(
            new BigDecimal("5.00"),
            new BigDecimal("8.00"),
            new BigDecimal("23.00")
    );

    @Override
    public void pump() {
        var prices = new ArrayList<Price>();
        try (ProgressBar pb = new ProgressBar("Products prices", PumperConfig.PRODUCT_AMOUNT)) { // name, initial max

            for (int producId = 0; producId < PumperConfig.PRODUCT_AMOUNT; producId++) {
                prices.addAll(generatePricesForProduct(producId));
                pb.step();
            }
        }

        Collections.shuffle(prices);
        for (int i = 0; i < prices.size(); i++) {
            prices.get(i).setPriceListId(i);
        }

        writer.write(prices);
    }

    List<Price> generatePricesForProduct(int productId) {
        List<Price> prices = new ArrayList<>();

        var howMuch = random.nextInt(30);
        var basePrice = getPrice();
        BigDecimal vat = vats.get(random.nextInt(vats.size()));

        BigDecimal netPrice = BigDecimal.valueOf(getPrice(basePrice));
        netPrice = netPrice.setScale(2, RoundingMode.HALF_UP);

        for (int i = 0; i < howMuch; i++) {
            prices.add(Price.builder()
                    .productId(productId)
                    .netPrice(netPrice)
                    .vat(vat)
                    .effectiveFrom(Utils.getRandomTimestamp())
                    .build()
            );
        }
        return prices;
    }


    private double getPrice() {
        var rangeMin = 0.10;
        var rangeMax = 10000.10;
        return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }

    private double getPrice(double basePrice) {
        var rangeMin = 0.80 * basePrice;
        var rangeMax = 1.20 * basePrice;
        return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }
}
