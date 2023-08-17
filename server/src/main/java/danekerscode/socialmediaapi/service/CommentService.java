package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Comment;
import danekerscode.socialmediaapi.payload.request.CommentDTO;

import java.util.List;

public interface CommentService {
    List<Comment> getPostComments(Integer postId);

    Comment save(CommentDTO commentDTO);

    void deleteById(Integer id);
}
