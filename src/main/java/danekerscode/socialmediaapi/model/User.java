package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import danekerscode.socialmediaapi.model.utils.GENDER;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Builder
@Table(name = "users" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private GENDER gender;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id" , referencedColumnName = "id")
    private Image image;
    private String code;
    @OneToMany(mappedBy = "owner" , cascade = CascadeType.ALL)
    List<Channel> channels;
}
