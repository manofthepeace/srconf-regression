package org.acme;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;
import io.smallrye.config.WithParentName;

@StaticInitSafe
@ConfigMapping(prefix = "my")
public interface MyConfig {

    @WithParentName
    Optional<Properties> properties();

    interface Properties {

        @WithName("config.main")
        List<MyMapping> sources();

        @WithName("config.urls")
        Map<Integer, String> urls();

        @WithParentName
        Map<String, Config> types();

        interface MyMapping {
            String key();

            List<String> id();
        }

        public interface Config {

            int maxConcurrentStart();

            int maxConcurrentStop();
        }

    }

}