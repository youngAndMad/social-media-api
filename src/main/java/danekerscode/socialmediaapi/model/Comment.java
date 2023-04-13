package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime commentedAt;
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Post post;

}
