package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.repository.NotificationRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.NotificationService;
import danekerscode.socialmediaapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public Boolean setChecked(Integer id) {
        var note = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityPropertiesException("invalid notification id"));
        note.setChecked(Boolean.TRUE);
        notificationRepository.save(note);
        return true;
    }

    @Override
    public Boolean send(Integer id , String body) {
        notificationRepository.save(
                Converter.toNotification(body , userRepository.findById(id).orElseThrow(UserNotFoundException::new))
        );
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        notificationRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteUserNotifications(Integer id) {
        notificationRepository.deleteAllByOwnerId(id);
    }
}
