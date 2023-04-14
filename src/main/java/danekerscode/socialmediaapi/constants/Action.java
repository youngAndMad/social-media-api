package danekerscode.socialmediaapi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum Action {
    FOLLOW("added you to friend list"),
    UNFOLLOW("deleted you from friend list"),
    BLOCK("added you to black list"),
    UNBLOCK("deleted you from black list");

    private final String message;

}
