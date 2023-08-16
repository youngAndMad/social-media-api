package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.model.Article;
import danekerscode.socialmediaapi.payload.response.ArticlesResponse;
import danekerscode.socialmediaapi.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("all")
    public ResponseEntity<ArticlesResponse> getAll() {
        return ResponseEntity.ok(new ArticlesResponse(articleService.getAll()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("new")
    public ResponseEntity<?> save(@RequestBody Article article) {
        return ResponseEntity.ok(this.articleService.save(article));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.articleService.deleteByID(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


}
