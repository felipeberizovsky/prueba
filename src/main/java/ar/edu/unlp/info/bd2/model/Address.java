package ar.edu.unlp.info.bd2.model;


import javax.persistence.*;

@Entity
@Table(name = "direccion")
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "direccion")
    private String address;
    @Column(name = "departamento")
    private String apartment;
    @Column(name = "coordenada_x")
    private float coordX;
    @Column(name = "coordenada_y")
    private float coordY;
    @Column(name = "descripcion")
    private String description;

    public Address(String name, String address, float coordX, float coordY, String description,Client client) {
        this.name = name;
        this.address = address;
        this.coordX = coordX;
        this.coordY = coordY;
        this.description = description;
        this.client = client;
    }

    public Address(String name, String address, String apartment, float coordX, float coordY, String description,Client client) {
        this.name = name;
        this.address = address;
        this.apartment = apartment;
        this.coordX = coordX;
        this.coordY = coordY;
        this.description = description;
        this.client = client;
    }

    public Address(){

    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getCoordX() {
        return coordX;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
