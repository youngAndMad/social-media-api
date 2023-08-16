package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.model.Comment;
import danekerscode.socialmediaapi.payload.request.CommentRequest;
import danekerscode.socialmediaapi.repository.CommentRepository;
import danekerscode.socialmediaapi.repository.PostRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.CommentService;
import danekerscode.socialmediaapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getPostComments(Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public Comment save(CommentRequest commentRequest) {
        return commentRepository
                .save(
                        Converter.toComment(
                                commentRequest,
                                userRepository.findById(commentRequest.senderId()).orElseThrow(UserNotFoundException::new)
                                , postRepository.findById(commentRequest.postId()).orElseThrow(() -> new EntityPropertiesException("invalid post id to comment"))
                        )
                );
    }

    @Override
    public void deleteByID(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<Comment> getById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
}
