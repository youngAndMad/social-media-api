package danekerscode.socialmediaapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String author;
    private String title;
    private String description;
    private String url;
    private Integer views;
    private String imageUrl;
    private String content;
    private LocalDateTime publishedAt;

}
