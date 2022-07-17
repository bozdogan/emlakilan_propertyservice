package org.bozdgn.propertyservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bozdgn.propertyservice.model.PropertyApprovalStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyOutput {
    Long id;

    String author;
    LocalDate dateCreated;
    LocalDate dateModified;
    PropertyApprovalStatus status;
    String title;
    String description;
    String imageUri;
    Integer viewCount;
}