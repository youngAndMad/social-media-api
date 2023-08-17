package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Chat;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.ChatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper
public interface ChatMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users" , expression = "java(users)")
    Chat toChat(ChatDTO chatDTO, Set<User> users);
}
