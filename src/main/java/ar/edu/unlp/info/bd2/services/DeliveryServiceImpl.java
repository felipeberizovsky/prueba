package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private final DeliveryRepository repository;
    public DeliveryServiceImpl(DeliveryRepository r) {
        this.repository = r;
    }

    @Override
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        Client client = new Client(name,username,password,email,dateOfBirth);
        return (Client) this.repository.save(client);
    }

    @Override
    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        DeliveryMan deliveryMan = new DeliveryMan(name,username,password,email,dateOfBirth);
        return (DeliveryMan) this.repository.save(deliveryMan);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return this.repository.getUserById(id);

    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.repository.getUserByEmail(email);
    }

    @Override
    public Optional<DeliveryMan> getAFreeDeliveryMan() {
        return this.repository.getFreeDeliveryMan();
    }

    @Override
    public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan1) throws DeliveryException {
        repository.save(deliveryMan1);
        return deliveryMan1;
    }

    @Override
    public Address createAddress(String name, String address, String apartment, float coordX, float coordY, String description, Client client) throws DeliveryException {
        Address address1 = new Address(name,address,apartment,coordX,coordY,description,client);
        client.addAddresses(address1);
        return (Address) this.repository.save(address1);
    }

    @Override
    public Address createAddress(String name, String address, float coordX, float coordY, String description, Client client) throws DeliveryException {
        Address address1 = new Address(name,address,coordX,coordY,description,client);
        client.addAddresses(address1);
        return (Address) this.repository.save(address1);
    }

    @Override
    public Order createOrder(int number, Date dateOfOrder, String comments, Client client, Address address) throws DeliveryException {
        Optional<Order> order1 = this.repository.getOrderByNumber(number);
        if(!order1.isPresent()) {
            return (Order) this.repository.save(new Order(number, dateOfOrder, comments, client, address));
        }
        throw new DeliveryException("Ya existe una orden con ese número");
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return this.repository.getOrderById(id);
    }

    @Override
    public Supplier createSupplier(String name, String cuil, String address, float coordX, float coordY) throws DeliveryException {
        if (this.repository.findSupplierByCuit(cuil).isPresent()) {
            throw new DeliveryException("Constraint violation");
        }
        Supplier supplier1 = new Supplier(name,  cuil, address, coordX, coordY);
        return (Supplier) this.repository.save(supplier1);
    }

    @Override
    public List<Supplier> getSupplierByName(String name) {
        return this.repository.getSupplierByName(name);
    }

    @Override
    public ProductType createProductType(String name, String description) throws DeliveryException {
//        if (this.repository.findSupplierByCuit(cuil) != null) { //to do se puede hacer con la funcion isPresent y optionalValue, no se que conviene
//            throw new DeliveryException("Constraint violation");
//        }
        ProductType productType1 = new ProductType(name, description);
        return (ProductType) this.repository.save(productType1);
    }

    @Override
    public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        Product product1 = new Product(name,  price,  weight,  description,  supplier, types);
        return (Product) this.repository.save(product1);
    }

    @Override
    public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        Product product2 = new Product(name,  price,  weight, lastPriceUpdateDate,  description,  supplier, types);
        return (Product) this.repository.save(product2);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return this.repository.getProductById(id);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return this.repository.getProductByName(name);
    }

    @Override
    public List<Product> getProductsByType(String type) throws DeliveryException {
        if (this.repository.getProductByType(type).isEmpty()) { //to do se puede hacer con la funcion isPresent y optionalValue, no se que conviene
            throw new DeliveryException("No existe el tipo de producto");
        }
        return this.repository.getProductByType(type);
    }

    @Override
    public Product updateProductPrice(Long id, float price) throws DeliveryException {
       Optional<Product> product1 = getProductById(id);
       if (!product1.isPresent()){
           throw new DeliveryException("No existe el producto a actualizar");
       }
       Product product2 = product1.get();
       product2.setPrice(price);
       product2.setLastPriceUpdateDate(new Date());
       return (Product) this.repository.update(product2);
    }

    @Override
    public boolean addDeliveryManToOrder(Long order, DeliveryMan deliveryMan) throws DeliveryException {
        Optional<Order> order1 = getOrderById(order);
        if(order1.isPresent()) {
            Order orden = order1.get();
            if(deliveryMan.isFree() && !orden.isDelivered() && !orden.getItems().isEmpty()){
                orden.setDeliveryMan(deliveryMan);
                deliveryMan.addOrder(orden);
                deliveryMan.setFree(false);
                this.repository.save(orden);
                return true;
            }
            return false;
        }
        throw new DeliveryException("No existe la orden");
    }

    @Override
    public boolean setOrderAsDelivered(Long order) throws DeliveryException {
        Optional<Order> order1 = getOrderById(order);
        if(order1.isPresent()) {
            Order orden = order1.get();
            if(orden.getDeliveryMan() != null) {
                orden.setDelivered(true);
                orden.getDeliveryMan().setFree(true);
                orden.getDeliveryMan().addScore();
                orden.getDeliveryMan().addSuccessOrder();
                orden.getClient().addScore();
                this.repository.save(orden);
                return true;
            }
            return false;
        }
        throw new DeliveryException("No existe la orden");
    }

    /**
     * Agrega una reseña a una orden
     * @param commentary comentario de la reseña
     */
    @Override
    public Qualification addQualificatioToOrder(Long order, String commentary) throws DeliveryException {
        Optional<Order> order1 = getOrderById(order);
        if(order1.isPresent()) {
            Order orden = order1.get();
            Qualification qualification = new Qualification(commentary);
            orden.setQualification(qualification);
            return (Qualification) this.repository.save(qualification);
        }
        throw new DeliveryException("No existe la orden");
    }

    @Override
    public Item addItemToOrder(Long order, Product product, int quantity, String description) throws DeliveryException {
        Optional<Order> order1 = getOrderById(order);
        if(order1.isPresent()) {
            Order orden = order1.get();
            Item item = new Item(product, quantity, description, orden);
            orden.addItem(item);
            orden.updateTotalPrice(product.getPrice() * quantity);
            return (Item) this.repository.save(item);
        }
        throw new DeliveryException("No existe la orden");
    }

    // A partir de aca metodos de TP2
    
    @Override
    public User updateUser(User user) throws DeliveryException{
        this.repository.update(user);
        return user;
    }
    @Override
    public Qualification updateQualification(Qualification qualification) throws DeliveryException{
        this.repository.update(qualification);
        return qualification;
    }
    
    @Override
    public List<User> getTopNUserWithMoreScore(int n){
        return this.repository.findTopNUserWithMoreScore(n);
    }

    @Override
    public List<DeliveryMan> getTop10DeliveryManWithMoreOrders(){
        return null;
    }

    @Override
    public List<Client> getUsersSpentMoreThan(float number){
        return null;
    }

    @Override
    public List<Order> getAllOrdersFromUser(String username){
        return null;
    }

    @Override
    public Long getNumberOfOrderNoDelivered(){
        return null;
    }

    @Override
    public Long getNumberOfOrderDeliveredAndBetweenDates(Date startDate, Date endDate){
        return null;
    }

    @Override
    public Optional<Order> getOrderDeliveredMoreExpansiveInDate(Date date){
        return null;
    }

    @Override
    public List<Supplier> getSuppliersWithoutProducts(){
        return null;
    }

    @Override
    public List<Product> getProductsWithPriceDateOlderThan(int days){
        return null;
    }

    @Override
    public List<Product> getTop5MoreExpansiveProducts(){
        return null;
    }

    @Override
    public Product getMostDemandedProduct(){
        return null;
    }

    @Override
    public List<Product> getProductsNoAddedToOrders(){
        return null;
    }

    @Override
    public List<ProductType> getTop3ProductTypesWithLessProducts(){
        return null;
    }

    @Override
    public Supplier getSupplierWithMoreProducts(){
        return null;
    }

    @Override
    public List<Supplier> getSupplierWith1StarCalifications(){
        return null;
    }

}




