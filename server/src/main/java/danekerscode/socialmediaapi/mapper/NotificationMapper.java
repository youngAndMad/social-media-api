package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Notification;
import danekerscode.socialmediaapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(
        imports = {
                LocalDateTime.class
        }
)
public interface NotificationMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "checked" , expression = "java(false)")
    @Mapping(target = "sentAt" ,expression = "java(LocalDateTime.now())")
    @Mapping(target = "message" ,expression = "java(body)")
    Notification toNotification(String body, User owner);
}
