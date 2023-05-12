package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "cantidad")
    private int quantity;
    @Column(name = "descripcion")
    private String description;

    @ManyToOne
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(optional=false)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Product product;


    public Item() { }

    public Item(Product product, int quantity, String description, Order order) {
        this.description = description;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
