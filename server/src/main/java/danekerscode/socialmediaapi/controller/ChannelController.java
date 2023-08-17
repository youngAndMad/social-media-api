package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.ChannelDTO;
import danekerscode.socialmediaapi.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static danekerscode.socialmediaapi.utils.ReturnError.validateRequest;
import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("channel")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<?> add(
            @RequestBody @Valid ChannelDTO dto,
            BindingResult br
    ) {
        validateRequest(br);
        return ResponseEntity.status(201)
                .body(channelService.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(channelService.getById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateChannel(
            @PathVariable Integer id,
            @RequestBody @Valid ChannelDTO dto,
            BindingResult br
    ) {
        validateRequest(br);
        this.channelService.update(dto, id);
        return ResponseEntity.ok(ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        channelService.deleteByID(id);
        return ResponseEntity.ok(ACCEPTED);
    }


}
