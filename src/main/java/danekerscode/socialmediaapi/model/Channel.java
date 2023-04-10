package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import danekerscode.socialmediaapi.constants.ChannelContent;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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
    @Enumerated(EnumType.STRING)
    private ChannelContent content;
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User owner;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id" , referencedColumnName = "id")
    private Image image;

    @OneToMany(mappedBy = "channel" , cascade = CascadeType.ALL)
    private List<Post> posts;


    @PrePersist
    public void onInit(){
        this.createdAt = now();
    }
}
