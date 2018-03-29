package com.ryanyi.restapi.Repository;

import com.ryanyi.restapi.Domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepo extends InMemRepo<Order> {

    protected void updateIfExists(Order original, Order desired) {
        original.setDecription(desired.getDecription());
        original.setCostInCents(desired.getCostInCents());
        original.setComplete(desired.isComplete());
    }
}
