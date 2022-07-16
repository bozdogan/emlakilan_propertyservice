package org.bozdgn.propertyservice.service;

import org.bozdgn.propertyservice.dto.PropertyInput;
import org.bozdgn.propertyservice.dto.PropertyOutput;
import org.bozdgn.propertyservice.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final Logger log = LoggerFactory.getLogger(getClass().getCanonicalName());
    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<PropertyOutput> getAll() {
        return List.of();
    }
    public PropertyOutput get(Long id) {
        return null;
    }
    public PropertyOutput save(PropertyInput propertyInput) {
        return null;
    }
    public PropertyOutput delete(Long id) {
        return null;
    }

}
