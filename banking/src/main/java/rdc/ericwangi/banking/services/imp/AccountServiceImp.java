package rdc.ericwangi.banking.services.imp;

import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;
import rdc.ericwangi.banking.dto.AccountDto;
import rdc.ericwangi.banking.exceptions.OperationNonPermittedException;
import rdc.ericwangi.banking.models.Account;
import rdc.ericwangi.banking.repository.AccountRepository;
import rdc.ericwangi.banking.services.AccountService;
import rdc.ericwangi.banking.validators.ObjectsValidator;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        /*if(dto.getId() !=null){
            throw new OperationNonPermittedException(
                    "Ce compte ne peut pas etre mis à jour",
                    "Enrgeistrement compte",
                    "Account",
                    "Mise à jour non autorisé"
            );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepository.
                findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount){
            throw new OperationNonPermittedException(
                    "C'est utilisateur a déjà un compte",
                    "Enregistrement compte",
                    "AccountService",
                    "Account création"
            );
        }
        //génération de iban
        if(dto.getId() ==null){
            account.setIban(generationRandomIban());
        }

        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto ::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto :: fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Ce compte n'existe pas avec ID:"+id));
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    public String generationRandomIban(){
        //générer un iban
        String iban = Iban.random(CountryCode.AE).toFormattedString();
        //verifier si iban existe ou pas
        boolean ibanExiste = accountRepository.findByIban(iban).isPresent();
        //si iban existe génére autre iban
        if (ibanExiste){
            generationRandomIban();
        }
        //si n'existe pas retournons comme resultat

        return iban;
    }
}
