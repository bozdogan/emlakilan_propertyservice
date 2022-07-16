package org.bozdgn.propertyservice.service;

import org.bozdgn.propertyservice.dto.PropertyInput;
import org.bozdgn.propertyservice.dto.PropertyOutput;
import org.bozdgn.propertyservice.error.PropertyNotFoundException;
import org.bozdgn.propertyservice.model.Property;
import org.bozdgn.propertyservice.repository.PropertyRepository;
import org.bozdgn.propertyservice.util.MappingUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.bozdgn.propertyservice.util.MappingUtil.mapPropertyInputToProperty;
import static org.bozdgn.propertyservice.util.MappingUtil.mapPropertyToPropertyOutput;

@Service
public class PropertyService {
    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<PropertyOutput> getAll() {
        return repository.findAll().stream().map(
                MappingUtil::mapPropertyToPropertyOutput
        ).collect(Collectors.toList());
    }

    public PropertyOutput get(Long id) {
        return mapPropertyToPropertyOutput(repository.findById(id).get());
    }

    public PropertyOutput save(PropertyInput propertyInput) {
        if (propertyInput.getId() == null) {
            Property property = mapPropertyInputToProperty(propertyInput);
            return mapPropertyToPropertyOutput(repository.save(property));
        } else {
            Optional<Property> optProperty = repository.findById(propertyInput.getId());
            if (optProperty.isPresent()) {
                Property property = mapPropertyInputToProperty(propertyInput);
                return mapPropertyToPropertyOutput(repository.save(property));
            } else {
                throw new PropertyNotFoundException(String.format("Property with id '%s'", propertyInput.getId()));
            }
        }
    }

    public void delete(Long id) {
        Optional<Property> property = repository.findById(id);
        if (property.isPresent()) {
            repository.delete(property.get());
        }
    }

}
