package com.ryanyi.restapi.Domain;

public interface Identifiable extends org.springframework.hateoas.Identifiable<Long> {
    public void setId(Long id);
}
