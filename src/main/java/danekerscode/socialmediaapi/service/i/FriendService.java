package danekerscode.socialmediaapi.service.i;

import danekerscode.socialmediaapi.payload.request.FriendAction;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import danekerscode.socialmediaapi.payload.response.UserStatus;

import java.util.List;

public interface FriendService {
    void doFinalAction(FriendAction friendAction);

    void follow(FriendAction action);

    void unfollow(FriendAction action);

    void block(FriendAction action);

    void unblock(FriendAction action);

    List<UserResponse> getFriendListByUserId(Integer id);

    List<UserResponse> getBlackListByUserId(Integer id);

    UserStatus getUserStatus(Integer firstUserId , Integer secondUserId);
}
