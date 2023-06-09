package danekerscode.socialmediaapi.payload.request;

import danekerscode.socialmediaapi.constants.Action;
import lombok.Data;

@Data
public class FriendAction {
    private Integer firstUserId;
    private Integer secondUserId;
    private Boolean accepted;
    private Action action;
}
