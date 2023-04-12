package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.Channel;

import java.util.List;

public interface ChannelService extends ParentService<Channel> {
    List<Channel> getUserChannels(Integer id);
}
