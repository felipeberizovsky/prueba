package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "tipo_usuario")
public abstract class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "nombre_usuario")
    private String username;
    @Column(name = "contrasena")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "fecha_nacimiento")
    private Date dateOfBirth;
    @Column(name = "puntaje")
    private int score;
    public User() {
    }

    public User(String name, String username, String password, String email, Date dateOfBirth) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addScore(){
        this.score ++;
    }
}
