package danekerscode.socialmediaapi.service;

public interface NotificationService {
    Boolean setChecked(Integer id);

    Boolean send(Integer id, String body);

    Boolean delete(Integer id);

    void deleteUserNotifications(Integer id);
}
