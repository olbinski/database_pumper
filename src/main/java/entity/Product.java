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
@Table(name = "PRODUCT")
public class Product implements CsvSerializable {

    @Id
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate;

    @Column(name = "published")
    private Boolean published;

    @Override
    public String csvRow() {
        return null;
    }

    @Override
    public String csvHeader() {
        return null;
    }
}
