package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.*;
import danekerscode.socialmediaapi.payload.response.TokenResponse;
import danekerscode.socialmediaapi.payload.response.UserResponse;

import java.util.Optional;

public interface UserService {

    User save(UserDTO userDTO);

    void updateStatus(StatusUpdateDTO request, Integer id);

    void update(UserDTO userDTO, Integer id);

    User getById(Integer id);

    void deleteById(Integer id);
}
