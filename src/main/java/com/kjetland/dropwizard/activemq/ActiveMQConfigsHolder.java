package com.kjetland.dropwizard.activemq;

import java.util.Set;

public interface ActiveMQConfigsHolder {

    Set<String> keys();
    
    ActiveMQConfigHolder get(String key);
}
