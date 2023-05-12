package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@DiscriminatorValue("cliente")
public class Client extends User{

    @Column(name = "fecha_registro")
    private Date dateOfRegister;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public Client(String name, String username, String password, String email, Date dateOfBirth) {
        super(name,username,password,email,dateOfBirth);
        this.dateOfRegister = new Date();
        setAddresses(new ArrayList<Address>());
    }
    public Client(){

    }

    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Date dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddresses(Address address){
        addresses.add(address);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}
