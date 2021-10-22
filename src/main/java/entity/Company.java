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
public class Company {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NIP")
    private String nip;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OWNER_FIRST_NAME")
    private String ownerFirstName;

    @Column(name = "OWNER_LAST_NAME")
    private String owenerLastName;

    @Column(name = "ADRESS_ID")
    private Integer addressId;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;
}
