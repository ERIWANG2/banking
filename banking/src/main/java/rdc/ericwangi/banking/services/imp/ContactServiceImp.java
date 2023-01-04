package rdc.ericwangi.banking.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rdc.ericwangi.banking.dto.ContactDto;
import rdc.ericwangi.banking.repository.ContactRepository;
import rdc.ericwangi.banking.services.ContactService;
import rdc.ericwangi.banking.validators.ObjectsValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImp implements ContactService {

    private final ContactRepository contactRepository;
    private final ObjectsValidator validator;

    @Override
    public Integer save(ContactDto dto) {
        return null;
    }

    @Override
    public List<ContactDto> findAll() {
        return null;
    }

    @Override
    public ContactDto findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return contactRepository.findByUserId(userId)
                .stream()
                .map(ContactDto ::fromEntity)
                .collect(Collectors.toList());
    }
}
