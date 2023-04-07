package danekerscode.socialmediaapi.model;

import javax.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String originalName;
    private Long size;
    private String contentType;
    private byte[] bytes;
}