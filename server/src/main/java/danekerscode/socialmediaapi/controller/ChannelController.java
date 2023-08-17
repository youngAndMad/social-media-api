package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.ChannelDTO;
import danekerscode.socialmediaapi.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("channel")
@RequiredArgsConstructor
public class ChannelController {
    
    private final ChannelService channelService;

    @PostMapping("new")
    public ResponseEntity<?> add(@RequestBody ChannelDTO request){
        channelService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id){
     return ResponseEntity.ok(channelService.getById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateChannel(@PathVariable Integer id,
                                           @RequestBody ChannelDTO channelDTO){
        this.channelService.update(channelDTO,id);
        return ResponseEntity.ok(ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        channelService.deleteByID(id);
        return ResponseEntity.ok(ACCEPTED);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(
                channelService.getAll()
        );
    }

}
