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
@Table(name = "ADDRESSES")
public class Address implements CsvSerializable {

    @Id
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "VOIVODESHIP", length = 100)
    private String voivodeship;

    @Column(name = "CITY", length = 100)
    private String city;

    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

    @Column(name = "STREET", length = 100)
    private String street;

    @Column(name = "ADDRESS_NUMBER", length = 255)
    private String addressNumber;

    @Override
    public String csvRow() {
        return String.format("%s;%s;%s;%s;%s;%s\n",
                addressId, voivodeship, city, postalCode, street, addressNumber);
    }

    @Override
    public String csvHeader() {
        return String.format("%s;%s;%s;%s;%s;%s\n",
                "ADDRESS_ID", "VOIVODESHIP", "CITY", "POSTAL_CODE", "STREET", "ADDRESS_NUMBER");
    }
}
