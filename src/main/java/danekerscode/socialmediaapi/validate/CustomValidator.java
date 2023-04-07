package danekerscode.socialmediaapi.validate;

import danekerscode.socialmediaapi.exception.RegistratoinException;
import danekerscode.socialmediaapi.payload.request.RegistrationRequest;
import danekerscode.socialmediaapi.service.i.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CustomValidator {

    private final UserService userService;

    public void validateRegistrationRequest(RegistrationRequest request) {
        StringBuilder sb  = new StringBuilder();
        if (userService.findByEmail(request.email()).isPresent())
            sb.append("this email ").append(request.email()).append(" registered yet!");
        if (!this.validateEmail(request.email()))
            sb.append("please specify valid email");
        if(request.gender().compareToIgnoreCase("MALE") !=0 && request.gender().compareToIgnoreCase("FEMALE") !=0)
            sb.append("please specify valid gender!");
        if (request.age() < 16)
            sb.append("for registration age should be greater than 16!");
        if (request.lastName().length() < 2)
            sb.append("length of  lastname should be longest than 2!");
        if (request.firstName().length() < 2)
            sb.append("length of firstname should be longest than 2!");
        if (!sb.isEmpty())
            throw new RegistratoinException(sb.toString());
    }
    public boolean validateEmail(String email){
        System.out.println(email);
        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
}
