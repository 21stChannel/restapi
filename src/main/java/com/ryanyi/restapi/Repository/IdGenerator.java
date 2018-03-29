package com.ryanyi.restapi.Repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class IdGenerator {

    // Component: Injectable
    // Scope with PROTOTYPE: Ensures a new object is created each time is it autowired, not singleton injection
    private AtomicLong nextId = new AtomicLong(1);

    public long getNextId() {
        return nextId.getAndIncrement();
    }

}
