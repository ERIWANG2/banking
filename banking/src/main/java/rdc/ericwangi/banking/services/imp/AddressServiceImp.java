package rdc.ericwangi.banking.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rdc.ericwangi.banking.dto.AddressDto;
import rdc.ericwangi.banking.models.Address;
import rdc.ericwangi.banking.repository.AddressRepository;
import rdc.ericwangi.banking.services.AddressService;
import rdc.ericwangi.banking.validators.ObjectsValidator;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final ObjectsValidator validator;


    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address adresse=AddressDto.fromEntity(dto);
        return addressRepository.save(adresse).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto :: fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto :: fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Adresse n'existe pas avec le ID:"+id));
    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }
}
