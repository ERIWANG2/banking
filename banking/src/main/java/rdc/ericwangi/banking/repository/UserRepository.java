package rdc.ericwangi.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import rdc.ericwangi.banking.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    //selec * from User from prenom='Eric'
    List<User>findAllByPrenom(String prenom);

    //select * from User prenom = '%Eric%'
    List<User>findAllByPrenomContaining(String prenom);

    //Pour igniorer la case dans la recherche
    List<User>findAllByPrenomContainingIgnoreCase(String prenom);

    //list des utilisateurs dont le compte a un iban(cas de jointure entre user et account)
    //select * from user u inner join account a on u.id = a.id_user and a.iban = 'D12344'
    List<User>findAllByAccountIban(String iban);

    //select * from user where prenom = '%ERIC%' and email= 'eriwang2@gmail.com'
    User findByPrenomContainingIgnoreCaseAndEmail(String prenom,String email);

    @Query("from User where prenom = :p")
    List<User>rechercheByPrenom(@Param("p") String prenom);

    @Query("from User where prenom = '%:prenom%'")
    List<User>rechercheByPrenomContaining(String prenom);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User>rechercheByIban(String iban);

    @Query(value = "select * from _user u inner join account a on u.id = a.id_user and a.iban =:iban",nativeQuery = true)
    List<User>rechercheByIbanNative(String iban);

    Optional<User>findByEmail(String email);
}
