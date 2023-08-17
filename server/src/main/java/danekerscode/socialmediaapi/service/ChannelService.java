package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.payload.request.ChannelDTO;

import java.util.List;

public interface ChannelService {
    List<Channel> getUserChannels(Integer id);

    Channel save(ChannelDTO request);

    void update(ChannelDTO channelDTO, Integer id);

    void deleteByID(Integer id);

    Channel getById(Integer id);

}
