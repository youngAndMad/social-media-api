package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.Article;

import java.util.List;

public interface ArticleService extends ParentService<Article>{
    Article save(Article article);
    void savaAll(List<Article> articles);
}
