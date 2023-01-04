package rdc.ericwangi.banking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rdc.ericwangi.banking.models.Contact;
import rdc.ericwangi.banking.models.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
public class ContactDto {

    private Integer id;
    private String nom;
    private String email;
    private String iban;
    private Integer userId;

    public static ContactDto fromEntity(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .nom(contact.getNom())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact toEntity(ContactDto contact){
        return Contact.builder()
                .id(contact.getId())
                .nom(contact.getNom())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(
                        User.builder()
                                .id(contact.getUserId())
                                .build()
                )
                .build();
    }

}
