package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.payload.request.ChannelRequest;

import java.util.List;

public interface ChannelService extends ParentService<Channel> {
    List<Channel> getUserChannels(Integer id);
    Channel save(ChannelRequest request);
    void update(ChannelRequest channelRequest, Integer id);
}
