package rdc.ericwangi.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rdc.ericwangi.banking.dto.TransactionDto;
import rdc.ericwangi.banking.dto.TransactionSumDetails;
import rdc.ericwangi.banking.models.Transaction;
import rdc.ericwangi.banking.models.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findAllByUserId(Integer userId);

    @Query("select sum(t.montant) from Transaction t where t.user.id =:userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(t.montant) as montant from Transaction t " +
            "where t.user.id =:userId and t.type=:transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.transactionDate as transactionDate, sum(t.montant) as montant from Transaction t " +
            "where t.user.id =:userId and t.dateCreation between :dateDebut and :dateFin  group by t.transactionDate")
    List<TransactionSumDetails> findSumTransactionByDate(LocalDateTime dateDebut, LocalDateTime dateFin, Integer userId);
}