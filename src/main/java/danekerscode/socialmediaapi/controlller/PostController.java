package danekerscode.socialmediaapi.controlller;

import danekerscode.socialmediaapi.payload.request.PostRequest;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.i.ChannelService;
import danekerscode.socialmediaapi.service.impl.ChannelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {
    private final ChannelServiceImpl channelService;

    @PostMapping("new")
    public ResponseEntity<?> addPost(@RequestBody PostRequest request) {
        channelService.addPost(request);
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .status(ACCEPTED)
                        .statusCode(ACCEPTED.value())
                        .message("post successfully saved")
                        .build(), CREATED
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Integer id){
        channelService.deletePostById(id);
        return ResponseEntity.ok("post deleted");
    }

    @GetMapping("all/{id}")
    public ResponseEntity<?> getChannelPosts(@PathVariable Integer id){
        return ResponseEntity.ok(channelService.getPostsByChannelId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPost(@PathVariable Integer id){
        return ResponseEntity.ok(channelService.getPostById(id));
    }
    
}
