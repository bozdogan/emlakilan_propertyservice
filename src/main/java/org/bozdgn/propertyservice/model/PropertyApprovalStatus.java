package org.bozdgn.propertyservice.model;

public enum PropertyApprovalStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String value;

    PropertyApprovalStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
