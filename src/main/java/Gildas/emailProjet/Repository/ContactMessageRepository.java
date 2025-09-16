package Gildas.emailProjet.Repository;

import Gildas.emailProjet.Entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
