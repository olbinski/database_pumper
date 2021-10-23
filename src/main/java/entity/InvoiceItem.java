package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "INVOCIE_ITEMS")
public class InvoiceItem implements CsvSerializable {

    @Id
    @Column(name = "INVOICE_ITEM_ID")
    private Integer invoiceItemId;

    @Column(name = "INVOICE_iD")
    private Integer invoiceId;

    @Column(name = "ITEM_ID")
    private Integer itemId;

    @Column(name = "NET_PRICE")
    private BigDecimal netPrice;

    @Column(name = "VAT")
    private BigDecimal vat;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;


    public String insertSql() {
        return String.format("insert into INVOCIE_ITEMS (INVOICE_ITEM_ID, INVOICE_iD, ITEM_ID, NET_PRICE, VAT, QUANTITY, DISCOUNT  ) values (%s, '%s', %s, '%s', %s, '%s', %s);\n",
                invoiceItemId, invoiceId, itemId, netPrice, vat, quantity, discount);
    }

    public String csvRow() {
        return String.format("%s, %s, %s, %s, %s, %s, %s\n",
                invoiceItemId, invoiceId, itemId, netPrice, vat, quantity, discount);
    }

    public String csvHeader() {
        return String.format("%s, %s, %s, %s, %s, %s, %s\n",
                "INVOICE_ITEM_ID", "INVOICE_iD", "ITEM_ID", "NET_PRICE", "VAT", "QUANTITY", "DISCOUNT");
    }
}
