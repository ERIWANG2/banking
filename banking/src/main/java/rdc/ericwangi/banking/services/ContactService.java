package rdc.ericwangi.banking.services;

import rdc.ericwangi.banking.dto.AddressDto;
import rdc.ericwangi.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{

    List<ContactDto> findAllByUserId(Integer userId);

}
