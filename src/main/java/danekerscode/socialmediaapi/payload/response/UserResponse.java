package danekerscode.socialmediaapi.payload.response;

import danekerscode.socialmediaapi.constants.GENDER;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private GENDER gender;
    private Integer age;
}
