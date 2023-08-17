package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.exception.EmailRegisteredYetException;
import danekerscode.socialmediaapi.exception.EntityNotFoundException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.jwt.JWTUtil;
import danekerscode.socialmediaapi.mapper.UserMapper;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.*;
import danekerscode.socialmediaapi.payload.response.TokenResponse;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.AddressService;
import danekerscode.socialmediaapi.service.KafkaService;
import danekerscode.socialmediaapi.service.UserService;
import danekerscode.socialmediaapi.utils.Converter;
import danekerscode.socialmediaapi.utils.KafkaMailMessage;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KafkaService kafkaService;
    private final AddressService addressService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Override
    public User save(UserDTO userDTO) {
        var optional = userRepository.findUserByEmail(userDTO.email());

        if (optional.isPresent()){
            throw new EmailRegisteredYetException();
        }

        var user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        kafkaService.sendEmailRequest(new KafkaMailMessage(userDTO.email() , "greeting"));
        addressService.save(user.getAddress());
        return userRepository.save(user);
    }

    public void sendVerifyCode(String email) {
        kafkaService.sendEmailRequest(new KafkaMailMessage(
                email, UUID.randomUUID().toString()
        ));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class));
    }

    @Override
    public void updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
        var user = findByCode(passwordUpdateDTO.code()).orElseThrow(() -> new AuthenticationException("invalid code"));
        user.setPassword(passwordEncoder.encode(passwordUpdateDTO.newPassword()));
        user.setCode(null);
        this.userRepository.save(user);
    }

    @Override
    public void update(UserDTO request, Integer id) {
//        customValidator.validateUpdateUserRequest(request);
        var user = userRepository.findById(id).orElseThrow();
//        Converter.toUpdatedUser(request, user);
        this.userRepository.save(user);
    }


    public Optional<User> findByCode(String code) {
        return userRepository.findUserByCode(code);
    }

    @Override
    public UserResponse getPageToVisit(Integer id) {
        return Converter.toUserResponse(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void updateStatus(StatusUpdateDTO request, Integer id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setStatus(request.status());
        userRepository.save(user);
    }

    @Override
    public TokenResponse authenticate(AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationDTO.email(),
                authenticationDTO.password());
        var user = (User) authenticationManager.authenticate(authenticationToken).getPrincipal();

        return createTokenResponse(authenticationDTO.email(), user.getId());
    }

    public TokenResponse createTokenResponse(String email, Integer userId) {
        return new TokenResponse(jwtUtil.generateToken(email), userId);
    }
}
