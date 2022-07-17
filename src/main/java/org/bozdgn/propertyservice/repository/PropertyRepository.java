package org.bozdgn.propertyservice.repository;

import org.bozdgn.propertyservice.model.Property;
import org.bozdgn.propertyservice.model.PropertyApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByStatus(PropertyApprovalStatus status);

}
