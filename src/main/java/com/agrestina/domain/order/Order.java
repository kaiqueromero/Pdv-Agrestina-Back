package com.agrestina.domain.order;


import com.agrestina.domain.client.Client;
import com.agrestina.domain.orderedItems.OrderedItem;
import com.agrestina.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderedItem> items = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne
    private Client client;

    public Order(List<OrderedItem> items, User user, Client client) {
        this.date = LocalDate.now();
        this.items = items;
        this.items.forEach(i -> i.setOrder(this));
        this.user = user;
        this.client = client;
    }
}
