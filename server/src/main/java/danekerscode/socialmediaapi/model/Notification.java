package danekerscode.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;


import java.time.LocalDateTime;

@Entity @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private LocalDateTime sentAt;
    private Boolean checked;

    @JsonIgnore
    @ManyToOne
    private User owner;
}
