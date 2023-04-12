package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.User;

import java.util.Optional;

public interface UserService extends ParentService<User> {
    Optional<User> findByEmail(String email);

    void updatePassword(String code, String newPassword);

    Optional<User> findByCode(String code);
}
