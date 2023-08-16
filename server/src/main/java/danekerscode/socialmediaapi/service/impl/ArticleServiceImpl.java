package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.model.Article;
import danekerscode.socialmediaapi.repository.ArticleRepository;
import danekerscode.socialmediaapi.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public void deleteByID(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Optional<Article> getById(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


    @Override
    public Article save(Article article) {
        try {
            return this.articleRepository.save(article);
        }catch (DataIntegrityViolationException e){
            System.out.println(article.toString());
            System.out.println(e.getMessage().toUpperCase());
        }
        return null;
    }

    @Override
    public void savaAll(List<Article> articles) {
        articles.forEach(this::save);
    }

}
