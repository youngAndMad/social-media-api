package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Post;
import danekerscode.socialmediaapi.payload.request.PostDTO;

import java.util.List;

public interface PostService {
    void addPost(PostDTO postDTO);

    void deletePostById(Integer id);

    List<Post> getPostsByChannelId(Integer id);

    void updatePost(PostDTO postDTO, Integer id);

    Post getPostById(Integer id);
}
