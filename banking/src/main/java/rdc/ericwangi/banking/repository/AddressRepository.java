package rdc.ericwangi.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rdc.ericwangi.banking.models.Address;

public interface AddressRepository extends JpaRepository <Address,Integer>{
}
