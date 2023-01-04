package rdc.ericwangi.banking.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Address extends AbstractEntity {

    private String rue;
    private String numeroMaison;
    private String commune;
    private String quartier;
    private String ville;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
