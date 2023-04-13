package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.Comment;

import java.util.List;

public interface CommentService extends ParentService<Comment>{
    List<Comment> getPostComments(Integer postId);
}
