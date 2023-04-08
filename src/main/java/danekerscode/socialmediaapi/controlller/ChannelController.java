package danekerscode.socialmediaapi.controlller;

import danekerscode.socialmediaapi.payload.request.ChannelRequest;
import danekerscode.socialmediaapi.service.i.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("new")
    public ResponseEntity<?> add(@RequestBody ChannelRequest request){
        channelService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        channelService.deleteByID(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping("all/{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable Integer id){
        return new ResponseEntity<>(channelService.getUserChannels(id),HttpStatus.OK);
    }

}
