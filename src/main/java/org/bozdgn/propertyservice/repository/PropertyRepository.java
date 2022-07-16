package org.bozdgn.propertyservice.repository;

import org.bozdgn.propertyservice.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    public Property findByAuthor(String author);
}
