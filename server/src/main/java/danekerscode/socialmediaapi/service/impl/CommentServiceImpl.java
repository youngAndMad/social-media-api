package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.mapper.CommentMapper;
import danekerscode.socialmediaapi.model.Comment;
import danekerscode.socialmediaapi.payload.request.CommentDTO;
import danekerscode.socialmediaapi.repository.CommentRepository;
import danekerscode.socialmediaapi.service.CommentService;
import danekerscode.socialmediaapi.service.PostService;
import danekerscode.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final PostService postService;
    private final UserService userService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getPostComments(Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public Comment save(CommentDTO commentDTO) {
        return commentRepository
                .save(
                        commentMapper.toComment(
                                commentDTO,
                                userService.getById(commentDTO.senderId()),
                                postService.getPostById(commentDTO.postId())
                        )
                );
    }

    @Override
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

}
