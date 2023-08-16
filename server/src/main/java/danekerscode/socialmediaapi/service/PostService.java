package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Post;
import danekerscode.socialmediaapi.payload.request.PostRequest;

import java.util.List;

public interface PostService {
    void addPost(PostRequest postRequest);

    void deletePostById(Integer id);

    List<Post> getPostsByChannelId(Integer id);

    void updatePost(PostRequest postRequest, Integer id);

    Post getPostById(Integer id);
}
