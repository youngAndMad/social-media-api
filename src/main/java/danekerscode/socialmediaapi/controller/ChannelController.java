package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.ChannelRequest;
import danekerscode.socialmediaapi.service.interfaces.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id){
     return ResponseEntity.ok(channelService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        channelService.deleteByID(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(
                channelService.getAll()
        );
    }



}
