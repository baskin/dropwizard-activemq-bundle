package com.kjetland.dropwizard.activemq;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

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

    private ActiveMQReceiver<T> receiver;
    private Meter eventReceivedMeter;

    public InstrumentedReceiver(ActiveMQReceiver<T> receiver, MetricRegistry metricRegistry, String meterName) {
        this.receiver = receiver;
        this.eventReceivedMeter = metricRegistry.meter(meterName);
    }

    public void receive(T message) {
        eventReceivedMeter.mark();
        receiver.receive(message);
    }
}
