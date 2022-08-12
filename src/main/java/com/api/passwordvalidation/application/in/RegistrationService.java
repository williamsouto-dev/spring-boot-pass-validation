package com.api.passwordvalidation.application.in;

import com.api.passwordvalidation.adapter.out.metrics.IPrometheusAdapter;
import com.api.passwordvalidation.commons.enums.MetricType;
import com.api.passwordvalidation.domain.validation.Password;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.validation.ValidationException;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegistrationService implements IRegistrationService {

    private final IPrometheusAdapter metric;

    @Override
    public boolean validatePassword(String password) {
        log.info("M=ValidateRegistrationService.validatePassword Password validation password={}", password);
        metric.registerCount(MetricType.COUNTER_REQUEST_VALIDATE);

        boolean isValid = Boolean.TRUE;
        try {
            Password.of(password).check();
        } catch (ValidationException exception) {
            log.error("Invalid password. Error={}", exception.getMessage());
            isValid = false;
        }

        metric.registerCount(MetricType.COUNTER_REQUEST_VALIDATE_STATUS, "status", String.valueOf(isValid));
        return isValid;
    }
}
