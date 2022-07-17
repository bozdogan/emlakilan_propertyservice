package org.bozdgn.propertyservice;

import org.bozdgn.propertyservice.model.Property;
import org.bozdgn.propertyservice.model.PropertyApprovalStatus;
import org.bozdgn.propertyservice.repository.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class PropertyserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner setupTestProperties(PropertyRepository propertyRepository) {
        return args -> {
            propertyRepository.save(new Property(
                    null,
                    "doctorx",
                    LocalDate.parse("2006-03-14"),
                    LocalDate.now(),
                    PropertyApprovalStatus.ACCEPTED,
                    "2+1, Caddeye Yakın",
                    "Metro durağına koşarak 20 dakika mesafede güneş gören evler",
                    "manzara.jpg",
                    0
            ));
            propertyRepository.save(new Property(
                    null,
                    "stinson",
                    LocalDate.parse("2006-04-09"),
                    LocalDate.now(),
                    PropertyApprovalStatus.ACCEPTED,
                    "2+1, Caddeye Yakın",
                    "Metro durağına koşarak 20 dakika mesafede güneş gören evler",
                    "legendary.jpg",
                    0
            ));
            propertyRepository.save(new Property(
                    null,
                    "doctorx",
                    LocalDate.parse("2008-12-25"),
                    LocalDate.now(),
                    PropertyApprovalStatus.REJECTED,
                    "Arefe Bayramınız kutlu olsun",
                    "adlkjflsf skfs lkjfals fklsj",
                    "blurps.bmp",
                    0
            ));
            propertyRepository.save(new Property(
                    null,
                    "doctorx",
                    LocalDate.parse("2008-12-25"),
                    LocalDate.now(),
                    PropertyApprovalStatus.PENDING,
                    "1+1 Tutar İNşaat manzaralı",
                    "Otoyola yakın ulaşımı kolay villa apartmanlar",
                    "vinc.bmp",
                    0
            ));
            propertyRepository.save(new Property(
                    null,
                    "user",
                    LocalDate.parse("2022-07-13"),
                    LocalDate.now(),
                    PropertyApprovalStatus.PENDING,
                    "Example Property Listing",
                    "Lorem ipsum dolor sit amet. I saw the tail of a comet.",
                    "N/A",
                    0
            ));
        };
    }

}
