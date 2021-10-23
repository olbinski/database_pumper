package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "STORES_PRODUCTS")
public class StoresProduct implements CsvSerializable {

    @Id
    @Column(name = "STORES_PRODUCT_ID")
    private Integer storesProductId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "AVAILABLE")
    private Integer available;

    @Column(name = "MIN_QUANTITY")
    private Integer minQuantity;

    @Column(name = "MAX_QUANTITY")
    private Integer maxQuantity;

    @Column(name = "LAST_REPLENISHMENT_DATE")
    private Timestamp lastReplenishmentDate;

    @Override
    public String csvRow() {
        return String.format("%s, %s, %s, %s, %s, %s\n",
                storesProductId, productId, available, minQuantity, maxQuantity, lastReplenishmentDate);
    }

    @Override
    public String csvHeader() {
        return String.format("%s, %s, %s, %s, %s, %s\n",
                "STORES_PRODUCT_ID", "PRODUCT_ID", "AVAILABLE", "MIN_QUANTITY", "MAX_QUANTITY", "LAST_REPLENISHMENT_DATE");
    }
}
