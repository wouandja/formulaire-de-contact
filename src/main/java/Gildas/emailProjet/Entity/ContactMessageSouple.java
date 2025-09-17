package Gildas.emailProjet.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ContactMessageSouple {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String email;
        private String phone;
        private String country;
        @Column(columnDefinition = "TEXT")
        private String message;

        private LocalDateTime dateEnvoi = LocalDateTime.now();





}
