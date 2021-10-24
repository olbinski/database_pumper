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
@Table(name = "COMPANY")
public class Company implements CsvSerializable {

    @Id
    @Column(name = "COMPANY_ID")
    private Integer companyId;

    @Column(name = "NIP")
    private String nip;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OWNER_FIRST_NAME")
    private String ownerFirstName;

    @Column(name = "OWNER_LAST_NAME")
    private String owenerLastName;

    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @Override
    public String csvRow() {
        return String.format("%s;%s;%s;%s;%s;%s;%s\n",
                companyId, nip, name, ownerFirstName, owenerLastName, addressId, creationDate);
    }

    @Override
    public String csvHeader() {
        return String.format("%s;%s;%s;%s;%s;%s;%s\n",
                "COMPANY_ID", "NIP", "NAME", "OWNER_FIRST_NAME", "OWNER_LAST_NAME", "ADDRESS_ID", "CREATION_DATE");
    }
}
