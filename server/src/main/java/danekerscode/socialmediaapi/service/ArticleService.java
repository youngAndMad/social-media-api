package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Article;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    void deleteByID(Integer id);

    Article getById(Integer id);

    List<Article> getAll();
}
