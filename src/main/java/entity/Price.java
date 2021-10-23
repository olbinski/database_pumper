package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "PRICE_LIST")
public class Price implements CsvSerializable {

    @Id
    @Column(name = "PRICE_LIST_ID")
    private Integer priceListId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "NET_PRICE")
    private BigDecimal netPrice;

    @Column(name = "VAT")
    private BigDecimal vat;

    @Column(name = "EFFECTIVE_FROM")
    private Timestamp effectiveFrom;

    @Override
    public String csvRow() {
        return null;
    }

    @Override
    public String csvHeader() {
        return null;
    }
}
