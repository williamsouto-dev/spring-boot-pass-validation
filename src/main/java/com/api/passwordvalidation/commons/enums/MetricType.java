package com.api.passwordvalidation.commons.enums;

import lombok.Getter;

@Getter
public enum MetricType {

    COUNTER_REQUEST_VALIDATE("counter_request_validate", "Total count requests validate."),
    COUNTER_REQUEST_VALIDATE_STATUS("counter_request_validate_status", "Total count requests validate by status(success or error).");

    private final String type;
    private final String description;

    MetricType(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
