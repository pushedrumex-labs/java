import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SynchronicityTest {

    int count = 0;
    @Test
    void 동시성_문제() throws Exception {
        count = 0;
        int poolSize = 50;
        int click = 1_000;

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        CountDownLatch latch = new CountDownLatch(click);

        for (int i = 0; i < click; i++) {
            executorService.submit(() -> {
                try {
                    count++;
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertThat(count).isLessThan(click);
    }

    @Test
    void 동시성_문제_해결O_synchronized() throws Exception {
        count = 0;
        int poolSize = 50;
        int click = 1_000;

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        CountDownLatch latch = new CountDownLatch(click);

        for (int i = 0; i < click; i++) {
            executorService.submit(() -> {
                try {
                    synchronized (this) {
                        count++;
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertThat(count).isEqualTo(click);
    }

    AtomicInteger atomicCount = new AtomicInteger(0);
    @Test
    void 동시성_문제_해결O_Atomic() throws Exception {
        int poolSize = 50;
        int click = 1_000;

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        CountDownLatch latch = new CountDownLatch(click);

        for (int i = 0; i < click; i++) {
            executorService.submit(() -> {
                try {
                    atomicCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertThat(atomicCount.get()).isEqualTo(click);
    }

    boolean stop = false;
    @Disabled
    @Test
    void 가시성_문제() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            while (!stop) {

            }
            latch.countDown();
        }).start();

        Thread.sleep(1000);
        stop = true;

        latch.await();
    }

    volatile boolean volatileStop = false;
    @Test
    void 가시성_문제_해결O_volatile() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            while (!volatileStop) {

            }
            latch.countDown();
        }).start();

        Thread.sleep(1000);
        volatileStop = true;

        latch.await();
    }

    AtomicBoolean atomicStop = new AtomicBoolean(false);
    @Test
    void 가시성_문제_해결O_Atomic() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            while (!atomicStop.get()) {

            }
            latch.countDown();
        }).start();

        Thread.sleep(1000);
        atomicStop.set(true);

        latch.await();
    }

}
