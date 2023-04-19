package danekerscode.socialmediaapi.payload.response;

import com.zaxxer.hikari.metrics.PoolStats;
import danekerscode.socialmediaapi.constants.GENDER;
import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.model.Image;
import danekerscode.socialmediaapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private GENDER gender;
    private Integer age;
    private Image avatar;
    private List<Channel> channels;
}
