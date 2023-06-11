package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.*;
import danekerscode.socialmediaapi.payload.response.TokenResponse;
import danekerscode.socialmediaapi.payload.response.UserResponse;

import java.util.Optional;

public interface UserService extends ParentService<User> {

    User save(UserRequest userRequest);

    void updatePassword(PasswordRequest passwordRequest);

    Optional<User> findByCode(String code);

    UserResponse getPageToVisit(Integer id);

    void updateStatus(StatusUpdateRequest request, Integer id);

    TokenResponse authenticate(AuthenticationRequest authenticationRequest);

    TokenResponse createTokenResponse(String email, Integer userId);

    void update(UserUpdateRequest userRequest, Integer id);
}
