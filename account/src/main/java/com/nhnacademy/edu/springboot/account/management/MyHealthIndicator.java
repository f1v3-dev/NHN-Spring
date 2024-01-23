package com.nhnacademy.edu.springboot.account.management;

import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {

    private final AtomicBoolean health = new AtomicBoolean(true);

    @Override
    public Health health() {

        if (health.get()) {
            return Health.up().build();
        }

        return Health.down().build();
    }

    public void setHealth(boolean bool) {
        health.set(bool);
    }
}
