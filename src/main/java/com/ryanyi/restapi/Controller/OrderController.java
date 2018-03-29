package com.ryanyi.restapi.Controller;

import com.ryanyi.restapi.Domain.Order;
import com.ryanyi.restapi.Repository.OrderRepo;
import com.ryanyi.restapi.Resource.OrderResource;
import com.ryanyi.restapi.Resource.OrderResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Order.class)
@RequestMapping(value = "/order", produces = "application/json")
public class OrderController {

    // CrossOrigin for CORS
    // The @RestControllera annotation, tells Spring that this class is a controller and will include REST endpoints.
    // This annotation is coupled with the @ExposesResourceFor(Order.class) annotation,
    // which denotes that if a link is needed to anOrder object,
    // this controller should be used to provide the path for that link.

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderResourceAssembler orderResourceAssembler;

    // /orders
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderResource>> findAllOrders() {
        List<Order> orders = orderRepo.findAll();
        // Http response and body, json
        return new ResponseEntity<>(orderResourceAssembler.toResourceCollection(orders), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderResource> findOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepo.findById(id);

        if (order.isPresent()) {
            return new ResponseEntity<>(orderResourceAssembler.toResource(order.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<OrderResource> createOrder(@RequestBody Order order) {
        Order createdOrder = orderRepo.create(order);
        return new ResponseEntity<>(orderResourceAssembler.toResource(createdOrder), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderRepo.delete(id);
        HttpStatus httpStatus = isDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(httpStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<OrderResource> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        boolean isUpdated = orderRepo.update(id, order);

        if (isUpdated) {
            return findOrderById(id);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
