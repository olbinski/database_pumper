package dto;

import entity.Price;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int productId;
    private List<Price> priceList;

    public void setPriceList(List<Price> priceList) {
        this.priceList = new ArrayList<>(priceList);
        this.priceList.sort(Comparator.comparingLong(x -> x.getEffectiveFrom().getTime()));
    }

    public Price getPrice(Timestamp date) {
        return this.priceList.stream().filter(x -> !x.getEffectiveFrom().after(date)).findFirst().orElse(null);
    }
}
