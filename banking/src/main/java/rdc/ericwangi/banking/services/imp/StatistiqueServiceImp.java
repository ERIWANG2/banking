package rdc.ericwangi.banking.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rdc.ericwangi.banking.dto.TransactionSumDetails;
import rdc.ericwangi.banking.models.TransactionType;
import rdc.ericwangi.banking.repository.TransactionRepository;
import rdc.ericwangi.banking.services.StatistiqueService;
import rdc.ericwangi.banking.validators.ObjectsValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatistiqueServiceImp implements StatistiqueService {

    private final TransactionRepository transactionRepository;
    private final ObjectsValidator validator;

    @Override
     public List<TransactionSumDetails> findSumTransactionByDate(
             LocalDate dateDebut, LocalDate dateFin, Integer userId) {
        LocalDateTime debut= LocalDateTime.of(dateDebut, LocalTime.of(0,0,0));
        LocalDateTime fin= LocalDateTime.of(dateFin, LocalTime.of(23,59,59));
        return transactionRepository.findSumTransactionByDate(debut,fin,userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId,TransactionType.DEPOT);
    }

    @Override
    public BigDecimal highestRetrait(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId,TransactionType.RETRAIT);
    }
}
