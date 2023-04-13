package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Channel channel;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "post")
    private List<Comment> comments;
}
