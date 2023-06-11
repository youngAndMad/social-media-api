package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.jwt.JWTUtil;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.*;
import danekerscode.socialmediaapi.payload.response.TokenResponse;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.interfaces.KafkaService;
import danekerscode.socialmediaapi.service.interfaces.UserService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
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
    private final KafkaService kafkaService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    @Override
    public User save(UserRequest userRequest) {
        customValidator.validateUserRequest(userRequest);
        kafkaService.sendEmailRequest(userRequest.email() , "greeting");
        var user = toUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        return userRepository.save(user);
    }

    public void sendVerifyCode(String email){
        kafkaService.sendEmailRequest(email , "activationCode");
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
    public void updatePassword(PasswordRequest passwordRequest) {
        var user = findByCode(passwordRequest.code()).orElseThrow(() -> new AuthenticationException("invalid code"));
        user.setPassword(passwordEncoder.encode(passwordRequest.newPassword()));
        user.setCode(null);
        this.userRepository.save(user);
    }

    @Override
    public void update(UserUpdateRequest request, Integer id) {
        customValidator.validateUpdateUserRequest( request);
        var user = userRepository.findById(id).orElseThrow();
        toUpdatedUser(request, user);
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

    @Override
    public void updateStatus(StatusUpdateRequest request, Integer id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setStatus(request.status());
        userRepository.save(user);
    }

    @Override
    public TokenResponse authenticate(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(),
                authenticationRequest.password());
        var user =  (User) authenticationManager.authenticate(authenticationToken).getPrincipal();

        return createTokenResponse(authenticationRequest.email(), user.getId());
    }

    public TokenResponse createTokenResponse(String email , Integer userId){
        return new TokenResponse(jwtUtil.generateToken(email) , userId);
    }
}
