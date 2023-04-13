package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.AuthenticationRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

public interface UserService extends ParentService<User> {

    void updatePassword(String code, String newPassword);

    Optional<User> findByCode(String code);
}
