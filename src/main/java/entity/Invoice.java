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
public class Invoice implements CsvSerializable {

    @Id
    @Column(name = "INVOICE_ID")
    private Integer invoiceId;

    @Column(name = "INVOICE_DATE")
    private Timestamp invoiceDate;

    @Column(name = "supply_date")
    private Timestamp supplyDate;

    @Column(name = "SUPPLIER_ID")
    private Integer supplierId;

    @Column(name = "CLIENT_ID")
    private Integer clientId;


    public String csvRow() {
        return String.format("%s;%s;%s;%s;%s\n",
                invoiceId, invoiceDate, supplyDate, supplierId, clientId);
    }

    @Override
    public String csvHeader() {
        return String.format("%s;%s;%s;%s;%s\n",
                "INVOICE_ID", "INVOICE_DATE", "SUPPLY_DATE", "SUPPLIER_ID", "CLIENT_ID");
    }


}
