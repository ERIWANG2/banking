package rdc.ericwangi.banking.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Transaction extends AbstractEntity{

    private BigDecimal montant;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;
    @Column(updatable = false)
    private LocalDate transactionDate;
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;
}
