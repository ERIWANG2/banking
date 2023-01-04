package rdc.ericwangi.banking.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rdc.ericwangi.banking.dto.TransactionDto;
import rdc.ericwangi.banking.models.Transaction;
import rdc.ericwangi.banking.models.TransactionType;
import rdc.ericwangi.banking.repository.TransactionRepository;
import rdc.ericwangi.banking.services.TransactionService;
import rdc.ericwangi.banking.validators.ObjectsValidator;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ObjectsValidator<TransactionDto> validator;


    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionType(transaction.getType()));
        BigDecimal montant = transaction.getMontant().multiply(transactionMultiplier);
        transaction.setMontant(montant);

        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDto ::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id)
                .map(TransactionDto ::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune transaction trouvé avec le ID:"+id)) ;
    }
    @Override
    public void delete(Integer id) {
        transactionRepository.deleteById(id);
    }
    public int getTransactionType(TransactionType type){
        return TransactionType.DEPOT == type ? 1 : -1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto :: fromEntity)
                .collect(Collectors.toList());
    }
}
