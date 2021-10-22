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
public class InvoiceItem {

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
}
