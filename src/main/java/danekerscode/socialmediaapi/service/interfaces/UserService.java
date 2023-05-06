package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.AuthenticationRequest;
import danekerscode.socialmediaapi.payload.request.PasswordRequest;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import danekerscode.socialmediaapi.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

public interface UserService extends ParentService<User> {

    User save(UserRequest userRequest);

    void updatePassword(PasswordRequest passwordRequest);

    Optional<User> findByCode(String code);

    UserResponse getPageToVisit(Integer id);

}
