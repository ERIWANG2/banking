package rdc.ericwangi.banking.services;

import rdc.ericwangi.banking.dto.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface StatistiqueService {

    List<TransactionSumDetails> findSumTransactionByDate(LocalDate dateDebut, LocalDate dateFin, Integer userId);

    //solde compte de l'utilisateur
    BigDecimal getAccountBalance(Integer userId);

    //Montant plus élevé transferer
    BigDecimal highestTransfert(Integer userId);

    //Montant plus élevé déposé
    BigDecimal highestDeposit(Integer userId);

    //Montant plus élevé du retrait
    BigDecimal highestRetrait(Integer userId);


}
