package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.constants.ChannelContent;
import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.model.Post;
import danekerscode.socialmediaapi.payload.request.ChannelRequest;
import danekerscode.socialmediaapi.payload.request.PostRequest;
import danekerscode.socialmediaapi.repository.ChannelRepository;
import danekerscode.socialmediaapi.repository.PostRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.ChannelService;
import danekerscode.socialmediaapi.service.PostService;
import danekerscode.socialmediaapi.utils.Converter;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl
        implements ChannelService, PostService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CustomValidator customValidator;


    @Override
    public void addPost(PostRequest postRequest) {
        customValidator.validatePost(postRequest);
        postRepository.save(Converter.toPost(postRequest, channelRepository.findById(postRequest.ownerChannelId()).get()));
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
    public void updatePost(PostRequest postRequest , Integer id) {
        customValidator.validatePost(postRequest);
        var post = postRepository.findById(id).orElseThrow();
        post.setBody(postRequest.body());
        post.setTitle(postRequest.title());
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


    public Channel save(ChannelRequest request) {
        customValidator.validateChannel(request);
        return channelRepository.save(Converter.toChannel(request, userRepository.findById(request.owner()).get()));
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
    public void update(ChannelRequest channelRequest, Integer id) {
        Channel channel = channelRepository.findById(id).orElseThrow(() -> new EntityPropertiesException("invalid id for channel"));
        customValidator.validateChannel(channelRequest);
        channel.setContent(ChannelContent.valueOf(channelRequest.content()));
        channel.setName(channelRequest.name());
        channel.setOwner(userRepository.findById(channelRequest.owner()).orElseThrow(UserNotFoundException::new));
        channel.setDescription(channelRequest.description());

        channel.setId(id);

        this.channelRepository.save(channel);
     }

    @Override
    public List<Channel> getAll() {
        return channelRepository.findAll();
    }


}
