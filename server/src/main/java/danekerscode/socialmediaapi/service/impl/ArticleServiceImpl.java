package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityNotFoundException;
import danekerscode.socialmediaapi.model.Article;
import danekerscode.socialmediaapi.mapper.ArticleMapper;
import danekerscode.socialmediaapi.payload.request.ArticleDTO;
import danekerscode.socialmediaapi.repository.ArticleRepository;
import danekerscode.socialmediaapi.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public void deleteByID(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Article getById(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Article.class));
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


    @Override
    public Article save(ArticleDTO  articleDTO) {
        return articleRepository
                .save(articleMapper.toArticle(articleDTO));
    }

}
