package Gildas.emailProjet.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageDTOs {

    private ContactMessageDTO contactMessageDTO;
    private int code;
    private String message;

}
