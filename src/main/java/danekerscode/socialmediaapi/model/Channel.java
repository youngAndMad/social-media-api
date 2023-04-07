package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor
@AllArgsConstructor
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String content;

    @ManyToOne
    @JsonIgnore
    private User owner;
    private LocalDateTime createdAt;



    @PrePersist
    public void onInit(){
        this.createdAt = now();
    }
}
