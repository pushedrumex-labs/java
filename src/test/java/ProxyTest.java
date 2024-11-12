import java.lang.reflect.Proxy;
import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;
import proxy.cglib.Target;
import proxy.cglib.TargetMethodInterceptor;
import proxy.dynamic.ProxyHandler;
import proxy.dynamic.TargetInterface;
import proxy.dynamic.TargetImpl;

class ProxyTest {

    @Test
    void dynamicProxy() {
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(TargetInterface.class.getClassLoader(),
            new Class[]{TargetInterface.class},
            new ProxyHandler(new TargetImpl()));

        proxy.print();
    }

    // 옵션 추가 필요 : --add-opens java.base/java.lang=ALL-UNNAMED (Java 모듈 시스템 접근 권한 부여)
    @Test
    void cglibProxy() {
        Target target = new Target();
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(Target.class); // 구체 클래스를 상속받아 프록시 생성
        enhancer.setCallback(new TargetMethodInterceptor(target)); // 프록시에 적용할 로직

        Target proxy = (Target) enhancer.create();

        proxy.print();
    }

}
