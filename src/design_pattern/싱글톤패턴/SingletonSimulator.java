package design_pattern.싱글톤패턴;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonSimulator {

    public static void main(String[] args) throws Exception {
        int poolSize = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        CountDownLatch latch = new CountDownLatch(poolSize);
        Singleton[] instances = new Singleton[poolSize];
        for (int i = 0; i < poolSize; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    instances[finalI] = Singleton.getInstance();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        System.out.println("isUnique? " + isUnique(instances));
    }

    static boolean isUnique(Singleton[] singletons) {
        for (int i=0;i<singletons.length-1;i++) {
            if (singletons[i] != singletons[i+1]) return false;
        }
        return true;
    }
}
