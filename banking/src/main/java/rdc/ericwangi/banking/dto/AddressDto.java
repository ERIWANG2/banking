package rdc.ericwangi.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rdc.ericwangi.banking.models.Address;
import rdc.ericwangi.banking.models.User;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDto {
    private Integer id;
    private String rue;
    private String numeroMaison;
    private String commune;
    private String quartier;
    private String ville;
    private Integer userId;

    public static AddressDto fromEntity(Address adress){
        return AddressDto.builder()
                .id(adress.getId())
                .rue(adress.getRue())
                .numeroMaison(adress.getNumeroMaison())
                .commune(adress.getCommune())
                .quartier(adress.getQuartier())
                .ville(adress.getVille())
                .userId(adress.getUser().getId())
                .build();
    }


    public static Address fromEntity(AddressDto adress){
        return Address.builder()
                .id(adress.getId())
                .rue(adress.getRue())
                .numeroMaison(adress.getNumeroMaison())
                .commune(adress.getCommune())
                .quartier(adress.getQuartier())
                .ville(adress.getVille())
                .user(
                        User.builder()
                                .id(adress.getUserId())
                                .build()
                  )
                .build();
    }

}
