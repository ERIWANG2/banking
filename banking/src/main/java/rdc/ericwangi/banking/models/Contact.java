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
public class Contact {
    @Id
    @GeneratedValue
    private Integer id;
    private String nom;
    private String email;
    private String iban;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;
}
