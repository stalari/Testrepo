package org.sample.foo.defaultsvc.config;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Validated
@ConfigurationProperties(prefix = "wiring")
public class FooWiringProperties {

    private static final Set<String> ALLOWED = Set.of("BarBean", "AcmeBarBean");

    /**
     * Mirrors legacy EAR application.xml:
     * ejb-ref-name 'org.sample.FooBean/bar' -> ejb-link (AcmeBarBean in legacy sample)
     */
    @NotBlank
    private String orgSampleFooBeanBar;

    public String orgSampleFooBeanBar() {
        return orgSampleFooBeanBar;
    }

    public void setOrgSampleFooBeanBar(String orgSampleFooBeanBar) {
        this.orgSampleFooBeanBar = orgSampleFooBeanBar;
    }

    @PostConstruct
    void validate() {
        if (!ALLOWED.contains(orgSampleFooBeanBar)) {
            throw new IllegalStateException(
                "Ambiguous or invalid wiring for key 'org.sample.FooBean/bar'. " +
                    "Expected one of " + ALLOWED + " but got: '" + orgSampleFooBeanBar + "'. " +
                    "Set env var WIRING_ORG_SAMPLE_FOOBEAN_BAR to resolve (deployment-time wiring override)."
            );
        }
    }
}