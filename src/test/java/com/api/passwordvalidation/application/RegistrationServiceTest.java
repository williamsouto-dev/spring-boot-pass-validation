package com.api.passwordvalidation.application;

import com.api.passwordvalidation.adapter.out.metrics.PrometheusAdapter;
import com.api.passwordvalidation.application.in.RegistrationService;
import com.api.passwordvalidation.commons.enums.MetricType;
import com.api.passwordvalidation.constants.ConstantsTest;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RegistrationServiceTest {

    @InjectMocks
    RegistrationService service;

    @Mock
    PrometheusAdapter prometheusAdapter;

    @Test
    public void when_validate_with_sucess_password_valid() {
        boolean result = service.validatePassword(ConstantsTest.PASS_VALID);

        verify(prometheusAdapter, times(1)).registerCount(MetricType.COUNTER_REQUEST_VALIDATE);
        verify(prometheusAdapter, times(1)).registerCount(MetricType.COUNTER_REQUEST_VALIDATE_STATUS, "status", String.valueOf(result));
        assertEquals(result, Boolean.TRUE);
    }

    @Test
    public void when_validate_with_sucess_password_invalid() {
        boolean result = service.validatePassword(ConstantsTest.PASS_INVALID);

        verify(prometheusAdapter, times(1)).registerCount(MetricType.COUNTER_REQUEST_VALIDATE);
        verify(prometheusAdapter, times(1)).registerCount(MetricType.COUNTER_REQUEST_VALIDATE_STATUS, "status", String.valueOf(result));
        assertEquals(result, Boolean.FALSE);
    }

    @Test
    public void when_validate_with_sucess_all_password_invalid() {
        List<String> passwords = Arrays.asList("aa", "ab", "AAAbbbCc", "AbTp9!foo", "AbTp9!foA", "AbTp9 fok");

        for (String password : passwords) {
            boolean result = service.validatePassword(password);
            assertEquals(result, Boolean.FALSE);
        }

        verify(prometheusAdapter, times(6)).registerCount(MetricType.COUNTER_REQUEST_VALIDATE);
        verify(prometheusAdapter, times(6)).registerCount(eq(MetricType.COUNTER_REQUEST_VALIDATE_STATUS), eq("status"), anyString());
    }

    @Test
    public void when_validate_with_sucess_password_empty() {
        boolean result = service.validatePassword(Strings.EMPTY);

        assertEquals(result, Boolean.FALSE);
        verify(prometheusAdapter, times(1)).registerCount(MetricType.COUNTER_REQUEST_VALIDATE);
        verify(prometheusAdapter, times(1)).registerCount(eq(MetricType.COUNTER_REQUEST_VALIDATE_STATUS), eq("status"), anyString());
    }
}
