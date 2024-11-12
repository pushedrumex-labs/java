package proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TargetMethodInterceptor implements MethodInterceptor {

    Object target;

    public TargetMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable {
        System.out.println("start");

        Object object = methodProxy.invoke(target, objects);

        System.out.println("end");

        return object;
    }
}
