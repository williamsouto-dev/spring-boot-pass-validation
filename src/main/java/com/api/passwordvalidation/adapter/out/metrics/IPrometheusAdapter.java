package com.api.passwordvalidation.adapter.out.metrics;

import com.api.passwordvalidation.commons.enums.MetricType;

public interface IPrometheusAdapter {

    void registerCount(MetricType metricType);

    void registerCount(MetricType metricType, String... tags);
}
