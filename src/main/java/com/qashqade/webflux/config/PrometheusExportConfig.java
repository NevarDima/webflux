package com.qashqade.webflux.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.binder.jvm.JvmHeapPressureMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmInfoMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
import org.springframework.context.annotation.Bean;

public class PrometheusExportConfig {

    @Bean
    PrometheusScrapeEndpoint endpoint(){
        CollectorRegistry collectorRegistry = new CollectorRegistry(true);
        PrometheusMeterRegistry meterRegistry = new PrometheusMeterRegistry((key) -> null, collectorRegistry, Clock.SYSTEM);
        new JvmMemoryMetrics().bindTo(meterRegistry);
        new JvmThreadMetrics().bindTo(meterRegistry);
        new JvmInfoMetrics().bindTo(meterRegistry);
        new JvmHeapPressureMetrics().bindTo(meterRegistry);
        new ProcessorMetrics().bindTo(meterRegistry);
        return new PrometheusScrapeEndpoint(collectorRegistry);
    }

}
