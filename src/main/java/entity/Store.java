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
@Table(name = "STORES")
public class Store {

    @Id
    @Column(name = "STORE_ID")
    private Integer store_id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "OWNER_COMPANY_ID")
    private Integer ownerCompanyId;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

}
