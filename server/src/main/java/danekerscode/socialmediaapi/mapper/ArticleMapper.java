package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Article;
import danekerscode.socialmediaapi.payload.request.ArticleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(imports = LocalDateTime.class)
public interface ArticleMapper {

    @Mapping(target = "publishedAt" , expression = "java(LocalDateTime.now())")
    Article toArticle(ArticleDTO articleDTO);
}
