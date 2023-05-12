package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proveedor" )
public class Supplier {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "direccion")
    private String address;
    @Column(name = "coordenada_x")
    private float coordX;
    @Column(name = "coordenada_y")
    private float coordY;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Product> products;

    public Supplier(){

    }
    public Supplier(String name, String cuit, String address, float coordX, float coordY) {
        this.name = name;
        this.cuit = cuit;
        this.address = address;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuil() {
        return cuit;
    }

    public void setCuil(String cuil) {
        this.cuit = cuil;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
