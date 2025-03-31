package org.acme;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

@StaticInitSafe
@ConfigMapping(prefix = "ok")
@ValidConfig
public interface MyEmptyConfig {

    boolean test();

}