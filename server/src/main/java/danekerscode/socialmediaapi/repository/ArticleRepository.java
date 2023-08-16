package danekerscode.socialmediaapi.repository;

import danekerscode.socialmediaapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}