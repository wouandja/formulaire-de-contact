package Gildas.emailProjet.Dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageDTO {

    private String name;
    private String email;
    private String number;
    private String message;

}
