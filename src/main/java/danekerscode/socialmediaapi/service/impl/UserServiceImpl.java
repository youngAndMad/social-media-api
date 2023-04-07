package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.model.utils.GENDER;
import danekerscode.socialmediaapi.payload.request.RegistrationRequest;
import danekerscode.socialmediaapi.resository.UserRepository;
import danekerscode.socialmediaapi.service.i.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(Object request) {
        RegistrationRequest registrationRequest = (RegistrationRequest) request;
        User user = User.builder()
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .email(registrationRequest.email())
                .gender(GENDER.valueOf(registrationRequest.gender().toUpperCase(Locale.ROOT)))
                .age(registrationRequest.age())
                .password(registrationRequest.password())
                .build();
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
    public void update(User user) {
        userRepository.save(user);
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
        this.update(user);
    }

    @Override
    public Optional<User> findByCode(String code) {
        return userRepository.findUserByCode(code);
    }
}
