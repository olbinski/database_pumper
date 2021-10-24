package pumpers;

import config.PumperConfig;
import dto.ProductDto;
import entity.Price;
import me.tongfei.progressbar.ProgressBar;
import utils.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class PriceListPumper extends AbstractPumper {

    private final Random random = new Random();
    private final List<ProductDto> productWithPrices = new ArrayList<ProductDto>();

    List<BigDecimal> vats = Arrays.asList(
            new BigDecimal("5.00"),
            new BigDecimal("8.00"),
            new BigDecimal("23.00")
    );

    public List<ProductDto> getProductWithPrices() {
        return productWithPrices;
    }

    @Override
    public void pump() {
        var prices = new ArrayList<Price>();
        try (ProgressBar pb = new ProgressBar("Products prices", PumperConfig.PRODUCT_AMOUNT)) { // name, initial max

            for (int producId = 0; producId < PumperConfig.PRODUCT_AMOUNT; producId++) {
                prices.addAll(generatePricesForProduct(producId));
                pb.step();
            }
        }

        Map<Integer, List<Price>> collect = prices.stream().collect(groupingBy(Price::getProductId));

        for (Map.Entry<Integer, List<Price>> integerListEntry : collect.entrySet()) {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(integerListEntry.getKey());
            productDto.setPriceList(integerListEntry.getValue());
            this.productWithPrices.add(productDto);
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

        prices.add(Price.builder()
                .productId(productId)
                .netPrice(netPrice)
                .vat(vat)
                .effectiveFrom(Timestamp.from(Instant.ofEpochSecond(Utils.EPOCH_FROM)))
                .build()
        );

        for (int i = 0; i < howMuch; i++) {

            netPrice = BigDecimal.valueOf(getPrice(basePrice));
            netPrice = netPrice.setScale(2, RoundingMode.HALF_UP);

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
