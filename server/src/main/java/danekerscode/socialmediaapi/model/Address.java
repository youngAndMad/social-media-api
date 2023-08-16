package danekerscode.socialmediaapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String city;
    private String street;
    private Integer houseNumber;
}
