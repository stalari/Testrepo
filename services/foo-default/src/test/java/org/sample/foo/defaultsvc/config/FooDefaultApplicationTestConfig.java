package org.sample.foo.defaultsvc.config;

import org.sample.foo.defaultsvc.FooDefaultApplication;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

final class FooDefaultApplicationTestConfig {
    static ApplicationContextRunner baseRunner() {
        return new ApplicationContextRunner().withConfiguration(AutoConfigurations.of(FooDefaultApplication.class));
    }
    private FooDefaultApplicationTestConfig() {}
}