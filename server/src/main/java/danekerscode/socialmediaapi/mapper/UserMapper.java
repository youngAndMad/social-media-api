package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.constants.Role;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.UserDTO;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import org.mapstruct.*;

@Mapper(
        imports = {
                Role.class
        },
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = AddressMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    @Mapping(target = "role" , expression = "java(Role.ROLE_USER)")
    User toUser(UserDTO userDTO);

    void update(UserDTO updateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);
}

