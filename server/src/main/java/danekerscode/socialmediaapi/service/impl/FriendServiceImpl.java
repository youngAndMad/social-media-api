package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.payload.request.FriendAction;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import danekerscode.socialmediaapi.payload.response.UserStatus;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.FriendService;
import danekerscode.socialmediaapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static danekerscode.socialmediaapi.payload.response.UserStatus.*;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Override
    public void doFinalAction(FriendAction friendAction) {
        switch (friendAction.getAction()) {
            case FOLLOW -> follow(friendAction);
            case UNFOLLOW -> unfollow(friendAction);
            case BLOCK -> block(friendAction);
            case UNBLOCK -> unblock(friendAction);
            default -> throw new EntityPropertiesException("invalid action");
        }
        if (friendAction.getAccepted()) {
            sendNotification(friendAction);
        }
        userRepository.save(userRepository.findById(friendAction.getFirstUserId()).orElseThrow());
    }

    @Override
    public void follow(FriendAction action) {
        var user = userRepository.findById(action.getFirstUserId()).orElseThrow(UserNotFoundException::new);
        if (!checkUser(action.getFirstUserId(), action.getSecondUserId(), this::getFriendListByUserId)) {
            action.setAccepted(Boolean.FALSE);
        } else {
            user.getFriendList()
                    .add(userRepository.findById(action.getSecondUserId())
                            .orElseThrow(UserNotFoundException::new));
            action.setAccepted(Boolean.TRUE);
        }
    }


    @Override
    public void unfollow(FriendAction action) {
        var user = userRepository.findById(action.getFirstUserId()).orElseThrow(UserNotFoundException::new);
        if (checkUser(action.getFirstUserId(), action.getSecondUserId(), this::getFriendListByUserId)) {
            action.setAccepted(Boolean.FALSE);
        } else {
            user.getFriendList()
                    .remove(userRepository.findById(action.getSecondUserId())
                            .orElseThrow(UserNotFoundException::new));
            action.setAccepted(Boolean.TRUE);
        }
    }

    @Override
    public void block(FriendAction action) {
        var user = userRepository.findById(action.getFirstUserId()).orElseThrow(UserNotFoundException::new);
        if (checkUser(action.getFirstUserId(), action.getSecondUserId(), this::getBlackListByUserId)) {
            action.setAccepted(Boolean.FALSE);
        } else {
            user.getBlackList()
                    .add(userRepository.findById(action.getSecondUserId())
                            .orElseThrow(UserNotFoundException::new));
            action.setAccepted(Boolean.TRUE);
        }
    }

    @Override
    public void unblock(FriendAction action) {
        var user = userRepository.findById(action.getFirstUserId()).orElseThrow(UserNotFoundException::new);
        if (!checkUser(action.getFirstUserId(), action.getSecondUserId(), this::getBlackListByUserId)) {
            action.setAccepted(Boolean.FALSE);
        } else {
            user.getBlackList().
                    remove(userRepository
                            .findById(action.getSecondUserId()).orElseThrow(UserNotFoundException::new));
            action.setAccepted(Boolean.TRUE);
        }
    }

    @Override
    public List<UserResponse> getFriendListByUserId(Integer id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.getFriendList()
                .stream()
                .map(Converter::toUserResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getBlackListByUserId(Integer id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.getBlackList()
                .stream()
                .map(Converter::toUserResponse)
                .toList();
    }

    @Override
    public UserStatus getUserStatus(Integer firstUserId, Integer secondUserId) {
        return !checkUser(firstUserId, secondUserId, this::getFriendListByUserId) ? FRIEND
                : !checkUser(firstUserId, secondUserId, this::getBlackListByUserId) ? BLOCKED
                : NEUTRAL;
    }

    @Override
    public List<UserResponse> getUserList(String status, Integer id) {
        return status.equals("friend") ? getFriendListByUserId(id)
                : status.equals("block") ? getBlackListByUserId(id)
                : Collections.emptyList();
    }

    private boolean checkUser(Integer firstUserId, Integer secondUserId,
                              Function<Integer, List<UserResponse>> function) {
        return function
                .apply(firstUserId)
                .stream()
                .map(UserResponse::getId)
                .noneMatch(p -> p.equals(secondUserId));
    }

    private void sendNotification(FriendAction friendAction){
        var user = userRepository.findById(friendAction.getFirstUserId()).get();
        notificationService.send(friendAction.getSecondUserId(), "%s %s %s".formatted(user.getFirstName(), user.getLastName(), friendAction.getAction().getMessage()));
    }

}
