package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "CATEGORIES")
public class Category implements CsvSerializable {

    @Id
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_CATEGORY_ID")
    private Integer parentCategoryId;

    @Override
    public String csvRow() {
        return null;
    }

    @Override
    public String csvHeader() {
        return null;
    }
}
