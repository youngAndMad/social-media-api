package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import danekerscode.socialmediaapi.constants.Gender;
import danekerscode.socialmediaapi.constants.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
