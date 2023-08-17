package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import danekerscode.socialmediaapi.constants.Gender;
import danekerscode.socialmediaapi.constants.Role;
import lombok.*;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    private String email;
    @JsonIgnore
    private String password;

    private Integer age;
    private Boolean isPrivateAccount;

    @JsonIgnore
    private String code;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Role role;

    private String imageUrl;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Channel> channels;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "id"))
    private List<User> friendList;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "black_list", joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "id"))
    private List<User> blackList;

    @ManyToMany
    private Set<Chat> chats;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> messages;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Notification> notifications;


}
