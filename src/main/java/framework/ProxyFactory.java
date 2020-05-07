package framework;

import protocol.http.HttpClient;
import register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {


    //consummer通过注册中心获取privider地址，避免地址写死
    //存在问题：更改通讯协议需要修改源码，比如从httpClient到NettyClient需要修改源码
    public static<T> T getProxy(final Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient();
                //通过注册中心获取privider地址，以地址随机的方式调用privider
                URL url = RemoteMapRegister.random(interfaceClass.getName());
                String result = httpClient.send(url.getHostname(),url.getPort(),
                        new Invocation(interfaceClass.getName(),method.getName(), method.getParameterTypes(), args));

                return result;
            }
        });
    }


}
