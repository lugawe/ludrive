package app.ludrive.core.util;

import java.io.Closeable;
import java.time.Duration;
import java.util.concurrent.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Debouncer implements Consumer<Runnable>, Closeable {

    private static final AtomicInteger counter = new AtomicInteger();

    private final Object lock = new Object();

    private final ScheduledExecutorService scheduler;
    private final long delayMillis;

    private ScheduledFuture<?> scheduledTask;

    public Debouncer(Duration delayDuration) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor(
                r -> new Thread(r, "debouncer-thread-" + counter.incrementAndGet()));
        this.delayMillis = delayDuration.toMillis();
    }

    public void call(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable");
        }
        synchronized (lock) {
            if (scheduledTask != null && !scheduledTask.isDone()) {
                scheduledTask.cancel(false);
            }

            scheduledTask = scheduler.schedule(runnable, delayMillis, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void accept(Runnable runnable) {
        call(runnable);
    }

    @Override
    public void close() {
        scheduler.shutdownNow();
    }
}
