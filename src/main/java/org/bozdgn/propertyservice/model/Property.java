package org.bozdgn.propertyservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "property")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    Long id;


    @Column(name = "author", nullable = false, length = 20)
    String author;

    @Column(name = "date_created")
    LocalDate dateCreated;

    @Column(name = "date_modified")
    LocalDate dateModified;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    PropertyApprovalStatus status;

    @Column(name = "title", length = 240)
    String title;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "image_uri", length = 512)
    String imageUri;

    @Column(name = "view_count")
    Integer viewCount;
}