package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Comment;
import danekerscode.socialmediaapi.model.Post;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "sender" , expression = "java(user)")
    @Mapping(target = "post" , expression = "java(post)")
    Comment toComment(CommentDTO commentDTO, User user, Post post);
}
