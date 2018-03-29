package com.ryanyi.restapi.Resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryanyi.restapi.Domain.Order;
import org.springframework.hateoas.ResourceSupport;

/*
 * The primary reason to create a separate resource class is that
 * the resource class allows us to implement a level of indirection between the Order class itself
 * and how that class is presented.
 * In this case, although the fields are the same, we are also attaching links to the fields in the Order class.
 * Without a dedicated resource class, we would have to intermix the domain logic with the presentation logic,
 * which would cause serious dependency issues in a large-scale system.
 */
public class OrderResource extends ResourceSupport {

    // ResourceSupport allows us to attach links to our resource
    // Restrict values of the fields in the resource from changing after they have been set
    // Ensuring they reflect values of the Order class for which it is acting as a resource
    private final long id;
    private final String description;
    private final long costInCents;
    private final boolean isComplete;

    public OrderResource(Order order) {
        id = order.getId();
        description = order.getDecription();
        costInCents = order.getCostInCents();
        isComplete = order.isComplete();
    }

    /*
     * We cannot use the getId() method as our getter for our ID
     * since the ResourceSupport class has a default getId() method that returns a link.
     * Therefore, we use the getResourceId() method as our getter for our id field;
     * thus, we have to annotate our getResourceId() method since,
     * by default, our resource would serialize the ID field to resourceId due to the name of the getter method.
     * To force this property to be serialized to id, we use the @JsonProperty("id") annotation.
     */
    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getCostInCents() {
        return costInCents;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
