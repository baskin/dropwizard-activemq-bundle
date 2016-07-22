package com.kjetland.dropwizard.activemq;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;

/**
 * 
 * An ActiveMQ receiver with following metrics :
 * 
 * <ul>
 *   <li>meter for received event</li>
 * </ul>
 * 
 * @param <T>
 */
public class InstrumentedReceiver<T> implements ActiveMQReceiver<T> {

    private static final MetricRegistry METRICS = SharedMetricRegistries.getOrCreate("default");

    private ActiveMQReceiver<T> receiver;
    private Meter eventReceivedMeter;

    public InstrumentedReceiver(ActiveMQReceiver<T> receiver, String meterName) {
        this.receiver = receiver;
        this.eventReceivedMeter = METRICS.meter(meterName);
    }

    public void receive(T message) {
        eventReceivedMeter.mark();
        receiver.receive(message);
    }
}
