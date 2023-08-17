package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.CommentDTO;
import danekerscode.socialmediaapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static danekerscode.socialmediaapi.utils.ReturnError.validateRequest;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<?> doComment(
            @RequestBody @Valid CommentDTO commentDTO,
            BindingResult br
    ) {
        validateRequest(br);
        return ResponseEntity.
                status(201)
                .body(commentService.save(commentDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(
            @PathVariable Integer id
    ) {
        return ResponseEntity
                .ok(commentService.getPostComments(id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) {
        commentService.deleteById(id);
    }
}
