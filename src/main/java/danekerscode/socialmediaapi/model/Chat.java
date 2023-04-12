package danekerscode.socialmediaapi.model;

import danekerscode.socialmediaapi.constants.ChatType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity @Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private Integer id;
    private LocalDateTime createdAt;
    private String lastMessage;

    @Enumerated(EnumType.STRING)
    private ChatType type;

    @ManyToMany
    @JoinTable(
            name = "users_chats",
            joinColumns = @JoinColumn(name = "chats_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "chat")
    private List<Message> messages;


}
