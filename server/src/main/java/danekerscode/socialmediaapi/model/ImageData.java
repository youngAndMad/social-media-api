package danekerscode.socialmediaapi.model;

import lombok.*;
import javax.persistence.*;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

}
