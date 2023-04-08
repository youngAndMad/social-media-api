package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.Request;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.payload.request.UserUpdateRequest;
import danekerscode.socialmediaapi.resository.UserRepository;
import danekerscode.socialmediaapi.service.i.MailService;
import danekerscode.socialmediaapi.service.i.UserService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static danekerscode.socialmediaapi.utils.Converter.toUpdatedUser;
import static danekerscode.socialmediaapi.utils.Converter.toUser;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomValidator customValidator;
    private final MailService mailService;


    @Override
    public User save(Object request) {
        UserRequest userRequest = (UserRequest) request;
        customValidator.validateUserRequest(userRequest);
        mailService.sendGreeting(userRequest.email());
        return userRepository.save(toUser(userRequest));
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
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
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
}
