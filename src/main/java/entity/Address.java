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
public class Address {

    @Id
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "VOIVODESHIP")
    private String voivodeship;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "STREET")
    private String street;

    @Column(name = "ADDRESS_NUMBER")
    private String addressNumber;
}
