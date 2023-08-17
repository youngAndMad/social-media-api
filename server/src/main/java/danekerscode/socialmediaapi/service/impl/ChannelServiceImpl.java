package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityNotFoundException;
import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.mapper.ChannelMapper;
import danekerscode.socialmediaapi.mapper.PostMapper;
import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.model.Post;
import danekerscode.socialmediaapi.payload.request.ChannelDTO;
import danekerscode.socialmediaapi.payload.request.PostDTO;
import danekerscode.socialmediaapi.repository.ChannelRepository;
import danekerscode.socialmediaapi.repository.PostRepository;
import danekerscode.socialmediaapi.service.ChannelService;
import danekerscode.socialmediaapi.service.PostService;
import danekerscode.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl
        implements ChannelService, PostService {

    private final ChannelRepository channelRepository;
    private final UserService  userService;
    private final PostRepository postRepository;
    private final ChannelMapper channelMapper;
    private final PostMapper postMapper;


    @Override
    public void addPost(PostDTO postDTO) {
        postRepository.save(
                postMapper.toPost(postDTO, getById(postDTO.ownerChannelId()))
        );
    }

    @Override
    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getPostsByChannelId(Integer id) {
       return postRepository.findAllByChannelId(id);
    }

    @Override
    public void updatePost(PostDTO postDTO, Integer id) {
        var post =getPostById(id);

        postMapper.update(postDTO,post);
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityPropertiesException("invalid post id"));
    }

    @Override
    public List<Channel> getUserChannels(Integer id) {
        return channelRepository.getChannelsByOwner_Id(id);
    }


    public Channel save(ChannelDTO request) {
        var owner = userService.getById(request.owner());
        return channelRepository.save(
                channelMapper.toChannel(request,owner)
        );
    }

    @Override
    public void deleteByID(Integer id) {
        channelRepository.deleteById(id);
    }

    @Override
    public Channel getById(Integer id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Channel.class));
    }

    @Override
    public void update(ChannelDTO channelDTO, Integer id) {
        Channel channel = getById(id);
        channelMapper.update(channelDTO,channel);
        this.channelRepository.save(channel);
     }


}
