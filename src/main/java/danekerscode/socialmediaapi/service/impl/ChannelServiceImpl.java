package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.payload.request.CreateChannelRequest;
import danekerscode.socialmediaapi.resository.ChannelRepository;
import danekerscode.socialmediaapi.resository.UserRepository;
import danekerscode.socialmediaapi.service.i.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    @Override
    public void addPost() {
        // todo
    }

    @Override
    public List<Channel> getUserChannels(Integer id) {
        return channelRepository.getChannelsByOwner_Id(id);
    }

    @Override
    public Channel save(Object t) {
        CreateChannelRequest request = (CreateChannelRequest) t;
        Channel channel = Channel.builder()
                .name(request.name())
                .content(request.content())
                .description(request.description())
                .owner(userRepository.findById(request.owner()).orElseThrow())
                .build();

        return channelRepository.save(channel);
    }

    @Override
    public void deleteByID(Integer id) {
        channelRepository.deleteById(id);
    }

    @Override
    public Optional<Channel> getById(Integer id) {
        return channelRepository.findById(id);
    }

    @Override
    public void update(Channel channel) {
        ChannelService.super.update(channel);
    }

    @Override
    public List<Channel> getAll() {
        return  channelRepository.findAll();
    }
}
