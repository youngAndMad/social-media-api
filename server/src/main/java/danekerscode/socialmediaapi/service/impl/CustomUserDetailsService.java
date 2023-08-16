package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(email);

        if (user.isEmpty()){
            throw new AuthenticationException("invalid login or password");
        }

        return user.get();
    }
}
