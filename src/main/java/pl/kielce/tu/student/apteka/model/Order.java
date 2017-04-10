//package pl.kielce.tu.student.apteka.model;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//@Entity
//@Table(name = "`order`")
//public class Order implements Persistable<Long> {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @NotNull
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @NotNull
//    @JoinColumn(name="client_id")
//    private Client client;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    @NotNull
//    private List<Medicine> medicines;
//
//    @Enumerated(EnumType.STRING)
//    @NotNull
//    @Column(name = "order_status")
//    private OrderStatus orderStatus;
//
//    public Order() {
//    }
//
//    public OrderStatus getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(OrderStatus orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }
//}
