package org.bozdgn.propertyservice.service;

import org.bozdgn.propertyservice.dto.PropertyInput;
import org.bozdgn.propertyservice.dto.PropertyOutput;
import org.bozdgn.propertyservice.dto.UpdateOutput;
import org.bozdgn.propertyservice.error.PropertyNotFoundException;
import org.bozdgn.propertyservice.model.Property;
import org.bozdgn.propertyservice.model.PropertyApprovalStatus;
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

    private static final String ERR_NOT_FOUND_WITH_ID = "Property with id '%s' does not exist";
    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }


    public List<PropertyOutput> getAll() {
        return repository.findAll().stream()
                .map(MappingUtil::mapPropertyToPropertyOutput)
                .collect(Collectors.toList());
    }

    public List<PropertyOutput> getAcceptedAds() {
        return repository.findAllByStatus(PropertyApprovalStatus.ACCEPTED).stream()
                .map(MappingUtil::mapPropertyToPropertyOutput)
                .collect(Collectors.toList());
    }

    public List<PropertyOutput> getPendingAds() {
        return repository.findAllByStatus(PropertyApprovalStatus.PENDING).stream()
                .map(MappingUtil::mapPropertyToPropertyOutput)
                .collect(Collectors.toList());
    }

    public List<PropertyOutput> getRejectedAds() {
        return repository.findAllByStatus(PropertyApprovalStatus.REJECTED).stream()
                .map(MappingUtil::mapPropertyToPropertyOutput)
                .collect(Collectors.toList());
    }

    public List<PropertyOutput> getLatestAcceptedAds() {
        return repository.findTop10ByStatusOrderByDateCreated(PropertyApprovalStatus.ACCEPTED).stream()
                .map(MappingUtil::mapPropertyToPropertyOutput)
                .collect(Collectors.toList());
    }

    public PropertyOutput get(Long id) {
        return mapPropertyToPropertyOutput(repository.findById(id).orElse(null));
    }

    public PropertyOutput save(PropertyInput propertyInput) {
        if (propertyInput.getId() == null) {
            Property property = mapPropertyInputToProperty(propertyInput);
            return mapPropertyToPropertyOutput(repository.save(property));
        } else {
            Optional<Property> optProperty = repository.findById(propertyInput.getId());
            if (optProperty.isPresent()) {
                Property property = mapPropertyInputToProperty(propertyInput);
                property.setDateCreated(optProperty.get().getDateCreated());

                return mapPropertyToPropertyOutput(repository.save(property));
            } else {
                throw new PropertyNotFoundException(String.format(ERR_NOT_FOUND_WITH_ID, propertyInput.getId()));
            }
        }
    }

    public void delete(Long id) {
        Optional<Property> property = repository.findById(id);
        if (property.isPresent()) {
            repository.delete(property.get());
        }
    }

    public UpdateOutput changeApprovalStatus(Long id, PropertyApprovalStatus newStatus) {
        Optional<Property> propertyQuery = repository.findById(id);
        if (propertyQuery.isPresent()) {
            Property property = propertyQuery.get();
            property.setStatus(newStatus);
            repository.save(property);
            return new UpdateOutput(UpdateOutput.Status.SUCCESS, "Status set to " + newStatus);
        } else {
            return new UpdateOutput(UpdateOutput.Status.ERROR, String.format(ERR_NOT_FOUND_WITH_ID, id));
        }
    }

}
