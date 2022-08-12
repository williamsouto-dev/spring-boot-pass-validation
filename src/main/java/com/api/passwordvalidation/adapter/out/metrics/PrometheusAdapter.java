package com.api.passwordvalidation.adapter.out.metrics;

import com.api.passwordvalidation.commons.enums.MetricType;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrometheusAdapter implements IPrometheusAdapter {

    private final MeterRegistry registry;

    @Override
    public void registerCount(MetricType metricType) {
        registry.counter(metricType.getType()).increment();
    }

    @Override
    public void registerCount(MetricType metricType, String... tags) {
        registry.counter(metricType.getType(), tags).increment();
    }
}
