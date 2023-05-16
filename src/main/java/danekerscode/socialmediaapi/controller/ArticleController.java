package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.model.Article;
import danekerscode.socialmediaapi.payload.response.ArticlesResponse;
import danekerscode.socialmediaapi.service.interfaces.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("all")
    public ResponseEntity<ArticlesResponse> getAll() {
        return new ResponseEntity<>(new ArticlesResponse(articleService.getAll()), HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<?> save(@RequestBody Article article) {
        return ResponseEntity.ok(this.articleService.save(article));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.articleService.deleteByID(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("add/link/{id}")
    public String hello(@RequestParam("link") String link,
            @PathVariable Integer id) {
        var article = articleService.getById(id).orElseThrow();
        article.setImageUrl(link);
        articleService.save(article);
        return "ok";
    }

}
