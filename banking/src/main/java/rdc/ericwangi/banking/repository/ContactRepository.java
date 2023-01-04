package rdc.ericwangi.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rdc.ericwangi.banking.models.Contact;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List<Contact> findByUserId(Integer id);
}
