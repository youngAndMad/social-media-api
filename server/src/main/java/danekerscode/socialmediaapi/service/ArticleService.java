package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Article;
import danekerscode.socialmediaapi.payload.request.ArticleDTO;

import java.util.List;

public interface ArticleService {
    Article save(ArticleDTO articleDTO);

    void deleteByID(Integer id);

    Article getById(Integer id);

    List<Article> getAll();
}
