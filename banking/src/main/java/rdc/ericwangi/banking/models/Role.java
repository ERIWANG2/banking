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
public class Role extends  AbstractEntity{

    private String nomRole;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
