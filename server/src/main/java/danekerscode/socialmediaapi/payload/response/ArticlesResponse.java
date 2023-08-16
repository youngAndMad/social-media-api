package danekerscode.socialmediaapi.payload.response;

import danekerscode.socialmediaapi.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

;

@Data
@AllArgsConstructor
public class ArticlesResponse {
    private List<Article> articles;
}
