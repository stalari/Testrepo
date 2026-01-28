package org.sample.foo.defaultsvc.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class FooWiringPropertiesTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
        .withUserConfiguration(FooDefaultApplicationTestConfig.class);

    @Test
    void startupFails_whenWiringMissing() {
        runner
            .withPropertyValues(
                "bar.defaultBaseUrl=http://localhost:1",
                "bar.acmeBaseUrl=http://localhost:2",
                "wiring.orgSampleFooBeanBar="
            )
            .run(ctx -> {
                assertThat(ctx.getStartupFailure()).isNotNull();
                assertThat(ctx.getStartupFailure().getMessage()).contains("Ambiguous or invalid wiring");
            });
    }

    @Test
    void startupFails_whenWiringInvalid() {
        runner
            .withPropertyValues(
                "bar.defaultBaseUrl=http://localhost:1",
                "bar.acmeBaseUrl=http://localhost:2",
                "wiring.orgSampleFooBeanBar=NotARealBean"
            )
            .run(ctx -> {
                assertThat(ctx.getStartupFailure()).isNotNull();
                assertThat(ctx.getStartupFailure().getMessage()).contains("org.sample.FooBean/bar");
            });
    }

    @Test
    void startupSucceeds_whenWiringIsAllowedValue() {
        runner
            .withPropertyValues(
                "bar.defaultBaseUrl=http://localhost:1",
                "bar.acmeBaseUrl=http://localhost:2",
                "wiring.orgSampleFooBeanBar=AcmeBarBean"
            )
            .run(ctx -> assertThat(ctx.getStartupFailure()).isNull());
    }
}