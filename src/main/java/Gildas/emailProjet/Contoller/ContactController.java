package Gildas.emailProjet.Contoller;


import Gildas.emailProjet.Dto.ContactMessageDTO;
import Gildas.emailProjet.Dto.ContactMessageDTOs;
import Gildas.emailProjet.Dto.ContactMessageSoupleDto;
import Gildas.emailProjet.Dto.ContactMessageSoupleDtos;
import Gildas.emailProjet.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*") // Autoriser les requêtes depuis Angular
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/sendShakMax")
    public ResponseEntity<ContactMessageDTOs> sendMessageShakmax(@RequestBody ContactMessageDTO form) {
        // On délègue complètement la logique au service
        ContactMessageDTOs response = contactService.messegeSendUserShakMax(form);

        // On renvoie la réponse avec le code HTTP approprié
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @PostMapping("/sendSoupleMooney")
    public ResponseEntity<ContactMessageSoupleDtos> sendMessageSoupleMoney(@RequestBody ContactMessageSoupleDto form) {
        // On délègue complètement la logique au service
        ContactMessageSoupleDtos response = contactService.messegeSendUserSoupleMooney(form);

        // On renvoie la réponse avec le code HTTP approprié
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}