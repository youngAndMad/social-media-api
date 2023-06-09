package danekerscode.socialmediaapi.repository;

import danekerscode.socialmediaapi.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    void deleteAllByOwnerId(Integer ownerId);
}