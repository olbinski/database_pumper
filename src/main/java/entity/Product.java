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

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "BARCODE", length = 20)
    private String barcode;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate;

    @Column(name = "PUBLISHED")
    private Boolean published;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Override
    public String csvRow() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
                productId, name, barcode, categoryId, creationDate, modifiedDate, published ? 1 : 0, description);
    }

    @Override
    public String csvHeader() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
                "PRODUCT_ID", "NAME", "BARCODE", "CATEGORY_ID", "CREATION_DATE", "MODIFIED_DATE", "PUBLISHED", "DESCRIPTION");
    }
}
