package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.AuthenticationRequest;
import danekerscode.socialmediaapi.payload.request.Request;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.payload.request.UserUpdateRequest;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.interfaces.MailService;
import danekerscode.socialmediaapi.service.interfaces.UserService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static danekerscode.socialmediaapi.utils.Converter.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomValidator customValidator;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User save(Object request) {
        UserRequest userRequest = (UserRequest) request;
        customValidator.validateUserRequest(userRequest);
        mailService.sendGreeting(userRequest.email());
        var user = toUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        return userRepository.save(user);
    }

    @Override
    public void deleteByID(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void updatePassword(String code, String newPassword) {
        var user = findByCode(code).orElseThrow(() -> new AuthenticationException("invalid code"));
        user.setPassword(newPassword);
        user.setCode(null);
        this.userRepository.save(user);
    }

    @Override
    public void update(Request request, Integer id) {
        customValidator.validateUpdateUserRequest((UserUpdateRequest) request);
        var user = userRepository.findById(id).orElseThrow();
        toUpdatedUser((UserUpdateRequest) request, user );
        this.userRepository.save(user);
    }

    @Override
    public Optional<User> findByCode(String code) {
        return userRepository.findUserByCode(code);
    }

    @Override
    public UserResponse getPageToVisit(Integer id) {
        return toUserResponse(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
