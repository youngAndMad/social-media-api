package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.Notification;

import java.util.List;

public interface NotificationService {
    Boolean setChecked(Integer id);
    Boolean send(Integer id, String body);
    Boolean delete(Integer id);
}
