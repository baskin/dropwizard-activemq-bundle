package com.kjetland.dropwizard.activemq;

import java.util.HashMap;
import java.util.Map;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ActiveMQBundleSet implements ConfiguredBundle<ActiveMQConfigsHolder>, Managed {

    Map<String, ActiveMQBundle> bundles = new HashMap<>();

    public ActiveMQBundleSet() {}

    @Override
    public void run(ActiveMQConfigsHolder configuration, Environment environment) throws Exception {
        for (String key : configuration.keys()) {
            ActiveMQBundle bundle = new ActiveMQBundle();
            bundles.put(key, bundle);
            bundle.run(configuration.get(key), environment);
        }
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        for (ActiveMQBundle b : bundles.values()) {
            b.initialize(bootstrap);
        }
    }

    @Override
    public void start() throws Exception {
        for (ActiveMQBundle b : bundles.values()) {
            b.start();
        }
    }

    @Override
    public void stop() throws Exception {
        for (ActiveMQBundle b : bundles.values()) {
            b.stop();
        }
    }
    
    public ActiveMQBundle bundle(String key) {
        return bundles.get(key);
    }
}
