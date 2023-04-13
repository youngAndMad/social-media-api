package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.CommentRequest;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("new")
    public ResponseEntity<?> doComment(@RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .timeStamp(now())
                        .message("successfully commented")
                        .data(Map.of("commentId", commentService.save(commentRequest).getId()))
                        .build(), CREATED
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        var comment = commentService.getById(id);
        return comment.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(comment.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        commentService.deleteByID(id);
        return ResponseEntity.ok("comment by id deleted. Id:" + id);
    }
}

