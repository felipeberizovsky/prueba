package ar.edu.unlp.info.bd2.repositories;



import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DeliveryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }
    public Object save(Object obj){
        this.getSession().save(obj);
        return obj;
    }
    public Object update(Object obj){
        this.getSession().update(obj);
        return obj;
    }

    public Optional<User> getUserById(Long id) {
        return (Optional<User>)getSession().createQuery("from User where id = :id").setParameter("id", id).uniqueResultOptional();
    }

    public Optional<User> getUserByEmail(String  email) {
        return (Optional<User>)getSession().createQuery("from User where email = :email").setParameter("email", email).uniqueResultOptional();
    }

    public Optional<Supplier> findSupplierByCuit(String cuit){
        return (Optional<Supplier>)getSession().createQuery("from Supplier where cuit = :cuit").setParameter("cuit", cuit).uniqueResultOptional();
        //to do hay otra forma de hacer esto, ponerle que sea opcional que te devuelva algo
    }

    public List<Supplier> getSupplierByName(String name) {

        return (List<Supplier>)getSession().createQuery("from Supplier where name like :name").setParameter("name", "%" + name + "%").list();
    }


    public Optional<Product> getProductById(Long id) {
        return (Optional<Product>) getSession().createQuery("from Product where id = :id").setParameter("id", id).uniqueResultOptional();
    }

    public List<Product> getProductByName(String name){
        return (List<Product>)getSession().createQuery("from Product where name like :name").setParameter("name", "%" + name + "%").list();
    }

    public List<Product>  getProductByType(String type){
        System.out.print("repository");
        return (List<Product>)getSession().createQuery("SELECT p FROM Product p JOIN p.types t WHERE t.name = :name").setParameter("name", type ).list();
    }


    public Optional<Order> getOrderById(Long id) {
        return (Optional<Order>)getSession().createQuery("from orden where id = :id").setParameter("id", id).uniqueResultOptional();
    }

    public Optional<Order> getOrderByNumber(int number) {
        return (Optional<Order>)getSession().createQuery("from orden where number = :number").setParameter("number", number).uniqueResultOptional();
    }

    public Optional<DeliveryMan> getFreeDeliveryMan() {
        return (Optional<DeliveryMan>)getSession().createQuery("from User where tipo_usuario = :userType and free = 1").setParameter("userType", "Delivery").setMaxResults(1).uniqueResultOptional();
    }

    // A partir de aca metodos de TP2

    public List<User> findTopNUserWithMoreScore(int n){
        return (List<User>)getSession().createQuery("From User u ORDER BY u.score DESC").setMaxResults(n).list();
    }


}
