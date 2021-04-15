package users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "age")
    private String age;

    public User(String name, String surname, String nationality, String age) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.age = age;
    }
}
