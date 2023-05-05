package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import danekerscode.socialmediaapi.constants.GENDER;
import danekerscode.socialmediaapi.constants.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;

    private Integer age;
    private Boolean isPrivateAccount;

    @JsonIgnore
    private String code;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Channel> channels;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    )
    private List<User> friendList;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "black_list",
            joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    )
    private List<User> blackList;

    @ManyToMany
    private Set<Chat> chats;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> messages;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
