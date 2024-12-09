package com.agrestina.service;

import com.agrestina.domain.client.Client;
import com.agrestina.domain.order.Order;
import com.agrestina.domain.orderedItems.OrderedItem;
import com.agrestina.domain.user.User;
import com.agrestina.dto.order.OrderDTO;
import com.agrestina.dto.order.RegisterOrderDTO;
import com.agrestina.exeption.ValidationException;
import com.agrestina.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public OrderDTO register(RegisterOrderDTO dto, User user, Client client) {
        var items = new ArrayList<OrderedItem>();
        for (var itemDto : dto.items()) {
            var inventory = inventoryRepository.findByProductId(itemDto.productId());
            if (inventory.getQuantity() >= itemDto.quantity()) {
                var product = productRepository.findById(itemDto.productId()).get();
                if (!product.isActive()) {
                    throw new ValidationException("Pedido contém produto excluído: " + product.getId());
                } else {
                    var orderedItem = new OrderedItem(null, product, itemDto.quantity());
                    items.add(orderedItem);
                    inventory.decrease(itemDto.quantity());
                }
            } else {
                throw new ValidationException("Estoque indisponível para o produto: " + itemDto.productId());
            }
        }

        var order = new Order(items, user, client);

        repository.save(order);

        return new OrderDTO(order);

    }
}
