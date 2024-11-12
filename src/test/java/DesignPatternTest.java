import static org.assertj.core.api.Assertions.assertThat;

import design_pattern.데코레이터패턴.Beverage;
import design_pattern.데코레이터패턴.Espresso;
import design_pattern.데코레이터패턴.Milk;
import design_pattern.데코레이터패턴.Whip;
import design_pattern.싱글톤패턴.Singleton;
import design_pattern.옵저버패턴.AObserver;
import design_pattern.옵저버패턴.ASubject;
import design_pattern.옵저버패턴.BObserver;
import design_pattern.전략패턴.FlyNoWay;
import design_pattern.전략패턴.MallarDuck;
import design_pattern.커맨드패턴.Command;
import design_pattern.커맨드패턴.Light;
import design_pattern.커맨드패턴.LightOffCommand;
import design_pattern.커맨드패턴.LightOnCommand;
import design_pattern.커맨드패턴.RemoteControl;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

class DesignPatternTest {

    @Test
    void 데코레이터패턴() {
        Beverage beverage = new Espresso(); // 에스프레소 5.0
        beverage = new Milk(beverage); // 우유 토핑 추가 1.0
        beverage = new Whip(beverage); // 휘핑 크림 추가 2.0

        assertThat(beverage.cost()).isEqualTo(8.0);
    }

    @Test
    void 싱글톤패턴() throws Exception {
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

        assertThat(isUnique(instances)).isTrue();
    }

    boolean isUnique(Singleton[] singletons) {
        for (int i=0;i<singletons.length-1;i++) {
            if (singletons[i] != singletons[i+1]) return false;
        }
        return true;
    }

    @Test
    void 옵저버패턴() {

        ASubject aSubject = new ASubject();
        AObserver aObserver = new AObserver(aSubject);
        BObserver bObserver = new BObserver(aSubject);

        aSubject.setData(1, 2);
        aSubject.setData(10, 20);
    }

    @Test
    void 전략패턴() {
        MallarDuck mallarDuck = new MallarDuck();

        mallarDuck.display();
        mallarDuck.performFly();
        mallarDuck.performQuack();
        mallarDuck.setFlyBehavior(new FlyNoWay());
        mallarDuck.performFly();
    }

    @Test
    void 커맨드패턴() {
        RemoteControl remote = new RemoteControl(); // 인보커

        Light light = new Light(); // 리시버
        Command lightOnCommand = new LightOnCommand(light); // 커맨드
        Command lightOffCommand = new LightOffCommand(light); // 커맨드

        remote.setCommand(0, lightOnCommand, lightOffCommand);

        remote.onButtonWasPressed(0); // 실행 - 조명 키기
        remote.offButtonWasPressed(0); // 실행 - 조명 끄기
    }

}
