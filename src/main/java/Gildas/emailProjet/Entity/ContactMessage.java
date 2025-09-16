package Gildas.emailProjet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String number;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime dateEnvoi = LocalDateTime.now();
}
