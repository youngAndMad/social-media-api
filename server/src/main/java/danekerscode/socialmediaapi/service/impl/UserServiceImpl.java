package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EmailRegisteredYetException;
import danekerscode.socialmediaapi.exception.EntityNotFoundException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.mapper.UserMapper;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.*;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.AddressService;
import danekerscode.socialmediaapi.service.KafkaService;
import danekerscode.socialmediaapi.service.UserService;
import danekerscode.socialmediaapi.utils.KafkaMailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KafkaService kafkaService;
    private final AddressService addressService;
    private final UserMapper userMapper;

    @Override
    public User save(UserDTO userDTO) {
        var optional = userRepository.findUserByEmail(userDTO.email());

        if (optional.isPresent()){
            throw new EmailRegisteredYetException();
        }

        var user = userMapper.toUser(userDTO);

        kafkaService.sendEmailRequest(new KafkaMailMessage(userDTO.email() , "greeting"));
        addressService.save(user.getAddress());
        return userRepository.save(user);
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
    public void update(UserDTO request, Integer id) {
        var user = userRepository.findById(id).orElseThrow();

        userMapper.update(request,user);
        this.userRepository.save(user);
    }



    @Override
    public void updateStatus(StatusUpdateDTO request, Integer id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setStatus(request.status());
        userRepository.save(user);
    }
}
