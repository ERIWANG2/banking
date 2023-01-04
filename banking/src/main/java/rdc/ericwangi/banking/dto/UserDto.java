package rdc.ericwangi.banking.dto;

import lombok.*;
import rdc.ericwangi.banking.models.*;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;
    @NotNull(message = "le prénom ne doit pas être vide")
    @NotEmpty(message = "le prénom ne doit pas être vide")
    @NotBlank(message = "le prénom ne doit pas être vide")
    private String prenom;

    @NotNull
    @NotEmpty
    @NotBlank
    private String nom;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=5,max = 10)
    private String password;

    @Past
    private LocalDateTime datenaissance;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .prenom(user.getPrenom())
                .nom(user.getNom())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto user){
        return User.builder()
                .id(user.getId())
                .prenom(user.getPrenom())
                .nom(user.getNom())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }


}
