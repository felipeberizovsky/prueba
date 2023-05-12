package ar.edu.unlp.info.bd2.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Delivery")
public class DeliveryMan extends User{

    @Column(name = "numero_ordenes")
    private int numberOfSuccessOrders;
    @Column(name = "fecha_admision")
    private Date dateOfAdmission;
    @Column(name = "libre")
    private boolean free;

    @OneToMany(mappedBy = "deliveryMan",cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Order> orders;

    public int getNumberOfSuccessOrders() {
        return numberOfSuccessOrders;
    }

    public void setNumberOfSuccessOrders(int numberOfSuccessOrders) {
        this.numberOfSuccessOrders = numberOfSuccessOrders;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public DeliveryMan(String name, String username, String password, String email, Date dateOfBirth) {
        super(name,username,password,email,dateOfBirth);
        this.numberOfSuccessOrders = 0;
        this.dateOfAdmission = new Date();
        this.free = true;
        setOrders(new ArrayList<Order>());
    }

    public DeliveryMan(){

    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void addSuccessOrder(){
        numberOfSuccessOrders ++;
    }

}
