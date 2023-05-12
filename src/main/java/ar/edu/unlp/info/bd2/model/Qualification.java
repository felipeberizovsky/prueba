package ar.edu.unlp.info.bd2.model;


import javax.persistence.*;

@Entity
@Table(name = "calificacion")
public class Qualification {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "puntaje")
    private float score; //De 1 a 5 estrellas
    @Column(name = "comentario")
    private String commentary;
    @OneToOne(mappedBy = "qualification")
    private Order order;


    public Qualification(){ }

    public Qualification(String commentary) {
        this.commentary = commentary;
    }


    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
