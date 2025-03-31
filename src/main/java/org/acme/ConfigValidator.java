package org.acme;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfigValidator implements ConstraintValidator<ValidConfig, MyEmptyConfig> {

    @Override
    public boolean isValid(MyEmptyConfig myEmptyConf, ConstraintValidatorContext context) {

        SmallRyeConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);

        // https://github.com/quarkusio/quarkus/issues/45549
        SmallRyeConfig config2 = new SmallRyeConfigBuilder()
                .withMapping(MyConfig.class)
                .withSources(new ConfigSource() {
                    @Override
                    public Set<String> getPropertyNames() {
                        Set<String> properties = new HashSet<>();
                        config.getPropertyNames().forEach(properties::add);
                        return properties;
                    }

                    @Override
                    public String getValue(final String propertyName) {
                        return config.getRawValue(propertyName);
                    }

                    @Override
                    public String getName() {
                        return "My Config";
                    }
                }).build();
        MyConfig myConf = config2.getConfigMapping(MyConfig.class);
        return myConf.properties().isPresent();

    }

}
