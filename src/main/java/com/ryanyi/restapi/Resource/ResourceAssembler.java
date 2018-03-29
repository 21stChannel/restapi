package com.ryanyi.restapi.Resource;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ResourceAssembler<DomainType, ResourceType> {

    public abstract ResourceType toResource(DomainType domainTypeObject);

    public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainTypeObjects) {
        return domainTypeObjects.stream().map(o -> toResource(o)).collect(Collectors.toList());
    }
}
