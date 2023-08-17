package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Chat;
import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.MessageDTO;
import danekerscode.socialmediaapi.payload.request.UpdateMessageDTO;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {
                LocalDateTime.class
        }
)
public interface MessageMapper {

    @Mapping(target = "sentAt" , expression = "java(LocalDateTime.now())")
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "sender" , expression = "java(sender)")
    @Mapping(target = "chat" , expression = "java(chat)")
    @Mapping(target = "senderFullName", expression = "java(userFullName(sender))")
    Message toMessage(MessageDTO dto , User sender, Chat chat);

    @Mapping(target = "text" , expression = "java(dto.updatedMessage())")
    @Mapping(target = "sender" , ignore = true)
    void update(@MappingTarget Message message , UpdateMessageDTO dto);


    default String userFullName(User user){
        return user.getFirstName()
                .concat(" ")
                .concat(user.getLastName());
    }
}
