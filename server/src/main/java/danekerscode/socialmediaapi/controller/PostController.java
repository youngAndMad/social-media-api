package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.PostDTO;
import danekerscode.socialmediaapi.service.ChannelService;
import danekerscode.socialmediaapi.service.PostService;
import danekerscode.socialmediaapi.service.impl.ChannelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {
    private final ChannelService channelService;
    private final PostService postService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addPost(
            @RequestBody PostDTO request
    ) {
        postService.addPost(request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePost(
            @PathVariable Integer id
    ) {
        channelService.deleteByID(id);
    }

    @GetMapping("all/{id}")
    public ResponseEntity<?> getChannelPosts(
            @PathVariable Integer id
    ) {
        return ResponseEntity.
                ok(postService.getPostsByChannelId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPost(
            @PathVariable Integer id
    ) {
        return ResponseEntity
                .ok(postService.getPostById(id));
    }

}
