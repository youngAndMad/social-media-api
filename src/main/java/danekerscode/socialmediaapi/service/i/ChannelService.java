package danekerscode.socialmediaapi.service.i;

import danekerscode.socialmediaapi.model.Channel;

import java.util.List;

public interface ChannelService extends ParentService<Channel>{
    void addPost(); // TODO
    List<Channel> getUserChannels(Integer id);
}
