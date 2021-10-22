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
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @Column(name = "INVOICE_ID")
    private Integer invoiceId;

    @Column(name = "INVOICE_DATE")
    private Timestamp invoiceDate;

    @Column(name = "supply_date")
    private Timestamp supplyDate;

    @Column(name = "SUPPLIER_ID")
    private Timestamp supplierId;

    @Column(name = "CLIENT_ID")
    private Integer clientId;
}
