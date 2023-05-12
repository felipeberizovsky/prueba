package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="orden")
@Table(name = "orden")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "numero")
    private int number;
    @Column(name = "fecha_orden")
    private Date dateOfOrder;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "precio_total")
    private float totalPrice;
    @Column(name = "entregado")
    private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private DeliveryMan deliveryMan;

    @ManyToOne(optional=false)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "calificacion_id", referencedColumnName = "id")
    private Qualification qualification;

    @ManyToOne(optional = false)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")

    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<Item>();


    public Order() { }

    public Order(int number, Date dateOfOrder, String comments, Client client, Address address) {
        this.number = number;
        this.dateOfOrder = dateOfOrder;
        this.comments = comments;
        this.client = client;
        this.address = address;
        this.totalPrice = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void updateTotalPrice(float value){
        this.totalPrice = this.totalPrice + value;
    }
}
