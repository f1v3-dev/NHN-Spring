package com.nhnacademy.edu.springboot.account.management;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "counter")
public class CounterEndpoint {

    private final AtomicLong count = new AtomicLong(0L);

    @ReadOperation
    public Long read() {
        return count.get();
    }

    @WriteOperation
    public Long increment(@Nullable Long delta) {
        if (delta == null) {
            return count.incrementAndGet();
        }

        return count.addAndGet(delta);
    }

    @DeleteOperation
    public Long reset() {
        count.set(0);

        return count.get();
    }
}
