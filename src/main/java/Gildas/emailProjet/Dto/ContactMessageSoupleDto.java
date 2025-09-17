package Gildas.emailProjet.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageSoupleDto {

    private String name;
    private String email;
    private String phone;
   private  String country;
    private String message;

}
