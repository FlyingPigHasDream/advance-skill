package proxyclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author rainyday
 * @createTime 2018/6/2.
 */
public class ProxyBoss {

    /**
     * 对接口方法进行代理
     * 代理后返回的还是对象
     * @param discountCoupon
     * @param interfaceClass
     * @param implementsClass
     * @param <T>
     * @return
     */
    public static<T> T getProxy(final int discountCoupon,
                                final Class<?> interfaceClass, final Class<?> implementsClass) {
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[] {interfaceClass},new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Integer returnValue = (Integer) method.invoke(implementsClass.newInstance(), args);
                return returnValue - discountCoupon;
            }
        });
    }
}
