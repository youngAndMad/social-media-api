package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.ArticleDTO;
import danekerscode.socialmediaapi.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static danekerscode.socialmediaapi.utils.ReturnError.validateRequest;

@RestController
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .ok(articleService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(
            @RequestBody @Valid ArticleDTO articleDTO,
            BindingResult br
    ) {
        validateRequest(br);
        return ResponseEntity
                .ok(articleService.save(articleDTO));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) {
        this.articleService.deleteByID(id);
    }


}
