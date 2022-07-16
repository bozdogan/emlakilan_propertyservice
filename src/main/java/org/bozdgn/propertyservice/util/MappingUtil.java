package org.bozdgn.propertyservice.util;

import org.bozdgn.propertyservice.dto.PropertyInput;
import org.bozdgn.propertyservice.dto.PropertyOutput;
import org.bozdgn.propertyservice.model.Property;
import org.bozdgn.propertyservice.model.PropertyApprovalStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

public class MappingUtil {
    private MappingUtil() {
    }

    public static Property mapPropertyInputToProperty(PropertyInput propertyInput) {
        return propertyInput == null ? null : new Property(
                propertyInput.getId(),
                getCurrentUsername(),
                LocalDate.now(),
                LocalDate.now(),
                PropertyApprovalStatus.PENDING,
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

    private static String getCurrentUsername() {
        String result;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            result = ((UserDetails) principal).getUsername();
        } else {
            result = principal.toString();
        }

        return result;
    }
}
