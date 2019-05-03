package com.mesosphere.scratch.statsd;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TestStatsD {
    public static void main( String[] tags) {
        log("Running with tags:");
        Arrays.asList(tags).forEach(TestStatsD::log);

        MetricRegistry registry = new MetricRegistry();
        Counter counter = registry.counter("test_counter");

        String statsdHost = System.getenv("STATSD_UDP_HOST");
        String statsdPort = System.getenv("STATSD_UDP_PORT");

        StatsdReporter reporter = new StatsdReporter(
                registry,
                statsdHost,
                Integer.parseInt(statsdPort),
                "",
                tags,
                MetricFilter.ALL,
                TimeUnit.SECONDS,
                TimeUnit.MILLISECONDS
        );

        reporter.start(10, TimeUnit.SECONDS);

        while(true){
            counter.inc();
            log("Counter incremented, current value: " + counter.getCount());
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    static void log(String msg) {
        System.out.println(msg);
    }
}
