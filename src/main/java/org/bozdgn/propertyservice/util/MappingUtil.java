package org.bozdgn.propertyservice.util;

import org.bozdgn.propertyservice.dto.PropertyInput;
import org.bozdgn.propertyservice.dto.PropertyOutput;
import org.bozdgn.propertyservice.model.Property;
import org.bozdgn.propertyservice.model.PropertyListingStatus;

public class MappingUtil {
    private MappingUtil() {
    }

    public static Property mapPropertyInputToProperty(PropertyInput propertyInput) {
        return propertyInput == null ? null : new Property(
                propertyInput.getId(),
                propertyInput.getAuthor(),
                propertyInput.getDateCreated(),
                propertyInput.getDateModified(),
                PropertyListingStatus.PENDING,
                propertyInput.getTitle(),
                propertyInput.getDescription(),
                propertyInput.getImageUri());
    }

    public static PropertyOutput mapPropertyToPropertyOutput(Property property) {
        return property == null ? null : new PropertyOutput(
                property.getId(),
                property.getAuthor(),
                property.getDateCreated(),
                property.getDateModified(),
                property.getStatus(),
                property.getTitle(),
                property.getDescription(),
                property.getImageUri());
    }
}
