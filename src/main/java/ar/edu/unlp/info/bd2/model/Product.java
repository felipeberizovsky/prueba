package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "producto")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "precio")
    private float price;
    @Column(name = "fecha_ultimo_precio")
    private Date lastPriceUpdateDate;
    @Column(name = "peso")
    private float weight;
    @Column(name = "descripcion")
    private String description;
    @ManyToOne(optional=false)
    @JoinColumn(name = "Supplier_id")
    private Supplier supplier;

    @ManyToMany
    @JoinTable(
            name = "producto_tproducto",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_producto_id")
    )
    private List<ProductType> types;

    public Product(){

    }

    public Product(String name, float price, float weight,Date lastPriceUpdateDate, String description, Supplier supplier, List<ProductType> types){
        this.name = name;
        this.price = price ;
        this.weight = weight;
        this.lastPriceUpdateDate = lastPriceUpdateDate;
        this.description = description;
        this.supplier = supplier;
        this.types = types;

    }

    public Product(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types){
        this.name = name;
        this.price = price ;
        this.weight = weight;
        this.description = description;
        this.supplier = supplier;
        this.types = types;

    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<ProductType> getTypes() {
        return types;
    }

    public void setTypes(List<ProductType> types) {
        this.types = types;
    }

    public Date getLastPriceUpdateDate() {
        return lastPriceUpdateDate;
    }

    public void setLastPriceUpdateDate(Date lastPriceUpdateDate) {
        this.lastPriceUpdateDate = lastPriceUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
