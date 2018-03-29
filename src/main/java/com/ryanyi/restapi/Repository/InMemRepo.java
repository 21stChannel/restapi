package com.ryanyi.restapi.Repository;

import com.ryanyi.restapi.Domain.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class InMemRepo<T extends Identifiable> {

    @Autowired
    private IdGenerator idGenerator;

    private List<T> elements = Collections.synchronizedList(new ArrayList<>());

    public T create(T element) {
        elements.add(element);
        element.setId(idGenerator.getNextId());
        return element;
    }

    // Why optional
    public Optional<T> findById(Long id) {
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public List<T> findAll() {
        return elements;
    }

    public boolean update(Long id, T updated) {
        if (updated == null) {
            return false;
        }

        Optional<T> element = findById(id);
        // Order class
        element.ifPresent(original -> updateIfExists(original, updated));
        return element.isPresent();
    }

    public boolean delete(Long id) {
        return elements.removeIf(elements -> elements.getId().equals(id));
    }

    public int getCount() {
        return elements.size();
    }

    public void clear() {
        elements.clear();
    }

    protected abstract void updateIfExists(T original, T desired);

}
