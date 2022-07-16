package org.bozdgn.propertyservice.model;

public enum PropertyListingStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String value;

    PropertyListingStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
