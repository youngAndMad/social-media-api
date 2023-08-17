package danekerscode.socialmediaapi.payload.response;

import danekerscode.socialmediaapi.constants.Gender;
import danekerscode.socialmediaapi.model.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Integer age;
    private String imageURL;
    private String email;
    private UserStatus userStatus;
    private List<Channel> channels;

}
