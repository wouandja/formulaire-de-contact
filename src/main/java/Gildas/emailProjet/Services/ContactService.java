package Gildas.emailProjet.Services;

import Gildas.emailProjet.Dto.ContactMessageDTO;
import Gildas.emailProjet.Dto.ContactMessageDTOs;
import Gildas.emailProjet.Dto.ContactMessageSoupleDto;
import Gildas.emailProjet.Dto.ContactMessageSoupleDtos;
import Gildas.emailProjet.Entity.ContactMessage;
import Gildas.emailProjet.Entity.ContactMessageSouple;
import Gildas.emailProjet.Repository.ContactMessageRepository;
import Gildas.emailProjet.Repository.ContactMessageReposotorySouple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
 
import java.time.LocalDateTime;

import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import java.nio.charset.StandardCharsets;

@Service
public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
private ResourceLoader resourceLoader;

    @Autowired
    private ContactMessageRepository contactMessageRepository;
    @Autowired
    private ContactMessageReposotorySouple contactMessageRepositorySouple;

    public ContactMessageDTOs messegeSendUserShakMax(ContactMessageDTO form) {
        ContactMessageDTOs response = new ContactMessageDTOs();

        try {
            logger.info("Nouvelle demande de contact reçue : {}", form);

            // Vérification de l'email
            if (form.getEmail() == null || !form.getEmail().contains("@") || !form.getEmail().contains(".")) {
                logger.warn("Email invalide : {}", form.getEmail());
                response.setCode(400);
                response.setMessage("Email invalide");
                response.setContactMessageDTO(form);
                return response;
            }

            // 1️⃣ Sauvegarde en base
            ContactMessage messageEntity = new ContactMessage();
            messageEntity.setName(form.getName());
            messageEntity.setEmail(form.getEmail());
            messageEntity.setNumber(form.getNumber());
            messageEntity.setMessage(form.getMessage());
            messageEntity.setDateEnvoi(LocalDateTime.now());
            contactMessageRepository.save(messageEntity);
            logger.info("Message sauvegardé en base pour : {}", form.getEmail());

 Resource resource = resourceLoader.getResource("classpath:templates/messageContactShakMax.html");
String template = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            String content = template
                    .replace("[[name]]", form.getName())
                    .replace("[[email]]", form.getEmail())
                    .replace("[[number]]", form.getNumber())
                    .replace("[[message]]", form.getMessage());

            // 3️⃣ Envoyer email HTML à l'admin
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo("shaksmax@yahoo.com"); // changer avec l'email de l'admin
            helper.setSubject(" Nouveau message de contact");
            helper.setText(content, true); // true → HTML
            helper.setReplyTo(form.getEmail());
            javaMailSender.send(mimeMessage);
            logger.info("Email HTML envoyé à l'admin avec replyTo {}", form.getEmail());

            // 4️⃣ Préparer la réponse pour l'API
            response.setCode(200);
            response.setMessage("Message envoyé avec succès");
            response.setContactMessageDTO(form);

        } catch (IOException | MessagingException e) {
            logger.error("Erreur lors de l'envoi du message : {}", e.getMessage(), e);
            response.setCode(500);
            response.setMessage("Erreur lors de l'envoi du message : " + e.getMessage());
            response.setContactMessageDTO(form);
        }

        return response;
    }

    public ContactMessageSoupleDtos messegeSendUserSoupleMooney(ContactMessageSoupleDto form) {
        ContactMessageSoupleDtos response = new ContactMessageSoupleDtos();

        try {
            logger.info("Nouvelle demande de contact reçue : {}", form);

            // Vérification de l'email
            if (form.getEmail() == null || !form.getEmail().contains("@") || !form.getEmail().contains(".")) {
                logger.warn("Email invalide : {}", form.getEmail());
                response.setCode(400);
                response.setMessage("Email invalide");
                response.setContactMessagesoupleDTO(form);
                return response;
            }

            // 1️⃣ Sauvegarde en base
            ContactMessageSouple messageEntity = new ContactMessageSouple();
            messageEntity.setName(form.getName());
            messageEntity.setEmail(form.getEmail());
            messageEntity.setPhone(form.getPhone());
            messageEntity.setMessage(form.getMessage());
            messageEntity.setCountry(form.getCountry());

            messageEntity.setDateEnvoi(LocalDateTime.now());
            contactMessageRepositorySouple.save(messageEntity);
            logger.info("Message sauvegardé en base pour : {}", form.getEmail());

          // PAR :
Resource resource = resourceLoader.getResource("classpath:templates/messageContactSoupleMooney.html");
String template = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            String content = template
                    .replace("[[name]]", form.getName())
                    .replace("[[email]]", form.getEmail())
                    .replace("[[number]]", form.getPhone())
                    .replace("[[message]]", form.getMessage())
                    .replace("[[pays]]", form.getCountry());


            // 3️⃣ Envoyer email HTML à l'admin
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo("awerufin2@gmail.com"); // changer avec l'email de l'admin
            helper.setSubject(" Nouveau message de contact");
            helper.setText(content, true); // true → HTML
            helper.setReplyTo(form.getEmail());
            javaMailSender.send(mimeMessage);
            logger.info("Email HTML envoyé à l'admin avec replyTo {}", form.getEmail());

            // 4️⃣ Préparer la réponse pour l'API
            response.setCode(200);
            response.setMessage("Message envoyé avec succès");
            response.setContactMessagesoupleDTO(form);

        } catch (IOException | MessagingException e) {
            logger.error("Erreur lors de l'envoi du message : {}", e.getMessage(), e);
            response.setCode(500);
            response.setMessage("Erreur lors de l'envoi du message : " + e.getMessage());
            response.setContactMessagesoupleDTO(form);
        }

        return response;
    }
}
