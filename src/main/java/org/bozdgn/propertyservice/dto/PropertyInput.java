package org.bozdgn.propertyservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyInput {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    Long id;

    String author;
    LocalDate dateCreated;
    LocalDate dateModified;
    String title;
    String description;
    String imageUri;
}